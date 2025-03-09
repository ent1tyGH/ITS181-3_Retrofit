package com.griel.employeeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.unit.dp

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
    LazyColumn(modifier = Modifier.padding(top = 32.dp)) {
        items(Employees) { Employee ->
            EmployeeItem(Employee = Employee)
        }
    }
}


@Composable
fun EmployeeItem(Employee: Employee) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = Employee.id.toString(),
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        )
        Text(
            text = Employee.name,
            modifier = Modifier
                .weight(3f)
                .padding(4.dp)
        )
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
