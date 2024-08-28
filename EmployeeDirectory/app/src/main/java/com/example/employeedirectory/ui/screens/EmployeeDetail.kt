package com.example.employeedirectory.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.employeedirectory.R
import com.example.employeedirectory.model.Employee
import com.example.employeedirectory.ui.theme.EmployeeDirectoryTheme
import kotlinx.coroutines.Dispatchers

@Composable
fun EmployeeDetail(employee: Employee, modifier: Modifier = Modifier) {
    var isRefreshing by remember {
        mutableStateOf(true)
    }
    
    // Build an ImageRequest with Coil
    val listener = object : ImageRequest.Listener {
        override fun onStart(request: ImageRequest) {
            super.onStart(request)
            isRefreshing = true
        }

        override fun onCancel(request: ImageRequest) {
            super.onCancel(request)
            isRefreshing = false
        }

        override fun onError(request: ImageRequest, result: ErrorResult) {
            super.onError(request, result)
            isRefreshing = false
        }

        override fun onSuccess(request: ImageRequest, result: SuccessResult) {
            super.onSuccess(request, result)
            isRefreshing = false
        }
    }
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(employee.photoURLLg)
        .listener(listener)
        .dispatcher(Dispatchers.IO)
        .memoryCacheKey(employee.photoURLLg)
        .diskCacheKey(employee.photoURLLg)
        .error(R.drawable.ic_broken_image)
        .fallback(R.drawable.ic_launcher_foreground)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    Column (
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            if (isRefreshing) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                )
            }
            AsyncImage(
                model = imageRequest,
                contentDescription = employee.fullName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = employee.fullName,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Start
        )
        Text(
            text = employee.team,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        employee.biography?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Text(
            text = formatPhoneNumber(employee.phoneNumber.toString()),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Start
        )
        Text(
            text = employee.emailAddress,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Start
        )

    }
}

@Preview(showBackground = true)
@Composable
fun EmployeeDetailPreview() {
    EmployeeDirectoryTheme {
        val employee = Employee(
            "12398023",
            "Lorem ipsum - kitten kapturere",
            "208-308-3838",
            team = "Customer Support",
            emailAddress = "katykahla@gmail.com",
            biography = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do\" +\n" +
                    "                        \" eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad\" +\n" +
                    "                        \" minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip\" +\n" +
                    "                        \" ex ea commodo consequat."
        )
        EmployeeDetail(employee)
    }
}