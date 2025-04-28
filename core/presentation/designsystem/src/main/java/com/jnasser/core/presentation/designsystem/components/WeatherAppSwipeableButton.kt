import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jnasser.core.presentation.designsystem.R
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import kotlin.math.roundToInt

enum class ConfirmationState {
    Default, Confirmed
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherAppSwipeableButton(
    modifier: Modifier = Modifier,
    initialState: Boolean = true,
    buttonText: String,
    buttonTextAlternative: String? = null,
    draggableIconActive: @Composable () -> Unit,
    draggableIconInactive: @Composable () -> Unit,
    onComplete: () -> Unit
) {
    val width = 140.dp
    val dragSize = 50.dp
    val density = LocalDensity.current

    val pxDistance = with(density) { (width - dragSize).toPx() }

    val state = remember {
        AnchoredDraggableState(
            initialValue = if(initialState) ConfirmationState.Default else ConfirmationState.Confirmed,
            anchors = DraggableAnchors {
                ConfirmationState.Default at 0f
                ConfirmationState.Confirmed at pxDistance
            },
            positionalThreshold = { totalDistance -> totalDistance * 0.5f },
            velocityThreshold = { with(density) { 125.dp.toPx() } },
            snapAnimationSpec = spring(),
            decayAnimationSpec = exponentialDecay()
        )
    }

    val progress = state.offset / pxDistance

    ConstraintLayout(
        modifier = modifier
            .width(width)
            .anchoredDraggable(
                state = state,
                orientation = Orientation.Horizontal
            )
            .background(Color(0x6641588A), RoundedCornerShape(dragSize))
    ) {
        val (text, draggable) = createRefs()

        val isComplete = state.currentValue == ConfirmationState.Confirmed

        Text(
            modifier = Modifier.constrainAs(text) {
                if(isComplete) {
                    start.linkTo(parent.start, margin = 15.dp)
                } else {
                    end.linkTo(parent.end, margin = 15.dp)
                    start.linkTo(draggable.end)
                }
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            text = if(isComplete) buttonTextAlternative.orEmpty() else buttonText,
            style = MaterialTheme.typography.labelLarge
        )

        DraggableControl(
            modifier = Modifier
                .constrainAs(draggable) {
                    if(!isComplete) start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .offset {
                    IntOffset(
                        state
                            .requireOffset()
                            .roundToInt(), 0
                    )
                }
                .size(dragSize),
            progress = progress,
            draggableIconActive = draggableIconActive,
            draggableIconInactive = draggableIconInactive,
            onComplete = {
                if (state.currentValue == ConfirmationState.Confirmed) {
                    onComplete()
                }
            }
        )
    }
}

@Composable
private fun DraggableControl(
    modifier: Modifier,
    progress: Float,
    draggableIconActive: @Composable () -> Unit,
    draggableIconInactive: @Composable () -> Unit,
    onComplete: () -> Unit
) {
    Box(
        modifier
            .padding(4.dp)
            .shadow(elevation = 2.dp, shape = CircleShape, clip = false)
            .background(MaterialTheme.colorScheme.onSurface, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        val isConfirmed = progress >= 0.8f
        Crossfade(targetState = isConfirmed) {
            if (it) {
                draggableIconActive()
                onComplete()
            } else {
                draggableIconInactive()
            }
        }
    }
}

@Preview
@Composable
private fun WeatherAppSwipeableButtonPreview() {
    WeatherAppTheme {
        WeatherAppSwipeableButton(
            buttonText = stringResource(R.string.follow_up),
            initialState = true,
            buttonTextAlternative = stringResource(R.string.unfollow_up),
            draggableIconActive = {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = Color(0x6641588A)
                )
            },
            draggableIconInactive = {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = null,
                    tint = Color(0x6641588A)
                )
            }
        ) {}
    }
}
