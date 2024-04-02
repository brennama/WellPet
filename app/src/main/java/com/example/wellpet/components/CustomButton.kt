import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    width: Dp = ButtonDefaults.MinWidth,
    shape: CornerBasedShape = MaterialTheme.shapes.medium.copy(
        topStart = CornerSize(12.dp),
        topEnd = CornerSize(12.dp),
        bottomStart = CornerSize(12.dp),
        bottomEnd = CornerSize(12.dp)
    ),
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
) {
    Button(
        onClick = onClick,
        shape = shape,
        modifier = modifier.width(width),
        colors = colors,
        contentPadding = contentPadding
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium
        )
    }
}