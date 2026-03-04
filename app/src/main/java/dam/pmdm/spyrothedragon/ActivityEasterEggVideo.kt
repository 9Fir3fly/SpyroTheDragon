package dam.pmdm.spyrothedragon

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dam.pmdm.spyrothedragon.databinding.ActivityEasterEggVideoBinding
//ACTIVITY PARA AÑADIR EL VIDEO DEL EASTER EGG
class ActivityEasterEggVideo : AppCompatActivity() {

    private lateinit var binding: ActivityEasterEggVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityEasterEggVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Para que se vea a "pantalla completa"
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        val path = "android.resource://dam.pmdm.spyrothedragon/${R.raw.spyro_easter_egg}"

        //Para asegurarnso de que el video esté listo y que se vea con "post"
        binding.videoView.post {
            binding.videoView.setVideoPath(path)
            binding.videoView.start()
        }

        binding.videoView.setOnCompletionListener {
            finish()
        }
    }
}