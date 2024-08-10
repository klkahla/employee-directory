package com.example.employeedirectory.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.employeedirectory.R
import com.example.employeedirectory.model.Employee
import com.example.employeedirectory.ui.theme.EmployeeDirectoryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            val employeeViewModel: EmployeeViewModel = viewModel(factory = EmployeeViewModel.Factory)
            EmployeeList(
                employeeUIState = employeeViewModel.employeeUIState
            )
        }
    }
}

@Composable
fun EmployeeList(employeeUIState: EmployeeUIState) {
    when(employeeUIState) {
        is EmployeeUIState.Error -> ErrorScreen()
        is EmployeeUIState.Loading -> LoadingScreen()
        is EmployeeUIState.Success -> ResultScreen(employeeUIState.employeeList)
    }
}

@Composable
fun EmployeeCard(employee: Employee, modifier: Modifier = Modifier) {

}

@Composable
fun ResultScreen(employees: List<Employee>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_medium))
    ) {
        items(
            items = employees,
            key = { employee ->
                employee.uuid
            }
        ) { employee ->
            EmployeeCard(employee = employee, modifier = Modifier.fillMaxSize())
        }
    }
}

fun ErrorScreen(modifier: Modifier = Modifier) {

}

fun LoadingScreen(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    EmployeeDirectoryTheme {
        LoadingScreen(
            Modifier
                .fillMaxSize()
                .size(200.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    EmployeeDirectoryTheme {
        ErrorScreen(Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    EmployeeDirectoryTheme {
        val mockData = List(10) {
            Employee(
                "$it",
                "Lorem ipsum - $it",
                "208-308-3838",
                email = "",
                bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do\" +\n" +
                        "                        \" eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad\" +\n" +
                        "                        \" minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip\" +\n" +
                        "                        \" ex ea commodo consequat."
            )
        }
        ResultScreen(mockData, Modifier.fillMaxSize())
    }
}