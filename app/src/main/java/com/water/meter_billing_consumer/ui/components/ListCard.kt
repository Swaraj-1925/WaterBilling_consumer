import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.water.meter_billing_consumer.ui.navigation.BillScreenRoute
import com.water.meter_billing_consumer.data.Bill

@Composable
fun ListCard(
    cardData: Bill,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                navController.navigate(BillScreenRoute(
                    id = cardData.id,
                    phone = cardData.phone,
                    reader_id = cardData.reader_id,
                    image_url = cardData.image_url,
                    reading_value = cardData.reading_value,
                    reading_date = cardData.reading_date,
                    due_date = cardData.due_date,
                    price = cardData.price,
                    modified = cardData.modified
                ))
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(25.dp) // Remove fillMaxSize()
            ) {
                Text(
                    text = "Reading",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .weight(1f) // Removed fill = false for proper weighting
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = " : ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = cardData.reading_value.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(25.dp) // Remove fillMaxSize()
            ) {
                Text(
                    text = "Reading Date",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = " : ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = cardData.reading_date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(25.dp) // Remove fillMaxSize()
            ) {
                Text(
                    text = "Amount",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = " : ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = cardData.price.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}
//fun convertToBill(cardData: CardData): Bill {
//    return Bill(
//        readingValue = cardData.reading.toFloatOrNull() ?: 0f,
//        readingDate = cardData.date,
//        price = cardData.amount.replace("₹", "").replace("/-", "").toFloatOrNull() ?: 0f
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun ShowListPreview() {
//    MaterialTheme {
//        Surface {
//            ListCard()
//        }
//    }
//}