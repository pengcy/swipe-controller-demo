package pl.fanfatal.swipecontrollerdemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

internal class PlayersDataAdapter(var players: ArrayList<Player>) : RecyclerView.Adapter<PlayersDataAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val name: TextView
         val nationality: TextView
         val club: TextView
         val rating: TextView
         val age: TextView

        init {
            name = view.findViewById(R.id.name) as TextView
            nationality = view.findViewById(R.id.nationality) as TextView
            club = view.findViewById(R.id.club) as TextView
            rating = view.findViewById(R.id.rating) as TextView
            age = view.findViewById(R.id.age) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.player_row, parent, false)

        return PlayerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.name.text = player.name
        holder.nationality.text = player.nationality
        holder.club.text = player.club
        holder.rating.text = player.rating!!.toString()
        holder.age.text = player.age!!.toString()
    }

    override fun getItemCount(): Int {
        return players.size
    }
}
