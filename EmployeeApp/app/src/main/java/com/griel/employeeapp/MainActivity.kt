package com.griel.employeeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.griel.employeeapp.data.model.Employee
import com.griel.employeeapp.ui.theme.EmployeeAppTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Popup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Info
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmployeeAppTheme {
                EmployeeScreen()
            }
        }
    }
}

@Composable
fun EmployeeScreen(viewModel: EmployeeViewModel = viewModel()) {
    val Employees by viewModel.employees.collectAsState()

    if(Employees.isEmpty()){
        LoadingScreen()
    } else {
        EmployeeList(Employees = Employees)
    }

}



@Composable
fun EmployeeList(Employees: List<Employee>) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 32.dp) // Increased top padding
    ) {
        // Table Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "ID",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Text(
                text = "Name",
                modifier = Modifier.weight(3f),
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }
        Divider(color = Color.Gray, thickness = 2.dp) // Line under header

        LazyColumn {
            itemsIndexed(Employees) {index, employee ->
                EmployeeItem(employee, index)
            }
        }
    }
}

@Composable
fun EmployeeItem(Employee: Employee, index: Int) {
    var showTooltip by remember { mutableStateOf(false) }
    var tooltipText by remember { mutableStateOf<String?>(null) }

    val backgroundColor = if (index % 2 == 0) Color.LightGray else Color.White

    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "${Employee.id} - ${Employee.name}", fontWeight = FontWeight.Bold)
            }

            Row {
                IconButton(onClick = {
                    tooltipText = "Job Title: ${Employee.jobTitle}"
                    showTooltip = true
                    println("Tooltip set: $tooltipText") // Debugging log
                }) {
                    Icon(imageVector = Icons.Default.Work, contentDescription = "Job Title")
                }

                IconButton(onClick = {
                    tooltipText = "Salary: \$${Employee.salary}"
                    showTooltip = true
                    println("Tooltip set: $tooltipText") // Debugging log
                }) {
                    Icon(imageVector = Icons.Default.AttachMoney, contentDescription = "Salary")
                }

                IconButton(onClick = {
                    tooltipText = "Employment Type: ${Employee.employmentType.name.replace("_", " ").lowercase().capitalize()}"
                    showTooltip = true
                    println("Tooltip set: $tooltipText") // Debugging log
                }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Employment Type")
                }
            }
        }

        if (showTooltip && tooltipText != null) {
            TooltipPopup(text = tooltipText!!) {
                showTooltip = false
            }
        }
    }
}

@Composable
fun TooltipPopup(text: String, onDismiss: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 8.dp, // Adds subtle shadow
            color = Color.Black.copy(alpha = 1f) // Slight transparency for better contrast
        ) {
            Text(
                text = text,
                color = Color.White, // Changed to white for visibility
                modifier = Modifier.padding(12.dp)
            )
        }
    }

    // Hide tooltip after 2 seconds
    LaunchedEffect(text) {
        delay(2000)
        onDismiss()
    }
}


@Composable
fun LoadingScreen() {
    Text("Loading...")
}

@Composable
fun ErrorScreen(message: String) {
    Text("Error: $message")
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
