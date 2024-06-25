package com.example.demo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.ui.theme.DemoTheme
import com.example.demo.api.sendPostRequest
import com.example.demo.viewmodel.ImageViewModel
import com.example.demo.viewmodel.RadioButtonViewModel
import com.example.demo.component.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ImageViewModel()
        setContent {
            DemoTheme {
                Greeting(modifier = Modifier, viewModel)
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, viewModel: ImageViewModel) {
    val context = LocalContext.current
    val vm = RadioButtonViewModel()
    val selectedOption by vm.selectedOption.collectAsState()
    val buttonFontSize = 40.sp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inverseOnSurface)
    ) {
        Row(modifier = modifier.padding(20.dp)) {
            Column(
                modifier = modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Demo", style = TextStyle(fontSize = 70.sp), fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(20.dp))

                RadioImageButtonGroup(vm)

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .width(1500.dp)
                        .border(BorderStroke(8.dp, Color(red = 223, green = 99, blue = 50))),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            Log.i("AIButton", selectedOption.toString())
                            sendPostRequest(context, selectedOption, method = "gan", viewModel)
                        },
                        border = BorderStroke(2.dp, Color(red = 223, green = 99, blue = 50)),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            "Outpainting (GAN)",
                            style = TextStyle(
                                fontSize = buttonFontSize,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Button(
                        onClick = {
                            Log.i("AIButton", selectedOption.toString())
                            sendPostRequest(context, selectedOption, method = "sd1", viewModel)
                        },
                        border = BorderStroke(2.dp, Color(red = 223, green = 99, blue = 50)),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            "Outpainting (Diffusion)",
                            style = TextStyle(
                                fontSize = buttonFontSize,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Button(
                        onClick = {
                            Log.i("AIButton", selectedOption.toString())
                            sendPostRequest(context, selectedOption, method = "sr1", viewModel)
                        },
                        border = BorderStroke(2.dp, Color(red = 223, green = 99, blue = 50)),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            "Super Resolusion",
                            style = TextStyle(
                                fontSize = buttonFontSize,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Button(
                        onClick = {
                            Log.i("AIButton", selectedOption.toString())
                            sendPostRequest(context, selectedOption, method = "sod1", viewModel)
                        },
                        border = BorderStroke(2.dp, Color(red = 223, green = 99, blue = 50)),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            "Crop w/ Salient OD",
                            style = TextStyle(
                                fontSize = buttonFontSize,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        viewModel.imageBitmap.value?.let { imageBitmap ->
            Image(
                bitmap = imageBitmap,
                contentDescription = "Selected Image",
                modifier = Modifier.fillMaxSize()
                    .clickable { viewModel.imageBitmap.value = null }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val viewModel = ImageViewModel()
    DemoTheme {
        Greeting (modifier = Modifier, viewModel)
    }
}
