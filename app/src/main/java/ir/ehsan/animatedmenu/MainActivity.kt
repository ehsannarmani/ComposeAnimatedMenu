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
import com.github.ehsannarmani.animated_menu.ui.AnimatedMenu
import com.github.ehsannarmani.animated_menu.ui.MenuDirection
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
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 100.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var menuOpenHorizontal by State(value = false)
                        var menuOpenVertical by State(value = false)
                        val items = listOf(
                            R.drawable.like,
                            R.drawable.dislike,
                            R.drawable.heart,
                            R.drawable.thanks,
                            R.drawable.heart,
                        )

                        Box(modifier = Modifier.animateContentSize(tween(300)).weight(1f), contentAlignment = Alignment.Center){
                            Column(
                                modifier=Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(onClick = { menuOpenHorizontal = !menuOpenHorizontal }) {
                                    Text(text = "Call me! (Horizontal)", fontFamily = vazir)
                                }

                                AnimatedMenu(
                                    open = menuOpenHorizontal,
                                    items = items,
                                    itemsDuration = 200,
                                    menuEnterAnim = scaleIn() + fadeIn(),
                                    menuExitAnim = scaleOut() + fadeOut(),
                                    itemBuilder = { index, item, modifier ->
                                        Image(
                                            modifier = modifier.clickable {
                                                menuOpenHorizontal = false
                                            },
                                            painter = painterResource(id = item),
                                            contentDescription = null
                                        )
                                    },
                                    direction = MenuDirection.Horizontal
                                )
                            }
                        }

                        Box(modifier=Modifier.height(32.dp))

                        Box(modifier=Modifier.animateContentSize(tween(300)).weight(1f), contentAlignment = Alignment.Center){
                            Column(
                                modifier=Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Button(onClick = { menuOpenVertical = !menuOpenVertical }) {
                                    Text(text = "Call me! (Vertical)", fontFamily = vazir)
                                }

                                AnimatedMenu(
                                    open = menuOpenVertical,
                                    items = items,
                                    itemsDuration = 200,
                                    menuEnterAnim = scaleIn() + fadeIn(),
                                    menuExitAnim = scaleOut() + fadeOut(),
                                    itemBuilder = { index, item, modifier ->
                                        Image(
                                            modifier = modifier.clickable {
                                                menuOpenVertical = false
                                            },
                                            painter = painterResource(id = item),
                                            contentDescription = null
                                        )
                                    },
                                    direction = MenuDirection.Vertical
                                )
                            }
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
