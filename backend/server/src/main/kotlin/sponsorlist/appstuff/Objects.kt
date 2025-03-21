package sponsorlist.appstuff

import io.ktor.http.*
import java.sql.Date


data class SponsorItem(val personality:Personality, val sponsorName:String,
                       val debutdPlatform:Platform,
                       var link:Url?, var code:String?, var linkToPost:Url,
                       val startDate: Date, val dateAdded:Date, var lastChecked:Date, var lastVerified:Date,
                       var valid:Boolean, var scam:Boolean);

data class Platform(
    val name: String,
    val url: String,
    val logoUrl: String,
    val description: String
)

data class Personality(
    val name: String,
    val platforms: Map<Platform, String> = emptyMap(),
    val description: String? = null
)

data class FileStructure(
    val sponsorItems: List<SponsorItem>,
    val platforms: List<Platform>,
    val personalities: List<Personality>
)