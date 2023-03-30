package com.littlelemon.littlelemon.screens

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.littlelemon.littlelemon.MainActivity
import com.littlelemon.littlelemon.R
import com.littlelemon.littlelemon.components.BrandButton
import com.littlelemon.littlelemon.components.Input
import com.littlelemon.littlelemon.navigation.Destinations


@Composable
fun OnboardingScreen(navController: NavHostController, sharedPreferences: SharedPreferences) {
    Column {
        Header()
        UpperPanel()
        InfoSection(navController, sharedPreferences)
    }

}


@Composable
fun Header(){
    Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically){
        Image(
            painter = painterResource(id = R.drawable.littlelemontopbar),
            contentDescription = "LittleLemon Logo",
            modifier =  Modifier.fillMaxWidth(0.5F),
            contentScale = ContentScale.FillWidth)
    }
}

@Composable
fun UpperPanel() {
    Column(
        modifier = Modifier
            .background(Color(0xFF495E57))
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.13f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Let's get to know you",
            fontSize = 24.sp,
            color = Color(0xFFEDEFEE)

        )
    }
}

@Composable
fun InfoSection(navController: NavHostController, sharedPreferences: SharedPreferences){

    val firstName = remember { mutableStateOf(TextFieldValue("")) }
    val firstNameError = remember { mutableStateOf("") }

    val lastName = remember { mutableStateOf(TextFieldValue("")) }
    val lastNameError = remember { mutableStateOf("") }

    val emailAddress = remember { mutableStateOf(TextFieldValue("")) }
    val emailAddressError = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(start = 10.dp)) {
        Text(
            text = "Personal information",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF495E57),
            modifier = Modifier.padding(top= 50.dp, bottom = 50.dp, start = 5.dp)
        )
        Input(
            label = "First name",
            onValueChange = { firstName.value = it },
            inputValue = firstName.value,
            isError = firstNameError.value.isNotEmpty(),
            errorMessage = firstNameError.value
        )
        Input(
            label = "Last Name",
            onValueChange = { lastName.value = it },
            inputValue = lastName.value,
            isError = lastNameError.value.isNotEmpty(),
            errorMessage = lastNameError.value
        )
        Input(
            label = "Email Address",
            keyboardType = KeyboardType.Email,
            onValueChange = { emailAddress.value = it },
            inputValue = emailAddress.value,
            isError = emailAddressError.value.isNotEmpty(),
            errorMessage = emailAddressError.value
        )
        BrandButton(
            text = "Register",
            modifier = Modifier.padding(top = 140.dp, end = 10.dp)
                .fillMaxWidth()
        ) {

            val firstNameValue = firstName.value.text
            val lastNameValue = lastName.value.text
            val emailAddressValue = emailAddress.value.text

            if (firstNameValue.isBlank()) {
                firstNameError.value = "Field Required"
            } else {
                firstNameError.value = ""
            }


            if (lastNameValue.isBlank()) {
                lastNameError.value = "Field Required"
            } else {
                lastNameError.value = ""
            }


            if (emailAddressValue.isBlank()) {
                emailAddressError.value = "Field Required"
            } else {
                emailAddressError.value = ""
            }

            if (firstNameValue.isBlank() || lastNameValue.isBlank() || emailAddressValue.isBlank()) {
                return@BrandButton
            }

            sharedPreferences
                .edit()
                .putString(MainActivity.FIRST_NAME_KEY, firstNameValue)
                .putString(MainActivity.LAST_NAME_KEY, lastNameValue)
                .putString(MainActivity.EMAIL_ADDRESS_KEY, emailAddressValue)
                .putBoolean(MainActivity.IS_LOGGED_IN_KEY, true)
                .apply()

            navController.navigate(route = Destinations.Home.route)
        }
    }
}