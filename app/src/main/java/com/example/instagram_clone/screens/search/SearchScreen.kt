package com.example.instagram_clone.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instagram_clone.R
import com.example.instagram_clone.components.CustomInputField
import com.example.instagram_clone.components.DrawableButton
import com.example.instagram_clone.components.LazyStaggeredGrid
import com.example.instagram_clone.components.PostGridItem
import com.example.instagram_clone.ui.theme.Blue
import com.example.instagram_clone.ui.theme.Dimension
import com.example.instagram_clone.ui.theme.Gray

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.spacedBy(Dimension.sm),
    ) {
        val searchQuery by remember { searchViewModel.lastSearchQuery }
        val searchFilters = remember { searchViewModel.filters }
        val activeFilters = searchViewModel.activeFilters
        val trendingPosts = searchViewModel.trendingPosts
//        val trendingPostsUiState = searchViewModel.trendingPostsUiState

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp.div(2)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimension.pagePadding.div(2)),
        ) {
            CustomInputField(
                modifier = Modifier.weight(1f),
                value = searchQuery,
                placeholder = "Search",
                textStyle = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium),
                imeAction = ImeAction.Search,
                backgroundColor = Gray,
                padding = PaddingValues(Dimension.pagePadding.div(2)),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "search icon",
                        modifier = Modifier.size(Dimension.smIcon.times(0.7f)),
                        tint = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                    )
                },
                onValueChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onFocusChange = {
                },
                onKeyboardActionClicked = {
                },
            )
            DrawableButton(
                painter = painterResource(id = R.drawable.ic_scan),
                backgroundColor = MaterialTheme.colors.background,
                iconTint = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                onButtonClicked = {
                },
                iconSize = Dimension.smIcon
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
            horizontalArrangement = Arrangement.spacedBy(Dimension.pagePadding.div(2)),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = Dimension.pagePadding.div(2))
        ) {
            items(searchFilters) { filter ->
                SearchFilterLayout(
                    name = stringResource(id = filter.name),
                    icon = filter.icon,
                    isActive = activeFilters.contains(filter.id),
                    onActiveChange = {
                        searchViewModel.updateActiveFilters(id = filter.id)
                    },
                )
            }
        }

        LazyStaggeredGrid(
            columnCount = 3,
            contentPadding = PaddingValues(1.dp)
        ) {
            trendingPosts.shuffled().forEach {
                item {
                    PostGridItem(
                        modifier = Modifier.padding(1.dp).fillMaxWidth(),
                        cover = it.images.first(),
                        imagesCount = it.images.size,
                        onPostClicked = {
                                        //nothing happens
                             },
                    )
                }
            }
        }
    }
}

@Composable
fun SearchFilterLayout(name: String, icon: Int?, isActive: Boolean, onActiveChange: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .background(
                if (isActive) Blue
                else MaterialTheme.colors.background
            )
            .border(
                width = 1.dp,
                color = if (isActive) Blue
                else MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                shape = MaterialTheme.shapes.small
            )
            .clickable(
                onClick = onActiveChange,
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            )
            .padding(horizontal = 16.dp.div(2), vertical = Dimension.xs),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = "search filter",
                modifier = Modifier.size(10.dp.times(0.5f)),
                tint = if (isActive) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground.copy(
                    alpha = 1f),
            )
        }
        Text(
            text = name,
            style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.Medium),
            color = if (isActive) MaterialTheme.colors.onPrimary
            else MaterialTheme.colors.onBackground.copy(alpha = 1f),
        )
   }
}
