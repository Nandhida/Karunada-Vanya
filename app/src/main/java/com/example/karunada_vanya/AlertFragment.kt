package com.example.karunada_vanya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AlertFragment : Fragment() {

    private lateinit var spinnerAnimal: Spinner
    private lateinit var editLocation: EditText
    private lateinit var btnSend: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlertAdapter
    private val database = FirebaseDatabase.getInstance()
    private val alertsRef = database.getReference("alerts")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_alert, container, false)

        spinnerAnimal = view.findViewById(R.id.spinnerAnimal)
        editLocation = view.findViewById(R.id.editLocation)
        btnSend = view.findViewById(R.id.btnSendAlert)
        recyclerView = view.findViewById(R.id.alertRecyclerView)

        val animals = listOf("Elephant", "Leopard", "Tiger", "Wild Boar", "Other")
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            animals
        )
        spinnerAnimal.adapter = spinnerAdapter

        adapter = AlertAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        btnSend.setOnClickListener { sendAlert() }

        listenForAlerts()

        return view
    }

    private fun sendAlert() {
        val animal = spinnerAnimal.selectedItem.toString()
        val location = editLocation.text.toString().trim()

        if (location.isEmpty()) {
            Toast.makeText(context, "Please enter your location!", Toast.LENGTH_SHORT).show()
            return
        }

        val alertId = alertsRef.push().key ?: return
        val alert = AlertItem(
            id = alertId,
            animal = animal,
            location = location,
            timestamp = System.currentTimeMillis()
        )

        alertsRef.child(alertId).setValue(alert)
            .addOnSuccessListener {
                Toast.makeText(context, "✅ Alert sent to community!", Toast.LENGTH_SHORT).show()
                editLocation.text.clear()
            }
            .addOnFailureListener {
                Toast.makeText(context, "❌ Failed to send alert", Toast.LENGTH_SHORT).show()
            }
    }

    private fun listenForAlerts() {
        val sixHoursMs = 6 * 60 * 60 * 1000L
        val cutoff = System.currentTimeMillis() - sixHoursMs

        alertsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val activeAlerts = mutableListOf<AlertItem>()
                for (child in snapshot.children) {
                    val alert = child.getValue(AlertItem::class.java) ?: continue
                    if (alert.timestamp >= cutoff) {
                        activeAlerts.add(alert)
                    } else {
                        child.ref.removeValue()
                    }
                }
                activeAlerts.sortByDescending { it.timestamp }
                adapter.updateAlerts(activeAlerts)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error loading alerts", Toast.LENGTH_SHORT).show()
            }
        })
    }
}