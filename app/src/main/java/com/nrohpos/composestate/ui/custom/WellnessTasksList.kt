package com.nrohpos.composestate.ui.custom

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nrohpos.composestate.model.WellnessTask


@Composable
fun WellnessTasksList(
    list: List<WellnessTask>,
    onCloseTask: (WellnessTask) -> Unit,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = list,
            key = { task ->
                // Return a stable + unique key for the item
                task.id
            }
        ) { task ->
            WellnessTaskItem(
                name = task.label,
                checkedState = task.checked.value,
                onClose = { onCloseTask(task) },

                onCheckedChange = { isChecked ->
                    onCheckedTask(task, isChecked)
                },
            )
        }
    }
}


@Composable
fun WellnessTaskItem(
    modifier: Modifier = Modifier,
    name: String,
    checkedState: Boolean = false,
    onClose: () -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},
) {
    WellnessTaskItem(
        taskName = name,
        checked = checkedState,
        onCheckedChange = onCheckedChange,
        onClose = onClose,
        modifier = modifier,
    )
}


@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName,
            color = MaterialTheme.colors.primary
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }