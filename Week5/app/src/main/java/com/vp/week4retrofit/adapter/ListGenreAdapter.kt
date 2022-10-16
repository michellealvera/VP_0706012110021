package com.vp.week4retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vp.week4retrofit.R
import com.vp.week4retrofit.model.Genre
import com.vp.week4retrofit.model.MovieDetails

class ListGenreAdapter(private val dataSet: List<Genre>): RecyclerView.Adapter<ListGenreAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvListGenre: TextView

        init {
            tvListGenre = view.findViewById(R.id.tv_listgenre)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListGenreAdapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_genres_movie_detail, viewGroup, false)

        return ListGenreAdapter.ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ListGenreAdapter.ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvListGenre.text = dataSet[position].name
        }
//        viewHolder.cvListGenre.setOnClickListener {
//            val intent = Intent(it.context, MovieDetail::class.java)
//            intent.putExtra("movie_id", dataSet[position].id)
//            it.context.startActivity(intent)
//        }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}
