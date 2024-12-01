package com.example.goalgrid.screen

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.goalgrid.components.AppButton
import com.example.goalgrid.components.InputField
import com.example.goalgrid.model.Goals

@Composable
fun GoalScreen(goalsViewModel: GoalsViewModel) {
    BuildTopForm(viewModel = goalsViewModel)
}

@Composable
fun BuildTopForm(viewModel: GoalsViewModel, callback: @Composable () -> Unit = {}) {

    val goalsList = viewModel.goalsList.collectAsState().value

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

        AppButton(buttonTitle = "Save") {
            if (titleTextFieldState.value.isNotEmpty() && descriptionTextFieldState.value.isNotEmpty()) {
                val time = Calendar.getInstance().time
                val formatter = SimpleDateFormat("E MMM dd h:mm a")
                val current = formatter.format(time)
                viewModel.addGoals(Goals(title = titleTextFieldState.value, description = descriptionTextFieldState.value, dateTime = current))
                Log.d("TAG", "the count is ${goalsList.count()}")
            }

            titleTextFieldState.value = ""
            descriptionTextFieldState.value = ""
        }

        AnimatedVisibility(goalsList.isNotEmpty()) {
            Divider(modifier = Modifier.padding(top = 10.dp))
            LazyColumn(
                modifier = Modifier
                    .padding(top = 20.dp)
            ) {
                items(goalsList) { goal ->
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

                                Text(
                                    goal.dateTime,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Light,
                                    color =  MaterialTheme.colorScheme.background)
                            }


                            IconButton(
                                onClick = {
                                    viewModel.removeGoals(goal)
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

//@Preview(showBackground = true)
//@Composable
//fun PreviewGoalScreen() {
//  BuildTopForm(viewModel = GoalsViewModel(repository = Repository(goalsDatabaseDao = GoalsDatabaseDao))) {
//
//  }
//}