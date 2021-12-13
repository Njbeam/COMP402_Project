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
        val url = "http://10.37.112.87/media-players"

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

                    Log.i("ID: ", it.id.toString())
                    Log.i("NAME: ", it.name)
                    Log.i("TYPE: ", it.type)
                    Log.i("iSPLAYING: ", it.isPlaying.toString())
                    Log.i("NOWPLAYINGSONGID: ", it.nowPlayingSongId.toString())
                    Log.i("CURRENTTIMESECONDS: ", it.currentTimeSeconds.toString())

                    val turnOnRequest = StringRequestWithBody(url + "?id=${it.id}", it, {}, {})
                    turnOnRequest.tag = this
                    requestQueue.add(turnOnRequest)
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

