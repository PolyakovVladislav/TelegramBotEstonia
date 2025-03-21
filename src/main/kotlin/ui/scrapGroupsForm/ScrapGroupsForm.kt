package ui.scrapGroupsForm

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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

@Preview
@Composable
fun ScrapGroupsForm(
    modifier: Modifier
) {

    val store = remember {
        ScrapGroupsStore(configurationRepository)
    }

    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(end = 4.dp),
                value = store.groupNameState,
                onValueChange = store::editGroupName,
                label = { Text("Group for scrap") }
            )
            Button(
                onClick = { store.add(store.groupNameState) }
            ) {
                Text("Add")
            }

            Spacer(modifier.height(8.dp))

            LazyColumn {
                store.groupsState.forEach { telegramGroupData ->
                    item {
                        CardGroup( { store.remove(it.groupLink) }, telegramGroupData)
                    }
                }
            }
        }
    }
}
