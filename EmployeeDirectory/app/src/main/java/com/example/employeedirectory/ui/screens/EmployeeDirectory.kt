package com.example.employeedirectory.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.employeedirectory.R
import com.example.employeedirectory.model.Employee
import com.example.employeedirectory.ui.theme.EmployeeDirectoryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeDirectory(modifier: Modifier = Modifier) {
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
                employeeUIState = employeeViewModel.employeeUIState,
                retryAction = employeeViewModel::getEmployees
            )
        }
    }
}

@Composable
fun EmployeeList(employeeUIState: EmployeeUIState, retryAction: () -> Unit, modifier: Modifier = Modifier) {
    when(employeeUIState) {
        is EmployeeUIState.Error -> ErrorScreen(retryAction)
        is EmployeeUIState.Loading -> LoadingScreen()
        is EmployeeUIState.Success -> ResultScreen(employeeUIState.employeeList)
    }
}

@Composable
fun EmployeeCard(employee: Employee, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = employee.fullName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium)),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            Text(
                text = employee.phoneNumber,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
            Text(
                text = employee.emailAddress,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
        }
    }
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

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loading_failed))
        Button(onClick = { retryAction() }) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
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
        ErrorScreen({}, Modifier.fillMaxSize())
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
                emailAddress = "katykahla@gmail.com",
                biography = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do\" +\n" +
                        "                        \" eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad\" +\n" +
                        "                        \" minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip\" +\n" +
                        "                        \" ex ea commodo consequat."
            )
        }
        ResultScreen(mockData, Modifier.fillMaxSize())
    }
}