package com.example.karunada_vanya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.TimeUnit

class AlertAdapter(private var alerts: List<AlertItem>) :
    RecyclerView.Adapter<AlertAdapter.ViewHolder>() {

    fun updateAlerts(newAlerts: List<AlertItem>) {
        alerts = newAlerts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alert_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val alert = alerts[position]

        val emoji = when (alert.animal) {
            "Elephant" -> "🐘"
            "Leopard" -> "🐆"
            "Tiger" -> "🐯"
            "Wild Boar" -> "🐗"
            else -> "🦁"
        }

        holder.txtAnimal.text = "$emoji ${alert.animal} spotted!"
        holder.txtLocation.text = "📍 ${alert.location}"

        val now = System.currentTimeMillis()
        val diffMs = now - alert.timestamp
        val hoursAgo = TimeUnit.MILLISECONDS.toHours(diffMs)
        val minutesAgo = TimeUnit.MILLISECONDS.toMinutes(diffMs)
        val hoursLeft = 6 - hoursAgo

        holder.txtTime.text = if (hoursAgo < 1) "$minutesAgo mins ago"
        else "$hoursAgo hrs ago"
        holder.txtExpiry.text = "⏱ Expires in $hoursLeft hr(s)"
    }

    override fun getItemCount() = alerts.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtAnimal: TextView = itemView.findViewById(R.id.txtAnimalAlert)
        val txtLocation: TextView = itemView.findViewById(R.id.txtLocationAlert)
        val txtTime: TextView = itemView.findViewById(R.id.txtTimeAlert)
        val txtExpiry: TextView = itemView.findViewById(R.id.txtExpiryAlert)
    }
}