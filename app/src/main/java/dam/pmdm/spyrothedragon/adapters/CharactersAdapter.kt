package dam.pmdm.spyrothedragon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import dam.pmdm.spyrothedragon.R
import dam.pmdm.spyrothedragon.RiptoMagicView
import dam.pmdm.spyrothedragon.models.Character

class CharactersAdapter(
    private val list: List<Character>,
) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    private val characterImages = mapOf(
        "spyro" to R.drawable.spyro,
        "hunter" to R.drawable.hunter,
        "elora" to R.drawable.elora,
        "ripto" to R.drawable.ripto
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview, parent, false)
        return CharactersViewHolder(view)
    }
    /*Modificación de la función onBindViewHolder para añadir el efecto "mágico" de glow en el
     personaje de Ripto. */
    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {

        val character = list[position]
        holder.nameTextView.text = character.name

        val drawableRes = characterImages[character.image] ?: R.drawable.placeholder
        holder.imageImageView.setImageResource(drawableRes)

        holder.itemView.setOnClickListener {
            val container = holder.itemView.findViewById<FrameLayout>(R.id.imageContainer)

            // Buscar si ya hay RiptoMagicView
            val existingMagic = container.children
                .filterIsInstance<RiptoMagicView>()
                .firstOrNull()

            if (existingMagic != null) {
                // Detener animación y remover
                existingMagic.stopMagic()
                container.removeView(existingMagic)
            } else {
                // Crear y añadir la animación
                val magicView =
                    RiptoMagicView(holder.itemView.context, holder.imageImageView, 0.20f, 0.35f)
                container.addView(
                    magicView,
                    FrameLayout.LayoutParams(
                        holder.imageImageView.width,
                        holder.imageImageView.height
                    )
                )
                magicView.startMagic()
            }

        }
    }

    override fun getItemCount(): Int = list.size

    class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val imageImageView: ImageView = itemView.findViewById(R.id.image)
    }
}
