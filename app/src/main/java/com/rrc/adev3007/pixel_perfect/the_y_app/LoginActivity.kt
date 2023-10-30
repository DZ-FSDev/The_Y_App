package com.rrc.adev3007.pixel_perfect.the_y_app

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import android.content.Intent
import android.util.Log
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rrc.adev3007.pixel_perfect.the_y_app.components.ScalingLevel
import com.rrc.adev3007.pixel_perfect.the_y_app.data.Synchronizer
import com.rrc.adev3007.pixel_perfect.the_y_app.data.models.UserAuth
import com.rrc.adev3007.pixel_perfect.the_y_app.session.SessionViewModel
import kotlinx.coroutines.launch


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = SessionViewModel(applicationContext)
        setContent {
            LoginScreen(viewModel)
        }
    }
}

@Composable
fun LoginScreen(viewModel: SessionViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisability by remember {
        mutableStateOf(true)
    }
    val activity = LocalContext.current as Activity
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var errorString by remember { mutableStateOf<String>("") }

    if (activity.intent.extras != null) {
        val extras = activity.intent.extras
        if (extras?.containsKey("CreatedUser") == true) {
            val createdUser = extras.getSerializable("CreatedUser") as? UserAuth
            createdUser?.let { userAuth ->
                LaunchedEffect(userAuth) {
                    try {
                        coroutineScope.launch {
                            val response = Synchronizer.api.postLogin(userAuth)
                            if (response.isSuccessful) {
                                val userAccount = response.body()
                                viewModel.setAPIKey(userAccount?.apiKey.toString())
                                viewModel.updateUsername(userAuth.username)
                                viewModel.updateEmail(userAccount?.email.toString())
                                viewModel.updateFirstName(userAccount?.firstName.toString())
                                viewModel.updateLastName(userAccount?.lastName.toString())
                                viewModel.updateScale((ScalingLevel.valueOf(userAccount?.uiScale.toString())))
                                viewModel.updateProfilePicture(userAccount?.profilePicture?.toString())
                                // placeholder to test username update
                                viewModel.updateUsername("${viewModel.firstName.value} ${viewModel.lastName.value}")
                                activity.finish()
                                activity.startActivity(Intent(activity, HomeActivity::class.java))
                            } else {
                                // TODO add something here if need
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("LoginScreen", "Login failed: ${e.message}")
                    }
                }
            }
        }
    }


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
            /*
        .background(Color(0xffF0F0F0))
            TODO? Find fix for textfields background color being outside of it's outline
        */
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
            Text(text = "Welcome Back", fontSize = 24.sp)
            Spacer(modifier = Modifier.padding(bottom = 24.dp))
            if (!errorString.isEmpty()) Text(text = errorString, color = Color.Red)
            OutlinedTextField(
                value = username,
                label = { Text(text = "Username") },
                onValueChange = {
                    username = it
                    errorString = ""
                },
                placeholder = { Text(text = "Enter Username") },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Column {
                OutlinedTextField(
                    value = password,
                    label = { Text(text = "Password") },
                    onValueChange = {
                        password = it
                        errorString = ""
                    },
                    placeholder = { Text(text = "Enter Password") },
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisability)
                        PasswordVisualTransformation()
                    else
                        VisualTransformation.None,
                    trailingIcon = {
                        if (passwordVisability) {
                            IconButton(
                                onClick = {
                                    passwordVisability = false
                                },
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.visibility_off),
                                    contentDescription = null
                                )
                            }
                        } else {
                            IconButton(
                                onClick = {
                                    passwordVisability = true
                                },
                            ) {

                                Icon(
                                    painter = painterResource(id = R.drawable.visibility_on),
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
                ClickableText(
                    onClick = {/* TODO */ println("This works?") },
                    text = AnnotatedString("Forgot Password?"),
                    style = TextStyle(color = Color.Blue),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 46.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Button(
                    onClick = {
                        try {
                            isLoading = true
                            coroutineScope.launch {
                                val loginAuth = UserAuth(username, password)
                                val response = Synchronizer.api.postLogin(loginAuth)
                                if (response.isSuccessful) {
                                    val userAccount = response.body()
                                    viewModel.setAPIKey(userAccount?.apiKey.toString())
                                    viewModel.updateUsername(username)
                                    viewModel.updateEmail(userAccount?.email.toString())
                                    viewModel.updateFirstName(userAccount?.firstName.toString())
                                    viewModel.updateLastName(userAccount?.lastName.toString())
                                    viewModel.updateScale((ScalingLevel.valueOf(userAccount?.uiScale.toString())))
                                    viewModel.updateProfilePicture(userAccount?.profilePicture?.toString())
                                    //placeholder to test username update
                                    viewModel.updateUsername("${viewModel.firstName.value} ${viewModel.lastName.value}")
                                    activity.finish()
                                    activity.startActivity(
                                        Intent(
                                            activity,
                                            HomeActivity::class.java
                                        )
                                    )
                                } else {
                                    Log.e("loginError", response.code().toString())
                                    errorString = "Username or password is incorrect!"
                                }
                                isLoading = false
                            }
                        } catch (e: Exception) {
                            Log.e("LoginScreen", "Login failed: ${e.message}")
                        }
                    },
                    enabled = !isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Login", Modifier.padding(8.dp))
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                ) {
                    // Solid line on the left
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .weight(1f)
                            .background(Color.Black)
                    )

                    // Some text
                    Text(
                        text = "or",
                        Modifier.padding(horizontal = 18.dp)
                    )

                    // Solid line on the right
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .weight(1f)
                            .background(Color.Black)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedButton(
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                    onClick = {
                        activity.finish()
                        activity.startActivity(Intent(activity, CreateUserActivity::class.java))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Sign up", Modifier.padding(8.dp))
                }
            }
        }
    }

