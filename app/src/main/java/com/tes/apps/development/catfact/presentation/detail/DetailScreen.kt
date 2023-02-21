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

    val height= catFactModel?.height.toString()
    val width =catFactModel?.width.toString()
    val  url = catFactModel?.url
    val origin =catFactModel?.origin
    val name  = catFactModel?.name
    val adaptability =catFactModel?.adaptability
    val catFriendly =catFactModel?.catFriendly
    val dogFriendly =catFactModel?.dogFriendly
    val desc =catFactModel?.desc
    val intelligence= catFactModel?.intelligence
    val  affectionLevel =catFactModel?.affectionLevel
    val lifeSpan =catFactModel?.lifeSpan

    val imagePainter = rememberImagePainter(data = url, builder = {
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
                        Text(text = name + " breed from "+origin, fontSize = 24.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.W400)
                    }
                }
                Row(Modifier.padding(horizontal = 8.dp)) {
                    Box(Modifier
                        .fillMaxWidth(), contentAlignment = Alignment.CenterStart
                    ) {
                        Column {
                            Text(text ="Life  Span "+lifeSpan+" years ,"+ " size of =" +width+"x"+height, fontSize = 16.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.height(20.dp))
                            // Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                            Text(text = "This cat  has  adaptability: $adaptability,  intelligence: $intelligence, cat friendly: $catFriendly, dog friendly: $dogFriendly and  affection level: $affectionLevel")
                            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                            Text(text = desc.toString())
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