package com.example.firstscreencomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.firstscreencomposable.ui.theme.FirstScreenComposableTheme



class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstScreenComposableTheme {
                MainScreen()
            }
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun MainScreen(categoryOptions: List<Category> = categoryList,
               devices: List<Device> = deviceList) {

    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(categoryOptions[0].name)
    }

    val categoryList = remember {
        mutableStateOf(devices.filter { it.category == categoryOptions[0].name })
    }

    Surface(color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {
            UserCard()

            Surface(color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {

                Row(modifier = Modifier
                    .selectableGroup()
                    .padding(
                        start = 16.dp, top = 8.dp,
                        end = 16.dp, bottom = 8.dp
                    ),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    categoryOptions.forEach { category ->
                        CategoryChipCard(category, selectedOption){ selected ->
                            onOptionSelected(selected)
                            categoryList.value = devices.filter {
                                it.category == selected
                            }
                        }
                    }
                }

            }

            Text(text = "Devices",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                style = MaterialTheme.typography.h6)

            LazyVerticalGrid(cells = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
            ){
                items(categoryList.value){device ->
                    DeviceCard(device = device)
                }
            }

        }

    }
}

@Composable
fun UserCard(){
    Card(backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        elevation = 8.dp
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Text(text = "Welcome back",
                    style = MaterialTheme.typography.subtitle1)
                Text(text = "John Wick",
                    style = MaterialTheme.typography.h4)
            }
            IconCard(icon = R.drawable.john, padding = 0.dp)
        }
    }
}

@Composable
fun IconCard(icon: Int, padding: Dp){
    Card(shape = CircleShape,
        backgroundColor = MaterialTheme.colors.primary,
        border = BorderStroke(1.dp, Color.White)
    ) {
        Image(painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun CategoryChipCard(category: Category, selectedOption: String,
                     onOptionSelected: (name: String) -> Unit){
    Card(modifier = Modifier
        .padding(8.dp)
        .selectable(
            selected = (category.name == selectedOption),
            onClick = { onOptionSelected(category.name) },
            role = Role.RadioButton
        ),
        backgroundColor = if (category.name == selectedOption)
            MaterialTheme.colors.primary
        else Color.LightGray,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(12.dp)
        ) {
            IconCard(icon = category.icon, padding = 4.dp)
            Text(text = category.name,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun DeviceCard(device: Device){
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .height(110.dp),
        elevation = 8.dp,
        border = BorderStroke(1.dp,
            if(device.status.value)
                MaterialTheme.colors.secondaryVariant
            else Color.LightGray)
    ) {
        Column(verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(painter = painterResource(id = device.icon),
                contentDescription = null)
            Text(text = device.name, style = MaterialTheme.typography.h6)

            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = if (device.status.value) "Turn On" else "Turn Off",
                    style = MaterialTheme.typography.body2)
                Switch(checked = device.status.value,
                    onCheckedChange = {newStatus -> device.status.value = newStatus})
            }
        }
    }
}


@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirstScreenComposableTheme {
        MainScreen()
    }
}