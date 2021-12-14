package com.example.finalproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.finalproject.databinding.FragmentHomeBinding
import com.example.finalproject.databinding.FragmentLightsBinding
import com.example.finalproject.databinding.FragmentLightsListBinding
import com.example.finalproject.databinding.FragmentMediaListBinding
import com.google.gson.Gson

const val ip = "http://10.22.101.248"


/**
 * A fragment representing a list of Items.
 */
class mediaFragment : Fragment() {
    private lateinit var requestQueue: RequestQueue
    private lateinit var binding: FragmentMediaListBinding
    private lateinit var adapter: MyMediaRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMediaListBinding.inflate(layoutInflater)

        requestQueue = Volley.newRequestQueue(this.context)

        //set ip address for where the smart home is running
        var url = "$ip/media-players"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { name ->
                val gson = Gson()
                val media = gson.fromJson<ArrayResult<media_players>>(name).result

                adapter = MyMediaRecyclerViewAdapter()
                binding.list.adapter = adapter
                binding.list.layoutManager = LinearLayoutManager(context)

                adapter.submitList(media)



                adapter.onClick = {
                    it.isPlaying = !it.isPlaying

                    if(it.isPlaying) {
                        val turnOnRequest = StringRequestWithBody(
                            url + "/play?id=${it.id}&songId=${it.nowPlayingSongId}",
                            it,
                            {},
                            {})
                        Log.i("url: ", turnOnRequest.toString())
                        turnOnRequest.tag = this
                        requestQueue.add(turnOnRequest)
                    }
                    if(!it.isPlaying){
                        val turnOffRequest = StringRequestWithBody(
                            url + "/pause?id=${it.id}",
                            it,
                            {},
                            {})
                        Log.i("url: ", turnOffRequest.toString())
                        turnOffRequest.tag = this
                        requestQueue.add(turnOffRequest)
                    }

                }

                adapter.prevPress = {
                    if(it.nowPlayingSongId == 0){
                        it.nowPlayingSongId = 6
                        val songPrevRequest = StringRequestWithBody(
                            "$ip/media-players/play?id=${it.id}&songId=${it.nowPlayingSongId}",
                            it,
                            {},
                            {}
                        )
                        songPrevRequest.tag = this
                        requestQueue.add(songPrevRequest)
                    }else{
                        it.nowPlayingSongId -= 1
                        val songPrevRequest = StringRequestWithBody(
                            "$ip/media-players/play?id=${it.id}&songId=${it.nowPlayingSongId}",
                            it,
                            {},
                            {}
                        )
                        songPrevRequest.tag = this
                        requestQueue.add(songPrevRequest)
                    }
                }
                adapter.skipPress = {
                    if(it.nowPlayingSongId == 6){
                        it.nowPlayingSongId = 0
                        val songPrevRequest = StringRequestWithBody(
                            "$ip/media-players/play?id=${it.id}&songId=${it.nowPlayingSongId}",
                            it,
                            {},
                            {}
                        )
                        songPrevRequest.tag = this
                        requestQueue.add(songPrevRequest)
                    }else{
                        it.nowPlayingSongId += 1
                        val songPrevRequest = StringRequestWithBody(
                            "$ip/media-players/play?id=${it.id}&songId=${it.nowPlayingSongId}",
                            it,
                            {},
                            {}
                        )
                        songPrevRequest.tag = this
                        requestQueue.add(songPrevRequest)
                    }
                }

                Log.i("VOLLEY", "media players are loaded.")
            },
            {
                Log.e("VOLLEY", "Failed to load media players $it")
            }
        )
        stringRequest.tag = this
        requestQueue.add(stringRequest)


        return binding.root
    }
}

