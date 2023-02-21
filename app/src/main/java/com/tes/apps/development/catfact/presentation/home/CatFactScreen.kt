package com.tes.apps.development.catfact.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.tes.apps.development.catfact.R.drawable
import com.tes.apps.development.catfact.presentation.ui.Screen

@Composable
fun HomeScreen(
    navController: NavController,
) {
    val viewModel: CatFactViewModel = hiltViewModel()

    val state by viewModel.uiState.collectAsState()
    val catFactsState = state.catFacts

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onEvent(CatFactEvent.LoadList)
    })
    Column(modifier = Modifier.padding(20.dp)) {

        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(CatFactEvent.OnSearchQueryChange(it))
            },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search breeds abys, beng etc...")
            },
            maxLines = 1,
            singleLine = true,
            label = { Text("Search breeds abys, beng etc...") },
            shape = RoundedCornerShape(10.dp),
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (catFactsState.isNullOrEmpty() || state.isLoading) {
            CustomCircularProgressBar()
        } else {
            LazyColumn(
                modifier = Modifier
            ) {

                items(catFactsState.size) { i ->
                    val catFact = catFactsState[i]
                    val imagePainter = rememberImagePainter(data = catFact.url, builder = {
                        placeholder(drawable.placeholder)
                    })

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clickable {
                                navController.currentBackStackEntry?.savedStateHandle?.set(key="catFact", value = catFact)
                                navController.navigate(
                                    route= Screen.Detail.route
                                )
                            },
                        elevation = 0.dp,
                        shape = RoundedCornerShape(0.dp)
                    ) {
                        Column {
                            Row(Modifier.padding(top = 10.dp)) {
                                Card(
                                    Modifier
                                        .fillMaxWidth(),
                                    elevation = 0.dp,
                                    shape = RoundedCornerShape(22.dp)
                                ) {
                                    Image(
                                        painter = imagePainter,
                                        modifier = Modifier.size(250.dp),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = "Cat Image"
                                    )
                                }
                            }
                            Row(Modifier.padding(top = 10.dp, start = 8.dp)) {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = catFact.name + " breed from " + catFact.origin,
                                        fontSize = 24.sp,
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.W400
                                    )
                                }
                            }
                            Row(Modifier.padding(horizontal = 8.dp)) {
                                Box(
                                    Modifier
                                        .fillMaxWidth(), contentAlignment = Alignment.CenterStart
                                ) {
                                    Column {
                                        Text(
                                            text = "Life  Span " + catFact.lifeSpan + " years                 More...",
                                            fontSize = 16.sp,
                                            color = Color.Gray
                                        )
                                    }
                                    Column(
                                        horizontalAlignment = Alignment.End,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        IconButton(onClick = {
                                            navController.navigate(
                                                route = "${Screen.Detail.route}?height=${catFact.height} width=${catFact.width} url=${catFact.url} name=${catFact.name} origin=${catFact.origin} adaptability=${catFact.adaptability} affectionLevel=${catFact.affectionLevel} intelligence=${catFact.intelligence} desc=${catFact.desc} catFriendly=${catFact.catFriendly} dogFriendly=${catFact.dogFriendly} lifeSpan=${catFact.lifeSpan} "
                                            )
                                        }) {
                                            Icon(
                                                modifier = Modifier
                                                    .height(30.dp)
                                                    .width(30.dp),
                                                painter = painterResource(id = drawable.forwardarrow),
                                                contentDescription = "Detail Screen Icon"
                                            )
                                        }
                                    }
                                }
                            }

                        }
                    }
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )

                }

            }
        }
    }
}

@Composable
private fun CustomCircularProgressBar() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, bottom = 50.dp)
    ) {

        CircularProgressIndicator(
            modifier = Modifier.size(100.dp),
            color = Color.Green,
            strokeWidth = 10.dp
        )
    }
}