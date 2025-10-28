package com.example.slideshow

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.slideshow.ui.theme.SlideshowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideshowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SlideshowLayout(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SlideshowLayout(modifier: Modifier = Modifier) {
    val images = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5
    )

    val captions = listOf(
        "This is image 1",
        "This is image 2",
        "This is image 3",
        "This is image 4",
        "This is image 5"
    )

    var index by remember { mutableStateOf(0) }
    var numberInput by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "CSI Slideshow",
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(Alignment.Start),
            style = MaterialTheme.typography.headlineSmall
        )

        Image(
            painter = painterResource(id = images[index]),
            contentDescription = "slideshow image",
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        )

        Text(
            text = captions[index],
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    index = if (index - 1 < 0) images.lastIndex else index - 1
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) { Text("Back") }

            Spacer(Modifier.width(12.dp))

            Button(
                onClick = {
                    index = (index + 1) % images.size
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) { Text("Next") }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = numberInput,
                onValueChange = { numberInput = it },
                label = { Text("Enter image # (1..${images.size})") },
                singleLine = true,
                modifier = Modifier
                    .weight(2f)
                    .heightIn(min = 56.dp)
            )

            Spacer(Modifier.width(12.dp))

            Button(
                onClick = {
                    val n = numberInput.toIntOrNull()
                    if (n == null || n !in 1..images.size) {
                        Toast.makeText(
                            context,
                            "Please enter a number from 1 to ${images.size}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        index = n - 1
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            ) { Text("Go") }
        }

        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SlideshowPreview() {
    SlideshowTheme {
        SlideshowLayout()
    }
}
