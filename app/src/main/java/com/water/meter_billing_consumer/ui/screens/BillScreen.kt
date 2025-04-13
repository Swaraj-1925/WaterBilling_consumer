import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.water.meter_billing_consumer.data.Bill

data class Customer(
    val id: Int = 6464,
    val phone: Int = 1234567890,
    val email: String = "swaraj@example.com",
    val name: String = "Swaraj",
    val address: String = "123 Main St, City",
    val hashedPassword: String = "hashed123"
)



@Composable
fun BillScreen(
    bill: Bill
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top section with User Info
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Water Bill",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
//        Text(
//            text = "For th",
//            textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.tertiary
//        )
        Spacer(modifier = Modifier.height(16.dp))

        // Bill Details Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
//                Text(text = "Bill Number:", style = MaterialTheme.typography.bodyMedium)
//                Text(text = "Not Available", style = MaterialTheme.typography.bodySmall) // Dummy
//                Spacer(modifier = Modifier.height(8.dp))

//                Text(text = "Address:", style = MaterialTheme.typography.bodyMedium)
//                Text(text = bill, style = MaterialTheme.typography.bodySmall)
//                Spacer(modifier = Modifier.height(8.dp))

//                Text(text = "Phone:", style = MaterialTheme.typography.bodyMedium)
//                Text(text = customer.phone.toString(), style = MaterialTheme.typography.bodySmall)
//                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "User ID : ${bill.phone}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Is modified: ${if (bill.modified) "yes" else "no"}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Reading Date:", style = MaterialTheme.typography.bodyMedium)
                Text(text = bill.reading_date, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Total Units Used:", style = MaterialTheme.typography.bodyMedium)
                Text(text = bill.reading_value.toString(), style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Amount:", style = MaterialTheme.typography.bodyMedium)
                Text(text = "₹${bill.price}/-", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Due Date:", style = MaterialTheme.typography.bodyMedium)
                Text(text = bill.due_date, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = bill.image_url,
                    contentDescription = "Water Meter Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Pay Now", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun BillScreenPreview() {
//    MaterialTheme {
//        Surface {
//            BillScreen()
//        }
//    }
//}