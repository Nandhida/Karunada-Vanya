package com.example.karunada_vanya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class GuideFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_guide, container, false)
        val container = view.findViewById<LinearLayout>(R.id.guideContainer)

        val guides = listOf(
            Triple("🐘", "Elephant in your field",
                "1. DO NOT run — elephants chase movement.\n" +
                        "2. Stay calm and move away slowly sideways.\n" +
                        "3. Hide behind a large tree or structure.\n" +
                        "4. Make loud noise only as a last resort.\n" +
                        "5. Call Forest Dept: 1926 immediately.\n" +
                        "6. Never stand between a mother and her calf.\n" +
                        "7. Do not use torches at night — it angers them."),

            Triple("🐆", "Leopard sighting near home",
                "1. Go inside your house immediately.\n" +
                        "2. Keep children and pets indoors.\n" +
                        "3. Do not corner or block its escape path.\n" +
                        "4. Leopards rarely attack unless provoked.\n" +
                        "5. Call Forest Dept: 1926 or local ranger.\n" +
                        "6. Warn neighbours using Movement Alert.\n" +
                        "7. Install lights around your home compound."),

            Triple("🐯", "Tiger attack prevention",
                "1. Never walk alone in forest areas at dawn/dusk.\n" +
                        "2. Make noise while walking — tigers avoid humans.\n" +
                        "3. If you see a tiger, stand tall and back away slowly.\n" +
                        "4. Do NOT run — tigers instinctively chase.\n" +
                        "5. Face the tiger at all times, never turn your back.\n" +
                        "6. Travel in groups when near forest borders.\n" +
                        "7. Report sightings to: Karnataka Forest Dept 1926."),

            Triple("🐗", "Wild Boar in crops",
                "1. Use noise makers around your field at night.\n" +
                        "2. Install solar-powered lights as deterrent.\n" +
                        "3. Plant crops that boars dislike on the border.\n" +
                        "4. Report repeated raids to the Forest Department.\n" +
                        "5. Community fencing is effective — talk to your GP.\n" +
                        "6. Never approach a boar with piglets — very dangerous.\n" +
                        "7. Use chilli-based sprays around the field boundary."),

            Triple("📞", "Emergency contacts",
                "🌲 Karnataka Forest Department: 1926\n\n" +
                        "🚔 Police Emergency: 100\n\n" +
                        "🏥 Ambulance: 108\n\n" +
                        "🐘 Wildlife SOS: 1800-200-9453\n\n" +
                        "📍 Report online: aranya.gov.in\n\n" +
                        "Save these numbers in your phone now!"),

            Triple("🤝", "Living with wildlife",
                "1. Never leave food waste outside — it attracts animals.\n" +
                        "2. Build proper fences around your livestock area.\n" +
                        "3. Join your village Wildlife Protection Committee.\n" +
                        "4. Teach children to respect — not fear — wildlife.\n" +
                        "5. Healthy forests mean healthy farms — protect trees.\n" +
                        "6. Report poaching immediately to Forest Dept: 1926.\n" +
                        "7. Remember: we share this land with them.")
        )

        guides.forEach { (emoji, title, content) ->
            val cardView = inflater.inflate(R.layout.item_guide_card, container, false)

            val txtEmoji = cardView.findViewById<TextView>(R.id.txtEmoji)
            val txtTitle = cardView.findViewById<TextView>(R.id.txtTitle)
            val txtContent = cardView.findViewById<TextView>(R.id.txtContent)
            val txtArrow = cardView.findViewById<TextView>(R.id.txtArrow)
            val header = cardView.findViewById<LinearLayout>(R.id.cardHeader)

            txtEmoji.text = emoji
            txtTitle.text = title
            txtContent.text = content

            header.setOnClickListener {
                if (txtContent.visibility == View.VISIBLE) {
                    txtContent.visibility = View.GONE
                    txtArrow.text = "▼"
                } else {
                    txtContent.visibility = View.VISIBLE
                    txtArrow.text = "▲"
                }
            }

            container.addView(cardView)
        }

        return view
    }
}