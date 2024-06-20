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
import java.time.Duration
import java.time.Instant


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

    Surface(color = MaterialTheme.colorScheme.inverseOnSurface,) {
        Row(modifier = modifier.padding(24.dp)) {
            Column(
                modifier = modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Image AI API DEMO", style = TextStyle(fontSize = 110.sp), fontWeight = FontWeight.Bold)
                Text("Pick'em", style = TextStyle(fontSize = 70.sp))
                Spacer(modifier = Modifier.height(30.dp))

                RadioImageButtonGroup(vm)

                AIButton(onClick = {
                    Log.i("AIButton", selectedOption.toString())
                    sendPostRequest(context, selectedOption, method="gan", viewModel)
                }, text = "Outpainting (GAN)", repo = "gh:basilevh/image-outpainting")

                DoubleAIButton(
                    onClick1 = {Log.i("AIButton", selectedOption.toString())
                                sendPostRequest(context, selectedOption, method="sd1", viewModel) },
                    onClick2 = {Log.i("AIButton", selectedOption.toString())
                                sendPostRequest(context, selectedOption, method="sd2", viewModel) },
                    text1 = "Outpainting (Diffusion)", text2 = "Outpainting (Diffusion)",
                    repo1 = "hf:runwayml/SD-inpainting", repo2 = "hf:runwayml/SD-2-inpainting")

                DoubleAIButton(
                    onClick1 = {Log.i("AIButton", selectedOption.toString())
                                sendPostRequest(context, selectedOption, method="sr1", viewModel) },
                    onClick2 = {Log.i("AIButton", selectedOption.toString())
                                sendPostRequest(context, selectedOption, method="sr2", viewModel) },
                    text1 = "Super Resolution", text2 = "Super Resolution",
                    repo1 = "gh:xinntao/ESRGAN", repo2 = "gh:xinntao/Real-ESRGAN")

                AIButton(onClick = {
                    Log.i("AIButton", selectedOption.toString())
                    sendPostRequest(context, selectedOption, method="sod", viewModel)
                }, text = "CROP w/ SOD", repo = "gh:backseason/DFI")

                Spacer(modifier = Modifier.height(30.dp))

                Box(modifier = Modifier.border(BorderStroke(5.dp, Color(red = 223, green = 99, blue = 50))).size(1200.dp)) {
                    viewModel.imageBitmap.value?.let { imageBitmap ->
                        Image(
                            bitmap = imageBitmap,
                            contentDescription = "Selected Image",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
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
