# Animated Menu For JetpackCompose
### An Animated Menu and material openable menu for JetpackCompose
<img src="https://github.com/ehsannarmani/ComposeAnimatedMenu/blob/master/media/gif.gif" width="250">
<hr/>

## Dependency

### Step 1: Add the JitPack repository to your build file
#### Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

## Step 2: Add the dependency
[![](https://jitpack.io/v/ehsannarmani/ComposeAnimatedMenu.svg)](https://jitpack.io/#ehsannarmani/ComposeAnimatedMenu)
```groovy
dependencies {
  implementation 'com.github.ehsannarmani:ComposeAnimatedMenu:latest_version'
}
```

<hr/>

## Usage

```kotlin
val items = listOf(
    R.drawable.like,
    R.drawable.dislike,
    R.drawable.heart,
    R.drawable.thanks,
    R.drawable.heart,
)
AnimatedMenu(
    open = true or false,
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
    direction = MenuDirection.Vertical,
    ...
) 
```
