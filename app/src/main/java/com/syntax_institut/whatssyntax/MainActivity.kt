package com.syntax_institut.whatssyntax

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.syntax_institut.whatssyntax.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        bottomNav = binding.bottomNavigationView
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHost.navController)
        navController = navHost.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.chatDetailFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.statusDetailFragment -> binding.bottomNavigationView.visibility = View.GONE
                else -> binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                binding.fragmentContainerView.findNavController().navigateUp()
            }
        })

        addKeyboardVisibilityListener(this.window, onShowKeyboard = { keyboardHeight ->
            bottomNav.visibility = View.GONE
        }, onHideKeyboard = {
            bottomNav.visibility = View.VISIBLE
        })
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        binding.bottomNavigationView.setupWithNavController(navController)

    }

    private fun addKeyboardVisibilityListener(
        window: Window,
        onShowKeyboard: ((keyboardHeight: Int) -> Unit)? = null,
        onHideKeyboard: (() -> Unit)? = null
    ) {
        val rootView = window.decorView.findViewById<View>(android.R.id.content)
        var wasOpened = false
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            rootView.getWindowVisibleDisplayFrame(r)
            val screenHeight = rootView.rootView.height

            val keyboardHeight = screenHeight - r.bottom

            if (keyboardHeight > screenHeight * 0.15) {
                if (!wasOpened) {
                    wasOpened = true
                    onShowKeyboard?.invoke(keyboardHeight)
                }
            } else {
                if (wasOpened) {
                    wasOpened = false
                    onHideKeyboard?.invoke()
                }
            }
        }
    }
}