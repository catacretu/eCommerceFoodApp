package com.example.ecommercefoodapp.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ecommercefoodapp.HomeActivity
import com.example.ecommercefoodapp.R
import com.example.ecommercefoodapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.concurrent.schedule

const val SPLASH_SCREEN_DURATION = 1500L

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
        setContent {
            SplashScreenCompose()
        }
        goToNextScreen()
    }

    private fun userLogged() = true

    private fun goToNextScreen() {
        if (userLogged()) {
            Timer().schedule(SPLASH_SCREEN_DURATION) {

                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            Timer().schedule(SPLASH_SCREEN_DURATION) {
//                val intent = Intent(this@MainActivity, AuthenticationActivity::class.java)
//                startActivity(intent)
                finish()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenCompose() {
    val typography = initializeFonts()
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (textMessage, topBox, bottomBox) = createRefs()

        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    0.55f to Color.Black,
                    0.8f to Color(0xFF148C14),
                    1.0f to Color.White
                )
            )
            .constrainAs(topBox) {
                start.linkTo(parent.start)
                bottom.linkTo(textMessage.top)
            })

        Text(text = "Hello composers",
            style = typography.body1,
            modifier = Modifier
                .padding(top = 25.dp)
                .constrainAs(textMessage) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    0.05f to Color.White,
                    0.2f to Color(0xFF148C14),
                    0.43f to Color.Black
                )
            )
            .constrainAs(bottomBox) {
                top.linkTo(textMessage.bottom)
                start.linkTo(parent.start)
            })
    }
}

fun initializeFonts(): androidx.compose.material.Typography {
    val fonts = FontFamily(
        Font(R.font.allerta_stencil_regular)
    )
    val typography = androidx.compose.material.Typography(
        body1 = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Normal,
            fontSize = 40.sp,
            color = Color(0xFF148C14)
        )
    )
    return typography
}