package flashcards

import com.squareup.moshi.KotlinJsonAdapterFactory
import java.io.File
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class MyFile {

    val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    val type = Types.newParameterizedType(List::class.java, Card::class.java)
    val cardAdapter = moshi.adapter<List<Card>>(type)

    inner class ImportCards {
        fun import(pathFile: String) {
            if (!File(pathFile).exists()) {
                Bot.Talk.sayFileNotFound()
            } else {
                var countImportedCard = 0
                cardAdapter.fromJson(File(pathFile).readLines()[0])
                        ?.forEach {
                            countImportedCard++
                            mutableListOfMyCards.add(it)
                        }
                Bot.Talk.sayCardsHaveBeenLoaded(countImportedCard)
            }
        }
    }

    inner class ExportCards {
        fun export(pathFile: String) {
            val fileToExport = File(pathFile)
            fileToExport.writeText(cardAdapter.toJson(mutableListOfMyCards))
            Bot.Talk.sayCardSaved(mutableListOfMyCards.size)
        }
    }
}