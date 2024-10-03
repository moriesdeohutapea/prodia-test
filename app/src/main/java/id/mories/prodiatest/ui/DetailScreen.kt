package id.mories.prodiatest.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import id.mories.domain.model.detail.Launche
import id.mories.prodiatest.ui.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    id: String?, mainViewModel: MainViewModel = koinViewModel()
) {
    LaunchedEffect(id) {
        id?.let { mainViewModel.getArticleById(it) }
    }
    val resultResponse by mainViewModel.articlesID.collectAsState(initial = null)

    resultResponse?.let { result ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = result.imageUrl,
                contentDescription = result.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = result.title.toString(),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = result.summary ?: "No summary available",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            DetailSection("News Site", result.newsSite ?: "Unknown")
            DetailSection("Published At", result.publishedAt ?: "N/A")
            DetailSection("Updated At", result.updatedAt ?: "N/A")
            DetailSection("URL", result.url.toString())

            if (result.launches?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.height(16.dp))
                LaunchSection(result.launches)
            }

            if (result.events?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.height(16.dp))
                EventSection(result.events)
            }
        }
    } ?: LoadingScreen()
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun DetailSection(title: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.labelMedium)
        Text(text = content, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun LaunchSection(launches: List<Launche>?) {
    SectionTitle("Launches")
    launches?.forEach { launch ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp), elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Launch ID: ${launch.launchId}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Provider: ${launch.provider}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun EventSection(events: List<id.mories.domain.model.detail.Event?>?) {
    SectionTitle("Events")
    events?.forEach { event ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp), elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Event ID: ${event?.eventId}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Provider: ${event?.provider}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.fillMaxWidth()
    )
}