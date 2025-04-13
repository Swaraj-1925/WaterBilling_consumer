import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.water.meter_billing_consumer.ui.navigation.SigninScreenRoute
import com.water.meter_billing_consumer.ui.viewmodel.HomeScreenViewModel
import androidx.compose.runtime.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: HomeScreenViewModel= hiltViewModel(),
    navController: NavController
){
    LaunchedEffect(Unit) {
        viewModel.getBills(allBills = true)
    }

    val cardDataList by viewModel.bill.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(text ="BILLS",
                style = MaterialTheme.typography.headlineMedium) },
            actions = {
                IconButton(
                    onClick = {
                        viewModel.logout()
                        navController.navigate(SigninScreenRoute)
                              },
                    modifier = Modifier
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Log out"
                    )
                }
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            items(cardDataList.size) { index ->
                val bill = cardDataList[index]
                if (bill != null) {
                    ListCard(
                        cardData = bill,
                        navController = navController
                    )
                }
            }
        }
    }
}

//@Composable
//@Preview
//fun ListScreenPreview(){
//    MaterialTheme{
//        Surface {
//            ListScreen()
//        }
//    }
//}
