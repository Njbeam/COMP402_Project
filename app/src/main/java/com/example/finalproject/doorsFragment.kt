package com.example.finalproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.finalproject.databinding.FragmentDoorsListBinding
import com.example.finalproject.databinding.FragmentLightsListBinding
import com.google.gson.Gson

/**
 * A simple [Fragment] subclass.
 * Use the [doorsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class doorsFragment : Fragment() {
    private lateinit var requestQueue: RequestQueue
    private lateinit var binding: FragmentDoorsListBinding
    private lateinit var adapter: MyDoorsRecyclerViewAdapter
    private val args: doorsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoorsListBinding.inflate(layoutInflater)

        requestQueue = Volley.newRequestQueue(this.context)
        val ip = args.ip
        //set ip address for where the smart home is running
        val url = "$ip/doors"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { name ->
                val gson = Gson()
                val lights = gson.fromJson<ArrayResult<doors>>(name).result

                adapter = MyDoorsRecyclerViewAdapter()
                binding.doorsList.adapter = adapter
                binding.doorsList.layoutManager = LinearLayoutManager(context)

                adapter.submitList(lights)

                adapter.onClick = {
                    it.isOpen = !it.isOpen

                    val turnOnRequest = StringRequestWithBody(url + "?id=${it.id}", it, {}, {})
                    turnOnRequest.tag = this
                    requestQueue.add(turnOnRequest)
                }

                Log.i("VOLLEY", "Doors are loaded.")
            },
            {
                Log.e("VOLLEY", "Failed to load Doors $it")
            }
        )
        stringRequest.tag = this
        requestQueue.add(stringRequest)

        return binding.root
    }

}