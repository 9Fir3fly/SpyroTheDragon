package dam.pmdm.spyrothedragon

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dam.pmdm.spyrothedragon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    // ---------------- TUTORIAL ----------------

    private var tutorialStep = 0
    private lateinit var currentOverlay: View
    private lateinit var btnSaltar: TextView
    private lateinit var soundPool: SoundPool
    private var soundSiguiente: Int = 0
    private var soundComenzarApp: Int = 0
    private var soundComenzarGuia: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val prefs = getSharedPreferences("app", MODE_PRIVATE)
        val tutorialShown = prefs.getBoolean("tutorialShown", false)

        if (!tutorialShown) {
            showWelcomeOverlay()
        }

        btnSaltar = TextView(this).apply {
            text = "Saltar tutorial"
            textSize = 14f
            setTextColor(getColor(R.color.purple))
            setOnClickListener {
                finalizarTutorial()
            }
        }

        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )

        params.gravity = Gravity.TOP or Gravity.END
        params.setMargins(0, 80, 40, 0)

        btnSaltar.layoutParams = params

        val navHostFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.navHostFragment)

        navHostFragment?.let {
            navController = NavHostFragment.findNavController(it)
            NavigationUI.setupWithNavController(binding.navView, navController!!)
            NavigationUI.setupActionBarWithNavController(this, navController!!)
        }

        binding.navView.setOnItemSelectedListener { menuItem ->
            selectedBottomMenu(menuItem)
        }

        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_characters,
                R.id.navigation_worlds,
                R.id.navigation_collectibles ->
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)

                else ->
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(5)
            .setAudioAttributes(audioAttributes)
            .build()

        soundSiguiente = soundPool.load(this, R.raw.btn_siguiente, 1)
        soundComenzarApp = soundPool.load(this, R.raw.comenzar_app, 1)
        soundComenzarGuia = soundPool.load(this, R.raw.comenzar_guia, 1)

    }

    // ---------------- BOTTOM NAV ----------------

    private fun selectedBottomMenu(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_characters ->
                navController?.navigate(R.id.navigation_characters)

            R.id.nav_worlds ->
                navController?.navigate(R.id.navigation_worlds)

            else ->
                navController?.navigate(R.id.navigation_collectibles)
        }
        return true
    }

    // ---------------- MENU INFO ----------------

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.about_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_info) {
            showInfoDialog()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showInfoDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.title_about)
            .setMessage(R.string.text_about)
            .setPositiveButton(R.string.accept, null)
            .show()
    }

    // ---------------- OVERLAY BIENVENIDA ----------------

    @SuppressLint("ClickableViewAccessibility")
    private fun showWelcomeOverlay() {

        val overlay = layoutInflater.inflate(R.layout.p1_guide_welcome, null)

        overlay.setOnTouchListener { _, _ -> true }

        addContentView(
            overlay,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        val startGuide = overlay.findViewById<View>(R.id.GifDiamanteBoton)

        startGuide.setOnClickListener {
            soundPool.play(soundComenzarGuia, 1f, 1f, 1, 0, 1f)
            startGuide.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(120)
                .withEndAction {
                    (overlay.parent as ViewGroup).removeView(overlay)

                    tutorialStep = 0
                    nextStepGuide()
                }
        }
    }

    // ---------------- SISTEMA DE PASOS ----------------

    @SuppressLint("SetTextI18n")
    private fun nextStepGuide() {

        val root = findViewById<ViewGroup>(android.R.id.content)

        if (::currentOverlay.isInitialized) {
            root.removeView(currentOverlay)
        }

        val layoutId = when (tutorialStep) {
            0 -> R.layout.p2_guide_characters
            1 -> R.layout.p3_guide_worlds
            2 -> R.layout.p4_guide_collectibles
            3 -> R.layout.p5_guide_info_button
            4 -> R.layout.p6_guide_resume
            else -> return
        }
        currentOverlay = layoutInflater.inflate(layoutId, root, false)
        root.addView(currentOverlay)

        if (btnSaltar.parent == null) {
            root.addView(btnSaltar)
        } else {
            btnSaltar.bringToFront()
        }

        if (tutorialStep == 4) {

            val btnComenzar = currentOverlay.findViewById<Button>(R.id.btn_Comenzar)

            btnComenzar?.setOnClickListener {
                soundPool.play(soundComenzarApp, 1f, 1f, 1, 0, 1f)

                finalizarTutorial()
            }
        }

        if (tutorialStep != 4) {

            val btnSiguiente = currentOverlay.findViewById<Button>(R.id.btn_siguiente)

            btnSiguiente?.setOnClickListener {
                soundPool.play(soundSiguiente, 1f, 1f, 1, 0, 1f)

                tutorialStep++
                nextStepGuide()
            }
        }

        animarOverlay(currentOverlay)

        when (tutorialStep) {
            0 -> navController?.navigate(R.id.navigation_characters)
            1 -> navController?.navigate(R.id.navigation_worlds)
            2 -> navController?.navigate(R.id.navigation_collectibles)
        }
    }

    private fun animarOverlay(view: View) {

        view.alpha = 0f
        view.scaleX = 0.8f
        view.scaleY = 0.8f

        view.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(300)
            .start()
    }

    private fun finalizarTutorial() {

        val root = findViewById<ViewGroup>(android.R.id.content)

        if (::currentOverlay.isInitialized) {
            root.removeView(currentOverlay)
        }

        if (btnSaltar.parent != null) {
            (btnSaltar.parent as ViewGroup).removeView(btnSaltar)
        }
        getSharedPreferences("app", MODE_PRIVATE)
            .edit {
                putBoolean("tutorialShown", true)
            }
    }
}