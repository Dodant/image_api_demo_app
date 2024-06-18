package com.example.demo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.ui.theme.DemoTheme
import com.example.demo.api.sendPostRequest
import com.example.demo.viewmodel.ImageViewModel
import com.example.demo.viewmodel.RadioButtonViewModel
import androidx.compose.foundation.Image


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
fun AIButton(onClick: () -> Unit, text: String, wip: Boolean = false, repo: String = "") {
    Column{
        Button(
            onClick = onClick,
            border = BorderStroke(5.dp, Color(red = 223, green = 99, blue = 50)),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier.width(1200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 28, green = 64, blue = 106),
                contentColor = Color(red = 223, green = 99, blue = 50)
            )
        ) {
            if (wip) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text, style = TextStyle(fontSize = 45.sp, fontWeight = FontWeight.Bold))
                    Text("Work in Progress", style = TextStyle(fontSize = 30.sp), color = Color(red = 114, green = 209, blue = 101))
                }
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text, style = TextStyle(fontSize = 45.sp, fontWeight = FontWeight.Bold))
                    Text(repo, style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Medium), color = Color(red = 216, green = 251, blue = 210))
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun DoubleAIButton(onClick1: () -> Unit, onClick2: () -> Unit, text1: String, text2: String, repo1: String, repo2: String) {
    Column {
        Row{
            Button(
                onClick = onClick1,
                border = BorderStroke(5.dp, Color(red = 223, green = 99, blue = 50)),
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier.width(590.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 28, green = 64, blue = 106),
                    contentColor = Color(red = 223, green = 99, blue = 50)
                )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text1, style = TextStyle(fontSize = 45.sp, fontWeight = FontWeight.Bold))
                    Text(repo1, style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Medium), color = Color(red = 216, green = 251, blue = 210))
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = onClick2,
                border = BorderStroke(5.dp, Color(red = 223, green = 99, blue = 50)),
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier.width(590.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 28, green = 64, blue = 106),
                    contentColor = Color(red = 223, green = 99, blue = 50)
                )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text2, style = TextStyle(fontSize = 45.sp, fontWeight = FontWeight.Bold))
                    Text(
                        repo2,
                        style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Medium),
                        color = Color(red = 216, green = 251, blue = 210)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun RadioImageButton(painter: Painter, contentDescription: String?, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.size(350.dp)
        )
        if (selected) {
            Box(
                modifier = Modifier
                    .matchParentSize()
            )
        }
    }
}

@Composable
fun RadioImageButtonGroup(viewModel: RadioButtonViewModel) {
    val selectedOption by viewModel.selectedOption.collectAsState()

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        RadioImageButton(
            painter = painterResource(id = R.drawable.sample),
            contentDescription = "Option 1",
            selected = selectedOption == 0,
            onClick = { viewModel.selectOption(0)
                Log.i("RadioImageButtonGroup", selectedOption.toString())}
        )
        RadioImageButton(
            painter = painterResource(id = R.drawable.sample1),
            contentDescription = "Option 2",
            selected = selectedOption == 1,
            onClick = { viewModel.selectOption(1)
                Log.i("RadioImageButtonGroup", selectedOption.toString())}
        )
        RadioImageButton(
            painter = painterResource(id = R.drawable.sample2),
            contentDescription = "Option 3",
            selected = selectedOption == 2,
            onClick = { viewModel.selectOption(2)
                Log.i("RadioImageButtonGroup", selectedOption.toString())}
        )
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

                AIButton(onClick = {   }, text = "Retargetting", wip = true)
                AIButton(onClick = {   }, text = "ROI CROP", wip = true)

                Spacer(modifier = Modifier.height(25.dp))

                Text("Ta-da", style = TextStyle(fontSize = 70.sp))
                Spacer(modifier = Modifier.height(30.dp))

                Box(modifier = Modifier.border(BorderStroke(5.dp, Color.Black)).size(1200.dp)) {
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
