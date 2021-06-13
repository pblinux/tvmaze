package me.pblinux.tvmaze.data.models.show

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

val showSampleJson = "{\n" +
        "\t\"id\": 1,\n" +
        "\t\"url\": \"https://www.tvmaze.com/shows/1/under-the-dome\",\n" +
        "\t\"name\": \"Under the Dome\",\n" +
        "\t\"type\": \"Scripted\",\n" +
        "\t\"language\": \"English\",\n" +
        "\t\"genres\": [\"Drama\", \"Science-Fiction\", \"Thriller\"],\n" +
        "\t\"status\": \"Ended\",\n" +
        "\t\"runtime\": 60,\n" +
        "\t\"averageRuntime\": 60,\n" +
        "\t\"premiered\": \"2013-06-24\",\n" +
        "\t\"officialSite\": \"http://www.cbs.com/shows/under-the-dome/\",\n" +
        "\t\"schedule\": {\n" +
        "\t\t\"time\": \"22:00\",\n" +
        "\t\t\"days\": [\"Thursday\"]\n" +
        "\t},\n" +
        "\t\"rating\": {\n" +
        "\t\t\"average\": 6.6\n" +
        "\t},\n" +
        "\t\"weight\": 96,\n" +
        "\t\"network\": {\n" +
        "\t\t\"id\": 2,\n" +
        "\t\t\"name\": \"CBS\",\n" +
        "\t\t\"country\": {\n" +
        "\t\t\t\"name\": \"United States\",\n" +
        "\t\t\t\"code\": \"US\",\n" +
        "\t\t\t\"timezone\": \"America/New_York\"\n" +
        "\t\t}\n" +
        "\t},\n" +
        "\t\"webChannel\": null,\n" +
        "\t\"dvdCountry\": null,\n" +
        "\t\"externals\": {\n" +
        "\t\t\"tvrage\": 25988,\n" +
        "\t\t\"thetvdb\": 264492,\n" +
        "\t\t\"imdb\": \"tt1553656\"\n" +
        "\t},\n" +
        "\t\"image\": {\n" +
        "\t\t\"medium\": \"https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg\",\n" +
        "\t\t\"original\": \"https://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg\"\n" +
        "\t},\n" +
        "\t\"summary\": \"<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>\",\n" +
        "\t\"updated\": 1621201742,\n" +
        "\t\"_links\": {\n" +
        "\t\t\"self\": {\n" +
        "\t\t\t\"href\": \"https://api.tvmaze.com/shows/1\"\n" +
        "\t\t},\n" +
        "\t\t\"previousepisode\": {\n" +
        "\t\t\t\"href\": \"https://api.tvmaze.com/episodes/185054\"\n" +
        "\t\t}\n" +
        "\t}\n" +
        "}"

val showSample = Json{ignoreUnknownKeys = true}.decodeFromString<Show>(showSampleJson)