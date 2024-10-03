package id.mories.prodiatest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.mories.prodiatest.ui.DashboardScreen
import id.mories.prodiatest.ui.DetailScreen
import id.mories.prodiatest.ui.route.Routes
import id.mories.prodiatest.ui.theme.ProdiaTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProdiaTestTheme {
                WelcomeApp()
            }
        }
    }
}

@Composable
fun WelcomeApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.INPUT_SCREEN) {
        composable("input_screen") { InputScreen(navController) }
        composable("${Routes.DASHBOARD_SCREEN}/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username")
            DashboardScreen(username, navController)
        }
        composable("${Routes.DETAIL_SCREEN}/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            DetailScreen(id)
        }
    }
}

@Composable
fun InputScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Enter your name") }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (username.isNotEmpty()) {
                    navController.navigate("${Routes.DASHBOARD_SCREEN}/$username")
                }
            }, modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WelcomeApp()
}