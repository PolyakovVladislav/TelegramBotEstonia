package ui.messages

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import configurationRepository
import domain.models.Message

@Composable
@Preview
fun MessagesForm(
    modifier: Modifier,
    onMessagesChanged: () -> Unit
) {
    val store = remember {
        MessagesStore(configurationRepository)
    }

    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Column {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(end = 4.dp),
                value = store.messageState,
                onValueChange = store::editMessageField,
                label = { Text("Message") }
            )
            Row(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(end = 4.dp).weight(2f),
                    value = store.timeState,
                    onValueChange = store::editTimeField,
                    label = { Text("Time (hh:mm)") }
                )
                Button(
                    modifier = Modifier.fillMaxWidth().padding(end = 4.dp).weight(1f),
                    onClick = { store.add(store.messageState, store.timeState); onMessagesChanged() }
                ) {
                    Text("Add")
                }
            }

            Spacer(modifier.height(8.dp))

            LazyColumn {
                store.state.forEach { message ->
                    item { ItemMessage(message, { store.remove(it.id); onMessagesChanged()} ) }
                }
            }
        }
    }
}
