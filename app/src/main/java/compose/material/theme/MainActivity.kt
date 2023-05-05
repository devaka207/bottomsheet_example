package compose.material.theme


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.BottomSheetState

import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.material.theme.ui.theme.Material3ComposeTheme
import kotlinx.coroutines.launch
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomSheetDemo()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@Composable
fun BottomSheetDemo() {

    val context = LocalContext.current
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp),
        sheetContent = {
            //Ui for bottom sheet
            Column(
                content = {

                    Spacer(modifier = Modifier.padding(20.dp))
                    Text(
                        text = "Let's login",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color.White
                    )
                    Text(
                        text = "To save your progress. Will take less than 5 sec ",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Button(

                        onClick = {
                            Toast.makeText(context, "Login Logics", Toast.LENGTH_SHORT).show()
                        }, shape = CutCornerShape(20)) {
                        Text(
                            text = "Continue with Google",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White)

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)

                    //.background(Color(0xFF6650a4))
                    .background(
                        brush = Brush.linearGradient(colors = listOf(Color(0xFF8E2DE2), Color(0xFF4A00E0))),
                        // shape = RoundedCornerShape(cornerRadius)
                    )
                    .padding(16.dp),

            )
        },
        sheetPeekHeight = 0.dp,

    ) {

        Column(modifier = Modifier.fillMaxSize()
            .background(Color.Blue)
        ) {

            //Image(painter = painterResource(id = R.drawable.background), contentDescription = null)
            Button(
                modifier = Modifier
                    .padding(30.dp),
                onClick = {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                            Toast.makeText(context, "Bottom State Expand", Toast.LENGTH_SHORT).show()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                            Toast.makeText(context, "Bottom State collapsed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            ) {
                Text(
                    text = "Click To Toggle Bottom Sheet",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White)
            }

        }
    }
}