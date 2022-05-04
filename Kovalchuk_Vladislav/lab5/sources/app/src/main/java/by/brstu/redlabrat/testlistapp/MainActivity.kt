package by.brstu.redlabrat.testlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.brstu.redlabrat.testlistapp.api.OmdbSearchResultMovie

class MainActivity : AppCompatActivity(), MyListAdapter.MyItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onResume() {
        super.onResume()
        val fragment = ListFragment.newInstance("")
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFrame, fragment)
            .commit()
    }

    override fun onMyItemClick(selectedMovie: OmdbSearchResultMovie) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrame, DetailsFragment.newInstance(selectedMovie.title))
            .addToBackStack(null)
            .commit()
    }
}