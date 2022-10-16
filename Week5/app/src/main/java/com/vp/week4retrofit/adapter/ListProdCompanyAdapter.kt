package com.vp.week4retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vp.week4retrofit.R
import com.vp.week4retrofit.helper.Const
import com.vp.week4retrofit.model.ProductionCompany

class ListProdCompanyAdapter(private val dataSet: List<ProductionCompany>): RecyclerView.Adapter<ListProdCompanyAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val ivListProdCompany: ImageView

        init {
            ivListProdCompany = view.findViewById(R.id.iv_prodCompany)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_prod_company_movie_detail, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Glide.with(viewHolder.itemView.context).load(Const.IMG_URL + dataSet[position].logo_path)
            .into(viewHolder.ivListProdCompany)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}