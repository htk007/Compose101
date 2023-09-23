@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.compose101

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose101.ui.theme.Compose101Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose101Theme {
                //clickAndIncrementExample()
                SimpleNameList()
            }
        }
    }
}

@Composable
fun SimpleNameList() {
    var name by remember{
        mutableStateOf("")
    }
    var nameList by remember {
        mutableStateOf(listOf<String>())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Yellow)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .background(Color.White)
        ){
            OutlinedTextField(
                value = name,
                onValueChange = { text ->
                    name = text
                },
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f))
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                if(name.isNotBlank()){
                    nameList = nameList + name
                    name = ""
                }
            },
                modifier =  Modifier.padding(5.dp)
            ) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription =null)
            }
        }
        displayList(names = nameList)
    }
}

@Composable
fun displayList(
    names : List<String>,
    modifier:Modifier = Modifier
) {
    LazyColumn(modifier){
        items(names){ currentName ->
            Row {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription =null,
                    modifier = Modifier.padding(16.dp)

                )
                Text(
                    text = currentName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Divider()
            }
        }
    }
}

//Basics of click interaction and state flow
@Composable
fun clickAndIncrementExample(){
    var count by remember {
        mutableStateOf(0)
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ){
        Text(
            text = count.toString(),
            fontSize = 30.sp)
        Button(onClick = {
            count++;
        }) {
            Text(text = "Click me! $count")
        }
    }
}

//more effective and profitable for resource consumption and rendering for list
@Composable
fun IconDisplayingWithLazyColumn(imageVector: ImageVector, times:Int){
    LazyColumn() {
        items(times){i->
            Row {
                Icon(imageVector =imageVector , contentDescription = null )
                Icon(imageVector =imageVector , contentDescription = null )
                Icon(imageVector =imageVector , contentDescription = null )
            }
        }
    }
}
// we can display each view element by list iteratively
@Composable
fun IconDisplaying(imageVector: ImageVector, times:Int){
    Column {
        for(i in 1..times){
            Row {
                Icon(imageVector =imageVector , contentDescription = null )
                Icon(imageVector =imageVector , contentDescription = null )
                Icon(imageVector =imageVector , contentDescription = null )
            }
        }
    }
}

//image displaying example from drawable resources
@Composable
fun ImageDisplaying(){
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = null,
        modifier =  Modifier.background(Color.Black)
    )
}
//Box design example
@Composable
fun GreetingWithBox(name:String){
    Box(
        modifier = Modifier.size(400.dp)
    ) {
        Text(
            text = "Hello $name",
            color= Color.Blue,
            fontSize = 30.sp,
            modifier = Modifier.align(alignment = Alignment.BottomEnd)
        )
        Text(
            text = "So exciting!",
            fontSize = 40.sp,
            color = Color.Red,
            fontFamily = FontFamily.Cursive
        )
    }
}

// Full screen example, this function include Row and Column logic.
@Composable
fun GreetingWithFullScreen(name: String) {
Column (
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxSize(),
){

    Row {
        Text(
            text = "Hello ",
            modifier = Modifier
                .background(Color.Yellow),
            fontSize = 40.sp
        )
        Text(
            text = " $name!",
            modifier = Modifier
                .background(Color.Green),
            fontSize = 40.sp
        )
    }
    aboutMe("Hasan KAYA")
    if(name.length>5){
        IconDisplayingWithLazyColumn(Icons.Default.Favorite,3)
    }else{
        IconDisplayingWithLazyColumn(Icons.Default.FavoriteBorder,3)
    }
    IconDisplayingWithLazyColumn(Icons.Default.Lock,1)
}
}

@Composable
fun aboutMe(authorName:String){
Row(){
    Column {
        Text(text = "Name: $authorName")
        Text(text = "Title: Software Engineer")
        Text(text = "Country: Turkey")
        Text(text = "Github: htk007")
    }
}
}
// for preview 
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose101Theme {
        //GreetingWithFullScreen(name ="Compose 101")
        //GreetingWithBox("Compose 101")
        //ImageDisplaying()
        //IconDisplaying()
        //clickAndIncrementExample()
        SimpleNameList()
    }
}