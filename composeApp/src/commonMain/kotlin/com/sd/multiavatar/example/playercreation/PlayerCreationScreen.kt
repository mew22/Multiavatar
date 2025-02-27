package io.github.mew22.example.playercreation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder
import io.github.mew22.AvatarData
import io.github.mew22.MultiAvatar
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerCreationScreen(
    state: PlayerCreationState,
    dispatch: (PlayerCreationEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar { Text("Player Creation") }
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            PlayerAvatar(
                data = state.playerIcon,
                dispatch = dispatch,
            )
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            PlayerName(
                name = state.playerName,
                onChanged = { dispatch(PlayerCreationEvent.NameChanged(it)) },
            )
            Spacer(modifier = Modifier.weight(1f))
            val dialogState = remember { mutableStateOf(false) }
            Button(
                onClick = { dialogState.value = true }
            ) {
                Text("Create")
            }
            if (dialogState.value) {
                Dialog(onDismissRequest = { dialogState.value = false }) {
                    Text("Generated parts ${state.playerIcon}")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
private fun ColumnScope.PlayerAvatar(
    data: AvatarData,
    dispatch: (PlayerCreationEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val playerIconBytes = remember(data) { MultiAvatar.getAvatarSvgBytes(data) }
    AsyncImage(
        model = ImageRequest.Builder(context = LocalPlatformContext.current)
            .data(playerIconBytes)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(184.dp)
            .border(
                2.dp,
                Color(0xffD9D9D9),
                CircleShape
            )
            .clip(CircleShape)
            .clickable { dispatch(PlayerCreationEvent.AvatarClicked) },
        error = ColorPainter(Color(0xffBC1A3F)),
    )
    FlowRow(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxWidth(1f)
    ) {
        FilterChip(
            shape = CircleShape,
            selected = false,
            onClick = { dispatch(PlayerCreationEvent.AvatarBackgroundClicked) },
            content = { Text("Background") }
        )
        FilterChip(
            shape = CircleShape,
            selected = false,
            onClick = { dispatch(PlayerCreationEvent.AvatarClothesClicked) },
            content = { Text("Clothes") }
        )
        FilterChip(
            shape = CircleShape,
            selected = false,
            onClick = { dispatch(PlayerCreationEvent.AvatarHeadClicked) },
            content = { Text("Head") }
        )
        FilterChip(
            shape = CircleShape,
            selected = false,
            onClick = { dispatch(PlayerCreationEvent.AvatarMouthClicked) },
            content = { Text("Mouth") }
        )
        FilterChip(
            shape = CircleShape,
            selected = false,
            onClick = { dispatch(PlayerCreationEvent.AvatarEyesClicked) },
            content = { Text("Eyes") }
        )
        FilterChip(
            shape = CircleShape,
            selected = false,
            onClick = { dispatch(PlayerCreationEvent.AvatarTopClicked) },
            content = { Text("Top") }
        )
    }
}

@Composable
private fun PlayerName(
    name: String,
    onChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = name,
        onValueChange = onChanged,
        label = {
            Text(
                text = "Player name",
                style = MaterialTheme.typography.bodyLarge,
            )
        },
    )
}

@Preview
@Composable
private fun PlayerCreationScreenPreview() {
    PlayerCreationScreen(
        state = PlayerCreationState(),
        dispatch = {}
    )
}
