package pl.fanfatal.swipecontrollerdemo

import android.graphics.Canvas
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var mAdapter: PlayersDataAdapter? = null
    internal var swipeController: SwipeController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPlayersDataAdapter()
        setupRecyclerView()
    }

    private fun setPlayersDataAdapter() {
        val players = ArrayList<Player>()
        try {
            val `is` = InputStreamReader(assets.open("players.csv"))

            val reader = BufferedReader(`is`)
            reader.readLine()
            var line: String?
            var st: Array<String>
            line = reader.readLine()
            while (line != null) {
                st = line.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                val player = Player()
                player.name = st[0]
                player.nationality = st[1]
                player.club = st[4]
                player.rating = Integer.parseInt(st[9])
                player.age = Integer.parseInt(st[14])
                players.add(player)
                line = reader.readLine()
            }
        } catch (e: IOException) {

        }

        mAdapter = PlayersDataAdapter(players)
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = mAdapter

        swipeController = SwipeController(object : SwipeControllerActions() {
            override fun onRightClicked(position: Int) {
                mAdapter!!.players.removeAt(position)
                mAdapter!!.notifyItemRemoved(position)
                mAdapter!!.notifyItemRangeChanged(position, mAdapter!!.itemCount)
            }
        })

        val itemTouchhelper = ItemTouchHelper(swipeController)
        itemTouchhelper.attachToRecyclerView(recyclerView)

        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
                swipeController!!.onDraw(c)
            }
        })
    }
}
