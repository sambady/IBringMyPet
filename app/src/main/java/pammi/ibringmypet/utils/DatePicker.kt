package pammi.ibringmypet.utils

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DatePicker(
    onValueChange: (String) -> Unit = {},
    pattern: String = "yyyy-MM-dd",
    defautlValue : String = ""
) {

    var value by remember { mutableStateOf(defautlValue) }

    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = if (value.isNotBlank()) LocalDate.parse(value, formatter) else LocalDate.now()
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            value = LocalDate.of(year, month + 1, dayOfMonth).toString()
            onValueChange(value)
        },
        date.year,
        date.monthValue - 1,
        date.dayOfMonth,
    )

    TextField(
        value = value,
        onValueChange = {},
        enabled = false,
        modifier = Modifier.clickable { dialog.show() }
    )
}
