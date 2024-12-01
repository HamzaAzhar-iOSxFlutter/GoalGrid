package com.example.goalgrid.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goalgrid.components.InputField
import com.example.goalgrid.model.Goals

@Composable
fun GoalScreen() {
        val goals = remember {
        mutableStateListOf<Goals>()
    }

    BuildTopForm(goals = goals)
}

@Composable
fun BuildTopForm(goals: MutableList<Goals>, callback: @Composable () -> Unit = {}) {

    val titleTextFieldState = remember {
        mutableStateOf("")
    }

    val descriptionTextFieldState = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        InputField(
            valueState = titleTextFieldState,
            labelId = "Title",
            enabled = true,
            isSingleLine = true
        )

        InputField(
            valueState = descriptionTextFieldState,
            labelId = "Description",
            enabled = true,
            isSingleLine = true
        )

        Button(
            onClick = {
                if (titleTextFieldState.value.isNotEmpty() && descriptionTextFieldState.value.isNotEmpty()) {
                    goals.add(Goals(title = titleTextFieldState.value, description = descriptionTextFieldState.value))
                }

                titleTextFieldState.value = ""
                descriptionTextFieldState.value = ""
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onBackground)
        ) {
            Text("Save")
        }

        AnimatedVisibility(!goals.isEmpty()) {
            Divider(modifier = Modifier.padding(top = 10.dp))
            LazyColumn(
                modifier = Modifier
                    .padding(top = 20.dp)
            ) {
                items(goals) { goal ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            .padding(top = 5.dp),
                        shape = RoundedCornerShape(6.dp),
                        elevation = CardDefaults.cardElevation(5.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Text(
                                    goal.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold,
                                    color =  MaterialTheme.colorScheme.background)

                                Text(
                                    goal.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Light,
                                    color =  MaterialTheme.colorScheme.background)
                            }

                            IconButton(
                                onClick = {
                                    goals.remove(goal)
                                },
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(8.dp)
                            ) {
                                Surface(
                                    shape = CircleShape,
                                    tonalElevation = 10.dp,
                                    shadowElevation = 10.dp,
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Cancel",
                                        tint = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGoalScreen() {
    BuildTopForm(
        goals = mutableListOf(
            Goals(title = "Preview Title 1", description = "Preview Description 1"),
            Goals(title = "Preview Title 2", description = "Preview Description 2")
        )
    )
}