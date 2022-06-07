package ir.ehsan.animatedmenu

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ehsan.animatedmenu.ui.components.AnimatedMenu
import ir.ehsan.animatedmenu.ui.theme.AnimatedMenuTheme
import ir.ehsan.animatedmenu.ui.theme.vazir
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedMenuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(vertical = 100.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var menuOpen by State(value = false)
                        Button(onClick = { menuOpen = !menuOpen }) {
                            Text(text = "فشارم بده", fontFamily = vazir)
                        }

                        val items = listOf(
                            R.drawable.like,
                            R.drawable.dislike,
                            R.drawable.heart,
                            R.drawable.thanks,
                            R.drawable.heart,
                        )

                        AnimatedMenu(
                            open = menuOpen,
                            items = items,
                            itemsDuration = 200
                        ) { index, item, modifier ->
                            Image(
                                modifier = modifier.clickable {
                                    menuOpen = false
                                },
                                painter = painterResource(id = item),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun <T> State(value: T) = remember {
    mutableStateOf(value)
}
