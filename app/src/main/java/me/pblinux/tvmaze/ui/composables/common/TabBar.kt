package me.pblinux.tvmaze.ui.composables.common

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.pblinux.tvmaze.data.models.episode.Episode
import me.pblinux.tvmaze.ui.screens.home.HomeTabs
import me.pblinux.tvmaze.ui.screens.home.Tabs
import me.pblinux.tvmaze.ui.theme.White

@Preview
@Composable
private fun TVTabBarPreview() {
    TVTabBar(tabs = Tabs, selectedTab = 1,
        onSearchPressed = {},
        onTabClicked = {
            Log.d("TVTabBar | Preview", it.toString())
        }
    )
}

@Composable
fun TVTabBar(
    tabs: List<HomeTabs>,
    selectedTab: Int,
    onSearchPressed: () -> Unit,
    onTabClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedColor = if (isSystemInDarkTheme()) White else MaterialTheme.colors.secondary
    val unselectedColor = if (isSystemInDarkTheme()) White.copy(alpha = 0.4f) else Color.Gray

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onSearchPressed) {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = "Search",
                tint = selectedColor
            )
        }
        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            backgroundColor = Color.Transparent,
            contentColor = Color.Transparent,
            divider = {},
            edgePadding = 32.dp
        ) {
            tabs.forEach {
                val selected = selectedTab == tabs.indexOf(it)

                Tab(
                    selected = selected,
                    onClick = { onTabClicked(tabs.indexOf(it)) },
                    text = {
                        Text(
                            text = it.title, style = MaterialTheme.typography.h5.copy(
                                color = if (selected) selectedColor else unselectedColor
                            )
                        )
                    },
                )
            }
        }
    }
}

@Composable
fun SeasonsTabBar(
    episodeInfo: Map<Int, List<Episode>>,
    selectedTab: Int,
    onTabClicked: (Int) -> Unit,
) {
    val selectedColor = if (isSystemInDarkTheme()) White else MaterialTheme.colors.secondary
    val unselectedColor = if (isSystemInDarkTheme()) White.copy(alpha = 0.4f) else Color.Gray

    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        backgroundColor = Color.Transparent,
        contentColor = Color.Transparent,
        divider = {},
        edgePadding = 8.dp
    ) {
        episodeInfo.onEachIndexed { index, entry ->
            val selected = index == selectedTab
            val title = if (entry.key > 100) "${entry.key}" else "Season ${entry.key}"

            Tab(selected = selected, onClick = { onTabClicked(index) }) {
                Text(
                    title, style = MaterialTheme.typography.subtitle2.copy(
                        color = if (selected) selectedColor else unselectedColor
                    )
                )
            }
        }
    }
}