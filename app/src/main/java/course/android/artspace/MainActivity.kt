package course.android.artspace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import course.android.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var imageId by remember {
        mutableStateOf(1)
    }

    var imageResource = R.drawable.golden_gate_bridge
    var textResource = R.string.golden_gate_text
    var artworkResource = R.string.golden_gate_artwork_name
    var yearResource = R.string.golden_gate_year


    when (imageId) {
        1 -> {
            imageResource = R.drawable.golden_gate_bridge
            textResource = R.string.golden_gate_text
            artworkResource = R.string.golden_gate_artwork_name
            yearResource = R.string.golden_gate_year

        }

        2 -> {
            imageResource = R.drawable.capitol
            textResource = R.string.capitol_text
            artworkResource = R.string.capitol_artwork_name
            yearResource = R.string.capitol_year
        }

        else -> {
            imageResource = R.drawable.liberty_island
            textResource = R.string.liberty_island_text
            artworkResource = R.string.liberty_island_artwork_name
            yearResource = R.string.liberty_island_year
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(29.dp))
        Card(
            modifier = Modifier
                .background(Color.Yellow),
            elevation = CardDefaults.cardElevation(15.dp),
            shape = RoundedCornerShape(0)
        ) {
            Image(
                modifier = Modifier
                    .padding(24.dp)
                    .shadow(5.dp)
                    .background(Color.Magenta),
                painter = painterResource(id = imageResource),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(29.dp))
        Row(
            modifier = Modifier
                .background(color = Color.LightGray)
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(id = textResource))
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = artworkResource))
                        }
                        withStyle(style = SpanStyle(
                            fontSize = 12.sp
                        )) {
                            append(" (" + stringResource(id = yearResource) + ")" )
                        }
                    }
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(40.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.width(200.dp),
                onClick = {
                    imageId--
                    if (imageId <= 0) imageId = 4
                    Log.d("buttonAction", imageId.toString())
                }
            ) {
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.weight(0.6f))
            Button(
                modifier = Modifier.width(200.dp),
                onClick = {
                    imageId++
                    if (imageId >= 5) imageId = 1
                    Log.d("buttonAction", imageId.toString())
                }
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}