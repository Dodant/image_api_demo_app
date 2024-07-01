package com.example.demo.component

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.demo.viewmodel.RadioButtonViewModel
import com.example.demo.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RadioImageButton(id: Int, num: Int, selectedOption: Int, viewModel: RadioButtonViewModel) {
    Box(
        modifier = Modifier.combinedClickable(
            onClick = {
                viewModel.selectOption(num)
                Log.i("RadioImageButtonGroup", selectedOption.toString()) },),
        contentAlignment = Alignment.Center
    ) {
        // For emul
        // Image(modifier = Modifier.width(340.dp).height(340.dp), painter = painterResource(id), contentDescription = "Option $num", contentScale = ContentScale.Crop)
        Image(
            modifier = Modifier
                .width(130.dp)
                .height(150.dp),
            painter = painterResource(id),
            contentDescription = "Option $num",
            contentScale = ContentScale.Crop
        )
        if (selectedOption == num) Box(modifier = Modifier.matchParentSize())
    }
}

@Composable
fun RadioImageButtonGroup(viewModel: RadioButtonViewModel) {
    val selectedOption by viewModel.selectedOption.collectAsState()

    Column {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(1500.dp)) {
            RadioImageButton(id = R.drawable.test1, num = 1, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test2, num = 2, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test3, num = 3, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test4, num = 4, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test5, num = 5, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test6, num = 6, selectedOption = selectedOption, viewModel = viewModel)

        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(1500.dp)) {
            RadioImageButton(id = R.drawable.test7, num = 7, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test8, num = 8, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test9, num = 9, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test10, num = 10, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test11, num = 11, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test12, num = 12, selectedOption = selectedOption, viewModel = viewModel)
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(1500.dp)) {
            RadioImageButton(id = R.drawable.test13, num = 13, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test14, num = 14, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test15, num = 15, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test16, num = 16, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test17, num = 17, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test18, num = 18, selectedOption = selectedOption, viewModel = viewModel)
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(1500.dp)) {
            RadioImageButton(id = R.drawable.test19, num = 19, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test20, num = 20, selectedOption = selectedOption, viewModel = viewModel)
            RadioImageButton(id = R.drawable.test21, num = 21, selectedOption = selectedOption, viewModel = viewModel)
        }
    }
}
