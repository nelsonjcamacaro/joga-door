package com.example.joga_door.datas

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.joga_door.R
import com.example.joga_door.databinding.CardViewBinding

class PlayersAdapter(private val playersList:List<Players>,private val onClickListener:(Players)->Unit):RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {

/*    interface OnPlayerClickListener{
        fun onViewClick
    }*/

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val binding = CardViewBinding.bind(itemView)
        private val name = binding.tvNameOfList
        private val club = binding.tvClubOfList
        private val photo = binding.ivOfList
        private val nationality = binding.tvAgeOfList
        fun bind(player:Players, onClickListener:(Players)->Unit){
            name.text = "Nombre: ${player.name}"
            club.text = "Club: ${player.club}"
            nationality.text = "Pais: ${player.nationality}"
            val into = Glide.with(photo.context).load(player.photo).into(photo)
            itemView.setOnClickListener{onClickListener(player)}
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = playersList[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return playersList.size
    }
}