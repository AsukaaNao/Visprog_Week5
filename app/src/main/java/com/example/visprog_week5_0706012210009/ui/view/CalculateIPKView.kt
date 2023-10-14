package com.example.visprog_week5_0706012210009.ui.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.visprog_week5_0706012210009.viewModel.HitungIPKViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.visprog_week5_0706012210009.model.CourseUIState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun calculateIPKView(
    viewModel: HitungIPKViewModel = viewModel()
) {
    var sks by remember { mutableStateOf("") }
    var score by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var courseList by remember {
        mutableStateOf(emptyList<CourseUIState>())
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        LazyColumn() {
            item {
                Text(
                    text = "Courses",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Total SKS: ${viewModel.sumSKS()}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "IPK: ${viewModel.sumIPK()}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height( if (viewModel.isScoreValid(score) && viewModel.isSksValid(sks)) 70.dp else 80.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = sks,
                            onValueChange = {
                                sks = it
                            },
                            label = {
                                Text("SKS", modifier = Modifier.padding(start = 5.dp))
                            },
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = androidx.compose.ui.text.input.ImeAction.Next
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Blue,
                                unfocusedBorderColor = Color.Gray,
                                textColor = Color.Black
                            )
                        )
                        Text(
                            text = "SKS must be between 1 and 10",
                            modifier = Modifier
                                .height(23.dp)
                                .padding(start = 5.dp)
                                .alpha(if (viewModel.isSksValid(sks)) 0f else 1f), // Set alpha to 0 when valid, 1 when invalid
                            color = Color.Red,
                            fontSize = 10.sp
                        )


                    }

                    Column(
                        Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = score,
                            onValueChange = {
                                score = it
                            },
                            label = {
                                Text("Score", modifier = Modifier.padding(start = 5.dp))
                            },
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .padding(start = 10.dp),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = androidx.compose.ui.text.input.ImeAction.Next
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Blue,
                                unfocusedBorderColor = Color.Gray,
                                textColor = Color.Black
                            )
                        )
                        if (!viewModel.isScoreValid(score)) {
                            Text(
                                "Score must be between 1 and 4",
                                modifier = Modifier
                                    .height(23.dp)
                                    .padding(start = 10.dp)
                                    .alpha(if (viewModel.isScoreValid(score)) 0f else 1f),
                                color = Color.Red,
                                fontSize = 10.sp
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        label = {
                            Text("Name", modifier = Modifier.padding(start = 5.dp))
                        },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(2f)
                            .padding(end = 10.dp)
                            .align(Alignment.CenterVertically),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = androidx.compose.ui.text.input.ImeAction.Done
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                            textColor = Color.Black
                        )
                    )

                    Button(
                        onClick = {
                            if (viewModel.isSksValid(sks) && viewModel.isScoreValid(score)) {
                                viewModel.addCourse(sks, score, name)
                                sks = ""
                                score = ""
                                name = ""
                                courseList = viewModel.getCourseList()
                            }
                        },
                        modifier = Modifier
                            .height(65.dp)
                            .width(90.dp)
                            .background(Color.Transparent),
                        shape = RoundedCornerShape(50.dp),
                        enabled = sks.isNotBlank() && score.isNotBlank() && name.isNotBlank() && viewModel.isSksValid(
                            sks
                        ) && viewModel.isScoreValid(score)
                    ) {
                        Text(
                            text = "+",
                            color = Color.White,
                            fontSize = 30.sp,
                            modifier = Modifier.background(Color.Transparent),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(courseList) { course ->
                CourseCard(course = course) {
                    Toast.makeText(
                        context,
                        "Course ${course.name} succesfully deleted",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.deleteCourse(course = course)
                    courseList = viewModel.getCourseList()
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun CourseCard(
    course: CourseUIState,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Name: ${course.name}",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "SKS: ${course.sks}",
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "Score: ${course.score}",
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.weight(1f)) // Create space between course details and icon
//
            IconButton(
                onClick = { onDeleteClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun calculateIPKPreview() {
    calculateIPKView(HitungIPKViewModel())
}