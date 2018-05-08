package com.massita.upmovies.feature.detail

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.view.Menu
import android.view.MenuItem
import com.massita.upmovies.R
import com.massita.upmovies.api.model.Movie
import com.massita.upmovies.extension.load
import com.massita.upmovies.feature.detail.fragment.MovieDetailFragment
import com.massita.upmovies.feature.detail.notification.NotificationPublisher
import com.massita.upmovies.feature.detail.notification.ScheduledAlarm
import com.massita.upmovies.helper.NotificationHelper
import com.massita.upmovies.helper.SharedPreferencesHelper
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity(), MovieDetailActivityContract.View {
    object Tag {
        const val MOVIE_DETAIL = "MOVIE_DETAIL"
    }

    companion object {

        const val TAG_MOVIE = "TAG_MOVIE"

        fun newIntent(context: Context, movie: Movie) : Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(TAG_MOVIE, movie)
            return intent
        }
    }

    lateinit var presenter: MovieDetailActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_detail)

        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val movie : Movie = intent.getParcelableExtra(TAG_MOVIE)

        presenter = MovieDetailActivityPresenter(this, movie)

        presenter.start()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_movie_detail, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
            R.id.action_remember -> {
                presenter.onRememberClick()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun setupCollapsingToolbar() {
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))
    }

    override fun setCoverImage(path: String) {
        coverImage.load(path, presenter.applyPalette())
    }

    override fun setupCollapsingToolbarColors() {
        val bitmap = (coverImage.drawable as BitmapDrawable).bitmap
        Palette.from(bitmap).generate { palette ->
            val primary = ContextCompat.getColor(this, R.color.colorPrimary)

            collapsingToolbar.setContentScrimColor(palette.getVibrantColor(primary));
        }
    }

    override fun showDetailFragment(movieId: Int) {
        val fragment = MovieDetailFragment.newInstance(movieId)
        showFragment(fragment, Tag.MOVIE_DETAIL)
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
    }

    fun showFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.addToBackStack(tag)
        transaction.replace(R.id.fragment_container, fragment, tag)
        transaction.commit()
    }

    override fun showRememberDialogPicker(options: Array<CharSequence>) {
        AlertDialog.Builder(this)
                .setTitle(R.string.dialog_select_remember_date_title)
                .setItems(options, ({ dialog, selected -> presenter.onRememberDateSelected(selected) }))
                .show()
    }

    override fun showEmptyRememberDatesMessage() {
        Snackbar.make(coordinatorLayout, R.string.movie_already_released, Snackbar.LENGTH_LONG)
                .show()
    }

    override fun scheduleNotification(dateTimeInMillis: Long, title: String?, date: String?, id: Int) {
        val builder = NotificationHelper(this).getNotification(
                getString(R.string.notification_movie_release_title),
                getString(R.string.notification_movie_release_message, title, date))

        val intent = MovieDetailActivity.newIntent(this, presenter.getMovie())
        val activity = PendingIntent.getActivity(this, id, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        builder.setContentIntent(activity)

        val notification = builder.build()

        val notificationIntent = Intent(this, NotificationPublisher::class.java)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, id)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification)
        val pendingIntent = PendingIntent.getBroadcast(this, id, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        SharedPreferencesHelper(this).saveScheduledAlarm(ScheduledAlarm(id, notification))

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, dateTimeInMillis, pendingIntent)
    }
}