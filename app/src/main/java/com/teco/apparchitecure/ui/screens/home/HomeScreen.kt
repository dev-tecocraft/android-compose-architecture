@file:OptIn(ExperimentalAnimationApi::class)

package com.teco.apparchitecure.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.teco.apparchitecure.data.local.model.CategoryModel
import com.teco.apparchitecure.ui.theme.AppArchitecureTheme

@Composable
fun HomeScreen() {
    AppArchitecureTheme {
        val viewModel: HomeViewModel = viewModel()
        val homeScreenState by viewModel.homeScreenState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Shopping Category",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                )
            }
            homeScreenState.isLoading?.let { isLoading ->
                AnimatedVisibility(
                    visible = isLoading,
                    enter = scaleIn(),
                    exit = scaleOut()
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.width(64.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                }
            }

            homeScreenState.categoryList?.let { categoryModels ->
                LazyColumn {
                    items(categoryModels) { item: CategoryModel ->
                        CategoryItem(categoryModel = item)
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(categoryModel: CategoryModel) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    isExpanded =
                        if (categoryModel.childCategory.isNotEmpty()) !isExpanded else false
                }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = categoryModel.name,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F),
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = {
                isExpanded = if (categoryModel.childCategory.isNotEmpty()) !isExpanded else false
            }) {
                Icon(
                    imageVector = if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    contentDescription = "Expand"
                )
            }
        }
        Divider()
        AnimatedVisibility(visible = isExpanded) {
            Column {
                categoryModel.childCategory.forEach { childCategory ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(text = childCategory.name, modifier = Modifier.weight(1F))
                    }
                    Divider(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}