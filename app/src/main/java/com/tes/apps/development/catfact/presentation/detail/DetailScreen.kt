package com.tes.apps.development.catfact.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.tes.apps.development.catfact.R
import com.tes.apps.development.catfact.domain.model.CatFactModel

@Composable
fun DetailScreen(
    catFactModel: CatFactModel?,
    navController: NavController,
) {

    val imagePainter = rememberImagePainter(data = catFactModel?.url, builder = {
        placeholder(R.drawable.placeholder)
    })

    Column(modifier = Modifier.padding(20.dp)) {
        TopAppBarContent(navController)
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),

            elevation = 0.dp,
            shape = RoundedCornerShape(0.dp)
        ) {
            Column {
                Row(Modifier.padding(top = 10.dp)) {
                    Card(Modifier
                        .fillMaxWidth(),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Image(
                            painter = imagePainter,
                            modifier = Modifier.size(300.dp),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Cat Image")
                    }
                }
                Row(Modifier.padding(top = 10.dp, start = 8.dp)) {
                    Box(Modifier
                        .fillMaxWidth()) {
                        Text(text = catFactModel?.name + " breed from "+catFactModel?.origin, fontSize = 24.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.W400)
                    }
                }
                Row(Modifier.padding(horizontal = 8.dp)) {
                    Box(Modifier
                        .fillMaxWidth(), contentAlignment = Alignment.CenterStart
                    ) {
                        Column {
                            Text(text ="Life  Span "+catFactModel?.lifeSpan+" years ,"+ " size of =" +catFactModel?.width+"x"+catFactModel?.height, fontSize = 16.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.height(20.dp))
                            // Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                            Text(text = "This cat  has  adaptability: ${catFactModel?.adaptability},  intelligence: ${catFactModel?.intelligence}, cat friendly: ${catFactModel?.catFriendly}, dog friendly: ${catFactModel?.dogFriendly} and  affection level: ${catFactModel?.affectionLevel}")
                            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                            Text(text = catFactModel?.desc.toString())
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun TopAppBarContent(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = " Cat Fact Detail",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 4.dp,
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Go back",
                )
            }
        }
    )
}