package ir.ehsan.animatedmenu.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ir.ehsan.animatedmenu.State
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <T> AnimatedMenu(
    open: Boolean,
    items: List<T>,
    openDuration: Int = 300,
    itemsDuration: Int = 150,
    itemsAnimDelay:Long = (openDuration-100).toLong(),
    itemSize: Int = 30,
    parentHeight: Int = itemSize + 20,
    menuEnterAnim: EnterTransition = scaleIn(tween(openDuration)),
    menuExitAnim: ExitTransition = scaleOut(tween(openDuration)),
    backgroundColor: Color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
    bind: @Composable (Int, T, Modifier) -> Unit
) {
    AnimatedVisibility(visible = open, enter = menuEnterAnim, exit = menuExitAnim) {
        Row(
            modifier = Modifier
                .clip(CircleShape)
                .background(backgroundColor)
                .size(height = parentHeight.dp, width = (items.count() * (itemSize + 16)).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            items.forEachIndexed { index, item ->
                var size by State(0.dp)
                val animDuration =
                    (items.count() * itemsDuration) - (((items.count() - 1) - index) * itemsDuration)

                val sizeAnimate =
                    animateDpAsState(targetValue = size, animationSpec = tween(animDuration))
                LaunchedEffect(open) {
                    delay(itemsAnimDelay)
                    size = itemSize.dp
                }
                bind(index, item, Modifier.size(sizeAnimate.value))

                Spacer(modifier = Modifier.width(10.dp))

            }
        }
    }
}