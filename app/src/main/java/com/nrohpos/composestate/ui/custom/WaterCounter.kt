package com.nrohpos.composestate.ui.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nrohpos.composestate.ui.theme.ComposeStateTheme
import com.nrohpos.composestate.viewmodel.WellnessViewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier) {
        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCloseTask = { task -> wellnessViewModel.remove(task) },
            onCheckedTask = { task, isChecked ->
                wellnessViewModel.changeTaskChecked(task, isChecked)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeStateTheme {
        WellnessScreen()
    }
}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable { mutableStateOf(0) }
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = { count++ }, enabled = count < 10) {
                Text("Add one")
            }
            Button(onClick = { count = 0 }, Modifier.padding(start = 8.dp)) {
                Text("Clear water count")
            }
        }
    }
}