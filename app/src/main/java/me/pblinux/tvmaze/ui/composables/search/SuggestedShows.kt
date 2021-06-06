package me.pblinux.tvmaze.ui.composables.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import me.pblinux.tvmaze.R
import me.pblinux.tvmaze.data.models.State
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.ui.composables.home.ShowItem
import me.pblinux.tvmaze.ui.composables.states.StateHandler

@Composable
fun SuggestedShows(state: State, onItemCLicked: (Show) -> Unit, onClick: () -> Unit) {
    StateHandler<List<Show>>(state = state, onClick = onClick) {
        Column(Modifier.fillMaxWidth()) {
            Text(
                stringResource(id = R.string.suggestions),
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(Modifier.height(16.dp))
            BoxWithConstraints {
                val posterSize = (this.maxWidth / 2) - 8.dp

                FlowRow(
                    mainAxisAlignment = FlowMainAxisAlignment.Start,
                    mainAxisSpacing = 16.dp,
                    crossAxisSpacing = 16.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    it.forEach { show ->
                        ShowItem(show = show, posterSize = posterSize) {
                            onItemCLicked(it)
                        }
                    }
                }
            }
        }
    }
}