package Adapter

import Interface.CardListener
import Model.Hewan
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vp.week1_0706012110021.R
import com.vp.week1_0706012110021.databinding.CardAnimalBinding

class ListHewanRVAdapter (val listHewan: ArrayList<Hewan>): RecyclerView.Adapter<ListHewanRVAdapter.viewHolder>() {
    class viewHolder (val itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = CardAnimalBinding.bind(itemView)

        fun setData(data: Hewan){
            binding.aniNameTextView.text = data.namanya
            binding.aniTypeTextView.text = data.jenisnya
            binding.aniAgeTextView.text = "Usia: " + data.usianya + " tahun"
            if(data.imageUri.isNotEmpty()) {
                binding.aniImageImageView.setImageURI(Uri.parse(data.imageUri))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_animal, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listHewan[position])
    }

    override fun getItemCount(): Int {
        return listHewan.size
    }
}