package sponsorlist.appstuff

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.FileWriter
import java.util.Date


interface IDBHandler {
    companion object {
        const val DATA_PATH = "/data";
        const val SPONSOR_ITEMS = "sponsorItems";
        const val PERSONALITIES = "personalities";
        const val PLATFORMS = "platforms";
    }

    public fun getSponsorList(personality: String? =null,
                              sponsor: String? = null,
                              platform:String? =null,
                              code:String?=null,
                              post:String?=null,
                              start: Date? = null,
                              added:Date? = null,
                              verified:Date? = null,
                              valid:Boolean?=null,
                              scam:Boolean?=null): List<SponsorItem>;
    public fun addSponsorItem(item: SponsorItem): String;
    public fun addSponsorItems(items: SponsorItemList): SponsorItem {
        items.list.forEach { it -> if (addSponsorItem(it) != "") else return it }

        return SponsorItem();
    }
    public fun removeSponsorItem(personality: String ="",
                                 sponsor: String = "",
                                 platform:String="",
                                 start: Date = Date(),
                                 added:Date = Date(),
                                 scam:Boolean?=null): SponsorItem;

    public fun getPersonalityList(): List<Personality>;
    public fun addPersonality(person: Personality): Unit;
    public fun removePersonality(): Personality;

    public fun getPlatformList(): List<Platform>;
    public fun addPlatform(plat: Platform): Unit;
    public fun removePlatform(): Platform;

    public fun addFromFile(file: File);
}


//class DBHandeler {
//    companion object {
//        const val path = "./data/sponsorsList.json";
//    }
//
//
//    val gson = Gson()
//    val sponsorsList: MutableList<SponsorItem> = mutableListOf<SponsorItem>()
//    val personalities: MutableSet<Personality> = mutableSetOf<Personality>()
//    val platforms: MutableSet<Platform> = mutableSetOf<Platform>()
//
//
//    public constructor() {
//        readFromFile();
//    }
//
//    fun readFromFile() {
//        val fileReader = try {
//            FileReader(path)
//        } catch (ex: FileNotFoundException) {
//            File(path).apply {
//                this.parentFile.mkdirs()
//                writeToFile()
//            }
//            FileReader(path)
//        }
//
//        val fileStream = if (fileReader != null) fileReader.buffered() else object {}.javaClass.getResourceAsStream(path)?.bufferedReader()
//        val jsonReader = JsonReader(fileStream)
//
//        try {
//            var type = object : TypeToken<Map<String, List<Any>>>() {}.type
//            val jsonMap: Map<String, List<Any>> = gson.fromJson(jsonReader, type);
//            sponsorsList.addAll(jsonMap["sponsorsListItem"] as MutableList<SponsorItem>);
//            personalities.addAll((jsonMap["personalities"] as MutableList<Personality>));
//            platforms.addAll(jsonMap["platforms"] as MutableList<Platform>);
//        } finally {
//            jsonReader.close()
//        }
//
//    }
//
//    fun writeToFile() {
//        val writer = FileWriter(path)
//        val dataMap = mapOf(
//            "sponsorsListItem" to sponsorsList,
//            "platforms" to platforms,
//            "personalities" to personalities
//        )
//
//        gson.toJson(dataMap, writer)
//
//        writer.close()
//    }
//
//    fun sponsorsListItemBySponsorName(key: String): List<SponsorItem> {
//        return sponsorsList.filter { it.sponsor == key};
//    }
//
//    fun personalityBySponsorName(key: String): List<Personality> {
//        return personalities.filter { it.name == key};
//    }
//
//    fun addToList(listName: String, item: Any) {
//        when (listName) {
//            "sponsorsLink", "sponsorsList" -> addSponsorLink(item as SponsorItem)
//            "personality" -> addPersonality(item as Personality)
//            "platform" -> addPlatform(item as Platform)
//        }
//    }
//
//    fun addSponsorLink(item: SponsorItem) {
//        this.sponsorsList.add(item)
//        writeToFile()
//    }
//
//    fun addPersonality(person: Personality) {
//        this.personalities.add(person)
//        writeToFile();
//    }
//
//    fun addPlatform(platform: Platform) {
//        this.platforms.add(platform)
//        writeToFile()
//    }
//
//    fun getFile(): File {
//        return File(path);
//    }
//
//    fun addFromFile(file: File) {
//        val data = gson.fromJson(file.readText(), FileStructure::class.java)
//        if (!data.personalities.isNullOrEmpty()) {
//            for (p: Personality in data.personalities) {
//                this.addPersonality(p);
//            }
//        }
//        if (!data.sponsorItems.isNullOrEmpty()) {
//            for (s: SponsorItem in data.sponsorItems) {
//                this.addSponsorLink(s);
//            }
//        }
//        if (!data.platforms.isNullOrEmpty()) {
//            for (p: Platform in data.platforms) {
//                this.addPlatform(p);
//            }
//        }
//
//        writeToFile()
//    }
//}