package id.mories.prodiatest.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import id.mories.domain.model.ResultResponse
import id.mories.prodiatest.ui.route.Routes
import id.mories.prodiatest.ui.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen(
    username: String?, navController: NavController, mainViewModel: MainViewModel = koinViewModel()
) {
    val articles by mainViewModel.articles.collectAsState(emptyList())
    val blogs by mainViewModel.blogs.collectAsState(emptyList())
    val reports by mainViewModel.reports.collectAsState(emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top
    ) {
        Greeting(username ?: "User")
        Spacer(modifier = Modifier.height(24.dp))
        ContentSection(title = "artikel", item = articles, navController = navController)
        ContentSection(title = "blog", item = blogs, navController = navController)
        ContentSection(title = "report", item = reports, navController = navController)
    }
}

@Composable
fun HorizontalItemsGrid(data: List<ResultResponse>, navController: NavController) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp), contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(data) { data ->
            DataItem(item = data, onClick = {
                navController.navigate("${Routes.DETAIL_SCREEN}/$it")
            })
        }
    }
}

@Composable
fun DataItem(item: ResultResponse, onClick: (id: Int?) -> Unit) {
    Column(modifier = Modifier
        .width(150.dp)
        .padding(8.dp)
        .clickable { onClick(item.id) }) {
        item.imageUrl?.let { url ->
            AsyncImage(
                model = url, contentDescription = "Article Image", contentScale = ContentScale.Crop, modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Good Morning", style = MaterialTheme.typography.headlineMedium)
        Text(text = name, style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun ContentSection(title: String, item: List<ResultResponse>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            Text(text = "see more", style = MaterialTheme.typography.bodyLarge)
        }
        HorizontalItemsGrid(item, navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    DashboardScreen("John Doe", navController = navController)
}