package com.example.demo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
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
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ImageViewModel()
        setContent {
            DemoTheme {
                Frame(modifier = Modifier, viewModel)
            }
        }
    }
}

@Composable
fun Frame(modifier: Modifier = Modifier, viewModel: ImageViewModel) {
    val context = LocalContext.current
    val vm = RadioButtonViewModel()
    val selectedOption by vm.selectedOption.collectAsState()
    var currentTime by remember { mutableStateOf(LocalDateTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            currentTime = LocalDateTime.now()
        }
    }

    val customWidth = 200.dp

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.inverseOnSurface)) {
        Row(modifier = modifier.padding(20.dp)) {
            Column(modifier = modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Digital AI Frame", style = TextStyle(fontSize = 50.sp, color = MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface),
                    fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif)
                Spacer(modifier = Modifier.height(20.dp))

                Text("Gallery", style = TextStyle(fontSize = 30.sp,  color = MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface),
                    fontWeight = FontWeight.Medium, fontFamily = FontFamily.Serif)
                Spacer(modifier = Modifier.height(20.dp))

                RadioImageButtonGroup(vm)
                Spacer(modifier = Modifier.height(30.dp))

                Text("Methods", style = TextStyle(fontSize = 30.sp,  color = MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface),
                    fontWeight = FontWeight.Medium, fontFamily = FontFamily.Serif)
                Spacer(modifier = Modifier.height(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Button(
                        onClick = {
                            Log.i("AIButton", selectedOption.toString())
                            sendPostRequest(context, selectedOption, method = "original", viewModel) },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .width(customWidth * 4 + 10.dp * 3)
                            .height(60.dp)
                    ) { Text("Original", style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight.Medium)) }

                    Spacer(modifier = Modifier.height(10.dp))

//                    Row(modifier = Modifier.width(1500.dp), horizontalArrangement = Arrangement.Center) {
//                        Button(
//                            onClick = {
//                                Log.i("AIButton", selectedOption.toString())
//                                sendPostRequest(context, selectedOption, method = "gan", viewModel) },
//                            shape = RoundedCornerShape(10.dp),
//                            modifier = Modifier
//                                .width(customWidth)
//                                .height(customHeight)
//                        ) { Text("Outpainting (GAN)", style = TextStyle(fontSize = buttonFontSize, fontWeight = FontWeight.Medium)) }
//                        Spacer(modifier = Modifier.width(9.dp))
//                        Button(
//                            onClick = {
//                                Log.i("AIButton", selectedOption.toString())
//                                sendPostRequest(context, selectedOption, method = "sd1", viewModel) },
//                            shape = RoundedCornerShape(10.dp),
//                            modifier = Modifier
//                                .width(customWidth)
//                                .height(customHeight)
//                        ) { Text("Outpainting (Diffusion)", style = TextStyle(fontSize = buttonFontSize, fontWeight = FontWeight.Medium)) }
//                        Spacer(modifier = Modifier.width(9.dp))
//                        Button(
//                            onClick = {
//                                Log.i("AIButton", selectedOption.toString())
//                                sendPostRequest(context, selectedOption, method = "sr1", viewModel) },
//                            shape = RoundedCornerShape(10.dp),
//                            modifier = Modifier
//                                .width(customWidth)
//                                .height(customHeight)
//                        ) { Text("Super Resolusion", style = TextStyle(fontSize = buttonFontSize, fontWeight = FontWeight.Medium)) }
//                        Spacer(modifier = Modifier.width(9.dp))
//                        Button(
//                            onClick = {
//                                Log.i("AIButton", selectedOption.toString())
//                                sendPostRequest(context, selectedOption, method = "sod1", viewModel) },
//                            shape = RoundedCornerShape(10.dp),
//                            modifier = Modifier
//                                .width(customWidth)
//                                .height(customHeight)
//                        ) { Text("Crop with Salient OD", style = TextStyle(fontSize = buttonFontSize, fontWeight = FontWeight.Medium)) }
//                    }
//                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            Log.i("AIButton", selectedOption.toString())
                            sendPostRequest(context, selectedOption, method = "original", viewModel)
                            sendPostRequest(context, selectedOption, method = "auto", viewModel)
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .width(customWidth * 4 + 10.dp * 3)
                            .height(60.dp)
                    ) {Text("All At Once", style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight.Medium)) }
                }
            }
        }

        viewModel.imageBitmap.value?.let { imageBitmap ->
            Box {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { viewModel.imageBitmap.value = null }
                )
                Column (modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        currentTime.format(DateTimeFormatter.ofPattern("EEEE, MMMM d", Locale.ENGLISH)),
                        style = TextStyle(fontSize = 30.sp, color = MaterialTheme.colorScheme.onBackground),
                        fontWeight = FontWeight.Normal, fontFamily = FontFamily.Monospace
                    )
                    Text(
                        currentTime.format(DateTimeFormatter.ofPattern("h:mm:ss a", Locale.ENGLISH))
                            .replace("AM", "am")
                            .replace("PM", "pm"),
                        style = TextStyle(fontSize = 50.sp, color = MaterialTheme.colorScheme.onBackground),
                        fontWeight = FontWeight.Normal, fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val viewModel = ImageViewModel()
    DemoTheme { Frame (modifier = Modifier, viewModel) }
}
