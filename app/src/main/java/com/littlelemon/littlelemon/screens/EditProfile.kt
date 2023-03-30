package com.littlelemon.littlelemon.screens

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.littlelemon.littlelemon.MainActivity
import com.littlelemon.littlelemon.components.BrandButton
import com.littlelemon.littlelemon.components.Input
import com.littlelemon.littlelemon.ui.theme.BrandColors
import com.littlelemon.littlelemon.ui.theme.BrandTypography

@Composable
fun EditProfileScreen(sharedPreferences: SharedPreferences, navController: NavHostController) {

    val firstName = sharedPreferences.getString(MainActivity.FIRST_NAME_KEY, "")
    val lastName = sharedPreferences.getString(MainActivity.LAST_NAME_KEY, "")
    val emailAddress = sharedPreferences.getString(MainActivity.EMAIL_ADDRESS_KEY, "")

    val firstNameValue = remember { mutableStateOf(TextFieldValue(firstName ?: "")) }
    val firstNameError = remember { mutableStateOf("") }

    val lastNameValue = remember { mutableStateOf(TextFieldValue(lastName ?: "")) }
    val lastNameError = remember { mutableStateOf("") }

    val emailAddressValue = remember { mutableStateOf(TextFieldValue(emailAddress ?: "")) }
    val emailAddressError = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title =  {
                    Text(text = "Update Profile", style = BrandTypography.ParagraphText)
                },
                backgroundColor = BrandColors.SurfaceColor,
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Arrow")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            Column(Modifier.padding(16.dp)) {
                Row {
                    Input(
                        label = "First name",
                        onValueChange = { firstNameValue.value = it },
                        inputValue = firstNameValue.value,
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(end = 6.dp),
                        isError = firstNameError.value.isNotEmpty(),
                        errorMessage = firstNameError.value
                    )

                    Input(
                        label = "Last name",
                        onValueChange = { lastNameValue.value = it },
                        inputValue = lastNameValue.value,
                        modifier = Modifier.weight(0.5f),
                        isError = lastNameError.value.isNotEmpty(),
                        errorMessage = lastNameError.value
                    )

                }

                Input(
                    label = "Email Address",
                    keyboardType = KeyboardType.Email,
                    onValueChange = { emailAddressValue.value = it },
                    inputValue = emailAddressValue.value,
                    isError = emailAddressError.value.isNotEmpty(),
                    errorMessage = emailAddressError.value,
                )

                BrandButton(
                    text = "Update",
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {

                    val firstNameInput = firstNameValue.value.text
                    val lastNameInput = lastNameValue.value.text
                    val emailAddressInput = emailAddressValue.value.text

                    if (firstNameInput.isBlank()) {
                        firstNameError.value = "Field Required"
                    } else {
                        firstNameError.value = ""
                    }


                    if (lastNameInput.isBlank()) {
                        lastNameError.value = "Field Required"
                    } else {
                        lastNameError.value = ""
                    }


                    if (emailAddressInput.isBlank()) {
                        emailAddressError.value = "Field Required"
                    } else {
                        emailAddressError.value = ""
                    }

                    if (firstNameInput.isBlank() || lastNameInput.isBlank() || emailAddressInput.isBlank()) {
                        return@BrandButton
                    }

                    sharedPreferences.edit {
                        if (firstNameInput != firstName) {
                            putString(MainActivity.FIRST_NAME_KEY, firstNameInput)
                        }

                        if (lastNameInput != lastName) {
                            putString(MainActivity.LAST_NAME_KEY, lastNameInput)
                        }

                        if (emailAddressInput != emailAddress) {
                            putString(MainActivity.EMAIL_ADDRESS_KEY, emailAddressInput)
                        }
                    }
                    navController.navigateUp()
                }
            }
        }
    }

}