package com.ronha.spike.compose.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ronha.spike.compose.R
import com.ronha.spike.compose.screens.Screen

private val messages: List<GreetingMessage> by lazy {
    val list: MutableList<GreetingMessage> = mutableListOf()
    for (i in 1..100) {
        list.add(
            GreetingMessage(
                title = "Hello Jetpack Compose $i!",
                body = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Leta set sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. (Item $i)"
            )
        )
    }
    list
}

@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold {
        HomeBodyContent(navController = navController)
    }
}

@Composable
fun HomeBodyContent(navController: NavController) {
    GreetingMessagesComponent(navController = navController, messages = messages)
}

@Composable
fun GreetingMessagesComponent(navController: NavController, messages: List<GreetingMessage>) {
    LazyColumn {
        items(messages) { message ->
            ComponentGreeting(navController = navController, message = message)
        }
    }
}

@Composable
fun ComponentGreeting(navController: NavController, message: GreetingMessage) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(8.dp)
            .clickable {
                navController.navigate(route = Screen.Detail.route)
            }
    ) {
        ImageGreeting()
        TextGreeting(message)
    }
}

@Composable
fun ImageGreeting() {
    Image(
        painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "Example",
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colors.primary)
    )
}

@Composable
fun TextGreeting(message: GreetingMessage) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(start = 8.dp)
        .clickable {
            expanded = !expanded
        }) {
        Text(
            text = message.title,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.subtitle1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = message.body,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.subtitle2,
            maxLines = if (expanded) Int.MAX_VALUE else 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}