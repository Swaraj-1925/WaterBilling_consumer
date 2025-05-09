package com.water.meter_billing_consumer.ui.screens

import android.net.http.SslCertificate.saveState
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.water.meter_billing_consumer.ui.components.InputBox
import com.water.meter_billing_consumer.ui.viewmodel.AuthViewModel
import com.water.meter_billing_consumer.utils.AuthFormField
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.water.meter_billing_consumer.ui.navigation.ListScreenRoute
import com.water.meter_billing_consumer.ui.navigation.SigninScreenRoute


@Composable
fun SignUpScreen( navController: NavController,viewModel: AuthViewModel = hiltViewModel()) {
    Scaffold (
        modifier = Modifier.fillMaxSize()
    ){ paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ){
            SignUpScreenBody(
                paddingValues=paddingValues,
                viewModel=viewModel,
                navController=navController
            )

        }
    }
}

@Composable
fun SignUpScreenBody(
    paddingValues: PaddingValues,
    viewModel: AuthViewModel,
    navController: NavController
    ){
    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val address by viewModel.address.collectAsState()
    var showAlert by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    val authenticated by viewModel.authenticated.collectAsState()

    val formErrors by viewModel.formErrors.collectAsState()
    var alertMessage by remember { mutableStateOf("") }


    LaunchedEffect(authenticated) {
        if (authenticated) {
            Log.d("SignUpScreen", "Authentication successful, navigating to home")
            navController.navigate(ListScreenRoute) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
            }
        }
    }

    LaunchedEffect(formErrors) {
        if (formErrors.isNotEmpty()) {
            alertMessage = formErrors.values.joinToString("\n")
            showAlert = true
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight()

    ) {
        Text(
            text = "Hey there Consumer,\nCreate an Account",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        InputBox(
            value = name,
            placeholder = "Name",
            onValueChange = { AuthFormField.Name(it) },
            viewModel = viewModel,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Name")
            }
        )
        InputBox(
            value = email,
            placeholder = "Email",
            onValueChange = { AuthFormField.Email(it) },
            viewModel = viewModel,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
            },
            keyboardType = KeyboardType.Email
        )
        InputBox(
            value = phoneNumber.toString(),
            placeholder = "Phone number",
            onValueChange = {
                AuthFormField.PhoneNumber(it)
            },
            viewModel = viewModel,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone")
            },
            keyboardType = KeyboardType.Phone
        )
        InputBox(
            value = address,
            placeholder = "Address",
            onValueChange = { AuthFormField.Address(it) },
            viewModel = viewModel,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Name")
            }
        )
        InputBox(
            value = password,
            placeholder = "Password",
            onValueChange = { AuthFormField.Password(it) },
            viewModel = viewModel,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            keyboardType = KeyboardType.Password,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        InputBox(
            value = confirmPassword,
            placeholder = "Confirm Password",
            onValueChange = { AuthFormField.ConfirmPassword(it) },
            viewModel = viewModel,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Confirm Password")
            },
            trailingIcon = {
                IconButton(onClick = {  confirmPasswordVisible = !confirmPasswordVisible}) {
                    Icon(
                        imageVector = if (confirmPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password"
                    )
                }
            },
            keyboardType = KeyboardType.Password,
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        Button(
            onClick = {
                val phoneDigits = phoneNumber.filter { it.isDigit() }.length
                if (phoneDigits != 10 || (password != confirmPassword) ) {
                    showAlert = true
                } else {
                    Log.d("SignUpScreen", "Registration submitted: $name, $email, $password,$address, $phoneNumber")
                    viewModel.onSingUpClicked()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(text = "Register", fontSize = 16.sp)
        }

        Text(
            text = "Signin",
            modifier = Modifier
                .clickable(
                    onClick = {
                        navController.navigate(SigninScreenRoute)
                    }
                )
        )
    }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            title = { Text("Form Error") },
            text = { Text(alertMessage) },
            confirmButton = {
                TextButton(
                    onClick = {
                        showAlert = false
                        alertMessage = ""
                    }

                ) {
                    Text("OK")
                }
            }
        )
    }
}
