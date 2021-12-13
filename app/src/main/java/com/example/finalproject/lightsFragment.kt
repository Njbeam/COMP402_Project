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
import com.google.gson.Gson

/**
 * A fragment representing a list of Items.
 */
class lightsFragment : Fragment() {
    private lateinit var requestQueue: RequestQueue
    private lateinit var binding: FragmentLightsListBinding
    private lateinit var adapter: MylightsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLightsListBinding.inflate(layoutInflater)

        requestQueue = Volley.newRequestQueue(this.context)

        //set ip address for where the smart home is running
        val url = "http://10.20.105.247/lights"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { name ->
                val gson = Gson()
                val lights = gson.fromJson<ArrayResult<lights>>(name).result

                adapter = MylightsRecyclerViewAdapter()
                binding.list.adapter = adapter
                binding.list.layoutManager = LinearLayoutManager(context)

                adapter.submitList(lights)

                adapter.onClick = {
                    it.isOn = !it.isOn

                    val turnOnRequest = StringRequestWithBody(url + "?id=${it.id}", it, {}, {})
                    turnOnRequest.tag = this
                    requestQueue.add(turnOnRequest)
                }

                Log.i("VOLLEY", "lights are loaded.")
            },
            {
                Log.e("VOLLEY", "Failed to load lights $it")
            }
        )
        stringRequest.tag = this
        requestQueue.add(stringRequest)

        return binding.root
    }
}

