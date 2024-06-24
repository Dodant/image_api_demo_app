package com.example.demo.component

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.viewmodel.RadioButtonViewModel
import com.example.demo.R

@Composable
fun AIButton(onClick: () -> Unit, text: String, repo: String = "") {
    Button(
        onClick = onClick,
        border = BorderStroke(5.dp, Color(red = 223, green = 99, blue = 50)),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.width(1200.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(red = 28, green = 64, blue = 106),
            contentColor = Color(red = 223, green = 99, blue = 50)
        )
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text, style = TextStyle(fontSize = 45.sp, fontWeight = FontWeight.Bold))
            Text(repo, style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Medium), color = Color(red = 216, green = 251, blue = 210))
        }
    }
}

@Composable
fun DoubleAIButton(onClick1: () -> Unit, onClick2: () -> Unit, text1: String, text2: String, repo1: String, repo2: String) {
    Row{
        Button(
            onClick = onClick1,
            border = BorderStroke(5.dp, Color(red = 223, green = 99, blue = 50)),
            shape = RoundedCornerShape(5.dp),
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
            shape = RoundedCornerShape(5.dp),
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
}

@Composable
fun RadioImageButton(painter: Painter, contentDescription: String?, selected: Boolean, onClick: () -> Unit) {
    Box(modifier = Modifier.clickable(onClick = onClick), contentAlignment = Alignment.Center) {
        Image(painter = painter, contentDescription = contentDescription, modifier = Modifier.width(250.dp))
        if (selected) {
            Box(modifier = Modifier.matchParentSize())
        }
    }
}

@Composable
fun RadioImageButtonGroup(viewModel: RadioButtonViewModel) {
    val selectedOption by viewModel.selectedOption.collectAsState()

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.width(1500.dp)
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
        RadioImageButton(
            painter = painterResource(id = R.drawable.sample3),
            contentDescription = "Option 4",
            selected = selectedOption == 3,
            onClick = { viewModel.selectOption(3)
                Log.i("RadioImageButtonGroup", selectedOption.toString())}
        )
    }
}
