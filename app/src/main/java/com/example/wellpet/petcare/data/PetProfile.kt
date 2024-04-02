import android.provider.CalendarContract.CalendarCache.URI
import java.time.LocalDate


data class PetProfile(
    val userId: String,
    val petId: String,
    val petName: String,
    val species: String,
    val breed: String,
    val temperament: String,
    val vaccinationStatus: String,
) {
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )
}