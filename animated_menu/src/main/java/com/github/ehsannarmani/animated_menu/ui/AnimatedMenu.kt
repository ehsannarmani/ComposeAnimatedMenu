package com.github.ehsannarmani.animated_menu.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <T> AnimatedMenu(
    open: Boolean,
    items: List<T>,
    openDuration: Int = 300,
    itemsDuration: Int = 150,
    itemsAnimDelay: Long = (openDuration - 100).toLong(),
    itemSize: Int = 30,
    parentSize: Int = itemSize + 20,
    menuEnterAnim: EnterTransition = scaleIn(tween(openDuration)),
    menuExitAnim: ExitTransition = scaleOut(tween(openDuration)),
    backgroundColor: Color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
    direction: MenuDirection = MenuDirection.Horizontal,
    itemBuilder: @Composable (Int, T, Modifier) -> Unit
) {
    val parentModifier = Modifier
        .clip(CircleShape)
        .background(backgroundColor)
        .size(
            height = if (direction == MenuDirection.Horizontal) parentSize.dp else (items.count() * (itemSize + 16)).dp,
            width = if (direction == MenuDirection.Horizontal) (items.count() * (itemSize + 16)).dp else parentSize.dp
        )

    val content: @Composable (index: Int, item: T) -> Unit = { index, item ->
        var size by remember {
            mutableStateOf(0.dp)
        }
        val animDuration =
            (items.count() * itemsDuration) - (((items.count() - 1) - index) * itemsDuration)

        val sizeAnimate =
            animateDpAsState(targetValue = size, animationSpec = tween(animDuration))
        LaunchedEffect(open) {
            delay(itemsAnimDelay)
            size = itemSize.dp
        }
        itemBuilder(index, item, Modifier.size(sizeAnimate.value))

    }
    AnimatedVisibility(visible = open, enter = menuEnterAnim, exit = menuExitAnim) {
        if (direction == MenuDirection.Horizontal) {
            Row(
                modifier = parentModifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                items.forEachIndexed { index, item ->
                    content(index, item)
                    Spacer(modifier = Modifier.width(10.dp))

                }
            }
        } else {
            Column(
                modifier = parentModifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items.forEachIndexed { index, item ->
                    content(index, item)
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }
    }
}

enum class MenuDirection { Vertical, Horizontal }