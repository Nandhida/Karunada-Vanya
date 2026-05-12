package com.example.karunada_vanya

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WikiFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WildlifeAdapter
    private lateinit var btnAll: Button
    private lateinit var btnAnimals: Button
    private lateinit var btnBirds: Button
    private lateinit var btnPlants: Button
    private val allItems = mutableListOf<WildlifeItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wiki, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        btnAll = view.findViewById(R.id.btnAll)
        btnAnimals = view.findViewById(R.id.btnAnimals)
        btnBirds = view.findViewById(R.id.btnBirds)
        btnPlants = view.findViewById(R.id.btnPlants)

        recyclerView.layoutManager = LinearLayoutManager(context)

        if (allItems.isEmpty()) {
            loadData()
        }

        adapter = WildlifeAdapter(requireContext(), allItems)
        recyclerView.adapter = adapter

        setupFilters()

        return view
    }

    private fun loadData() {
        allItems.add(WildlifeItem(
            "Black Panther", "Animal",
            "Karnataka's most elusive big cat",
            "The Black Panther is a melanistic leopard. Nagarhole and Kabini forests are among the best places in the world to spot them. They are solitary nocturnal hunters.",
            R.drawable.panther
        ))
        allItems.add(WildlifeItem(
            "Bengal Tiger", "Animal",
            "India's national animal",
            "Karnataka hosts over 500 Bengal Tigers in Bandipur and Nagarhole Tiger Reserves. A single tiger can have a territory of over 100 sq km.",
            R.drawable.tiger
        ))
        allItems.add(WildlifeItem(
            "Indian Elephant", "Animal",
            "Largest land animal in Asia",
            "Karnataka has the highest elephant population in India. They are vital for forest ecosystems, creating pathways and dispersing seeds across vast areas.",
            R.drawable.elephant
        ))
        allItems.add(WildlifeItem(
            "Great Hornbill", "Bird",
            "Nature's fruit planter",
            "The Great Hornbill is Karnataka's state bird. Its massive yellow beak with a casque on top is used for fighting and attracting mates. It can live up to 50 years.",
            R.drawable.hornbill
        ))
        allItems.add(WildlifeItem(
            "Indian Peacock", "Bird",
            "India's national bird",
            "The Indian Peacock is famous for its spectacular iridescent tail feathers. The male dances during monsoon to attract females.",
            R.drawable.peacock
        ))
        allItems.add(WildlifeItem(
            "Sandalwood Tree", "Plant",
            "Karnataka's fragrant gold",
            "Indian Sandalwood is one of the most valuable trees in the world. Karnataka produces the finest quality. It takes 30+ years to mature.",
            R.drawable.sandalwood
        ))
    }

    private fun setupFilters() {
        btnAll.setOnClickListener {
            adapter.updateList(allItems)
            setActive(btnAll)
        }
        btnAnimals.setOnClickListener {
            adapter.updateList(allItems.filter { it.category == "Animal" })
            setActive(btnAnimals)
        }
        btnBirds.setOnClickListener {
            adapter.updateList(allItems.filter { it.category == "Bird" })
            setActive(btnBirds)
        }
        btnPlants.setOnClickListener {
            adapter.updateList(allItems.filter { it.category == "Plant" })
            setActive(btnPlants)
        }
    }

    private fun setActive(active: Button) {
        listOf(btnAll, btnAnimals, btnBirds, btnPlants).forEach {
            it.backgroundTintList =
                android.content.res.ColorStateList.valueOf(Color.WHITE)
            it.setTextColor(Color.parseColor("#2D6A4F"))
        }
        active.backgroundTintList =
            android.content.res.ColorStateList.valueOf(Color.parseColor("#2D6A4F"))
        active.setTextColor(Color.WHITE)
    }
}