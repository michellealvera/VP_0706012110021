package Adapter

import Interface.CardListener
import Model.Hewan
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.vp.week1_0706012110021.InputanimalActivity
import com.vp.week1_0706012110021.R
import com.vp.week1_0706012110021.databinding.CardAnimalBinding
import kotlin.coroutines.coroutineContext

class ListHewanRVAdapter(val listHewan: ArrayList<Hewan>, cardListener: CardListener): RecyclerView.Adapter<ListHewanRVAdapter.viewHolder>() {
    class viewHolder (val itemView: View, val cardListenerr: CardListener): RecyclerView.ViewHolder(itemView) {
        val binding = CardAnimalBinding.bind(itemView)
        val listHewan = ArrayList<Hewan>()
        val position = listHewan[adapterPosition]

        fun setData(data: Hewan){
            binding.aniNameTextView.text = data.namanya
            binding.aniTypeTextView.text = data.jenisnya
            binding.aniAgeTextView.text = "Usia: ${data.usianya} tahun"
            if(data.imageUri.isNotEmpty()) {
                binding.aniImageImageView.setImageURI(Uri.parse(data.imageUri))
            }
//            binding.editButton.setOnClickListener {
//                val myIntent = Intent(context, InputanimalActivity::class.java).putExtra("position", position)
//                context.startActivity(myIntent)
//            }
//            binding.deleteButton.setOnClickListener {
//                AlertDialog.Builder(context).setTitle("Delete").setIcon(R.drawable.ic_round_warning).setMessage("Apakah Anda akan membuang data ${data.namanya} ini?").setPositiveButton("Ya"){ dialog,_->
//                    listHewan.removeAt(adapterPosition)
//                    Toast.makeText(context,"Hewan telah berhasil dihapus",Toast.LENGTH_SHORT).show()
//                    dialog.dismiss()
//                }
//                    .setNegativeButton("Tidak"){
//                        dialog,_->
//                        dialog.dismiss()
//                    }
//                    .create()
//                    .show()
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_animal, parent, false)
        val context = layoutInflater.context
        return viewHolder(view, context)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listHewan[position])
    }

    override fun getItemCount(): Int {
        return listHewan.size
    }

}