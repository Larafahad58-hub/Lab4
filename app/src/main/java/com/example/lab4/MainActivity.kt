package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PlannerHomeRoute()
            }
        }
    }
}

data class PlannerUiState(
    val greeting: String = "Hello, Student",
    val totalTasks: Int = 7,
    val completedTasks: Int = 3
)

@Composable
fun PlannerHomeRoute() {
    var uiState by remember { mutableStateOf(PlannerUiState()) }

    PlannerHomeScreen(
        uiState = uiState,
        onTaskCompleteClicked = {
            if (uiState.completedTasks < uiState.totalTasks) {
                uiState = uiState.copy(completedTasks = uiState.completedTasks + 1)
            }
        }
    )
}

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onTaskCompleteClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = uiState.greeting,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${uiState.completedTasks} of ${uiState.totalTasks} done",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = onTaskCompleteClicked) {
                Text(text = "Complete a Task")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlannerHomeScreenPreviewEmpty() {
    MaterialTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(greeting = "Welcome, Lolo", totalTasks = 5, completedTasks = 0),
            onTaskCompleteClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlannerHomeScreenPreviewDone() {
    MaterialTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(greeting = "Welcome, Lolo", totalTasks = 5, completedTasks = 5),
            onTaskCompleteClicked = {}
        )
    }
}
