package com.example.androidcoursehw

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidcoursehw.databinding.ActivityMainBinding
import java.util.*

private const val TAG_HOME = "tag_home"
private const val TAG_DASHBOARD = "tag_dashboard"
private const val TAG_SETTINGS = "tag_settings"
private const val TAG_TEST = "tag_test"

private var lastFragment = TAG_HOME

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        supportFragmentManager.beginTransaction().run {
            add(R.id.fragment_container, HomeFragment(), TAG_HOME)
            commit()
            binding.ivHome.setColorFilter(Color.parseColor("#FF6200EE"), PorterDuff.Mode.SRC_IN)
            binding.tvHome.setTextColor(Color.parseColor("#FF6200EE"))

        }

        binding.apply {
            homeButton.setOnClickListener {
                removeColor(lastFragment)
                supportFragmentManager.beginTransaction().apply {
                    setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right
                    )
                    replace(R.id.fragment_container, HomeFragment(), TAG_HOME)
                    addToBackStack(TAG_HOME)
                    commit()
                }
                binding.ivHome.setColorFilter(Color.parseColor("#FF6200EE"), PorterDuff.Mode.SRC_IN)
                binding.tvHome.setTextColor(Color.parseColor("#FF6200EE"))
                lastFragment = TAG_HOME
            }

            dashboardButton.setOnClickListener {
                removeColor(lastFragment)
                supportFragmentManager.beginTransaction().apply {
                    setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right
                    )
                    replace(R.id.fragment_container, DashboardFragment(), TAG_DASHBOARD)
                    addToBackStack(TAG_DASHBOARD)
                    commit()
                }
                binding.ivDashboard.setColorFilter(
                    Color.parseColor("#FF6200EE"),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tvDashboard.setTextColor(Color.parseColor("#FF6200EE"))
                lastFragment = TAG_DASHBOARD
            }

            settingsButton.setOnClickListener {
                removeColor(lastFragment)
                supportFragmentManager.beginTransaction().apply {
                    setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right
                    )
                    replace(R.id.fragment_container, SettingsFragment(), TAG_SETTINGS)
                    addToBackStack(TAG_SETTINGS)
                    commit()
                }
                binding.ivSettings.setColorFilter(
                    Color.parseColor("#FF6200EE"),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tvSettings.setTextColor(Color.parseColor("#FF6200EE"))
                lastFragment = TAG_SETTINGS
            }

            testButton.setOnClickListener {
                removeColor(lastFragment)
                supportFragmentManager.beginTransaction().apply {
                    setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right
                    )
                    replace(R.id.fragment_container, TestFragment(), TAG_TEST)
                    addToBackStack(TAG_TEST)
                    commit()
                }
                binding.ivTest.setColorFilter(Color.parseColor("#FF6200EE"), PorterDuff.Mode.SRC_IN)
                binding.tvTest.setTextColor(Color.parseColor("#FF6200EE"))
                lastFragment = TAG_TEST
            }
        }
    }

    private fun removeColor(fragmentTag: String) {
        when (fragmentTag) {
            TAG_HOME -> {
                binding.ivHome.setColorFilter(Color.parseColor("#F0000000"), PorterDuff.Mode.SRC_IN)
                binding.tvHome.setTextColor(Color.parseColor("#F0000000"))
            }
            TAG_DASHBOARD -> {
                binding.ivDashboard.setColorFilter(
                    Color.parseColor("#F0000000"),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tvDashboard.setTextColor(Color.parseColor("#F0000000"))
            }
            TAG_SETTINGS -> {
                binding.ivSettings.setColorFilter(
                    Color.parseColor("#F0000000"),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tvSettings.setTextColor(Color.parseColor("#F0000000"))
            }
            TAG_TEST -> {
                binding.ivTest.setColorFilter(Color.parseColor("#F0000000"), PorterDuff.Mode.SRC_IN)
                binding.tvTest.setTextColor(Color.parseColor("#F0000000"))
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}