package flashcards

import kotlin.system.exitProcess

class StartProgram {

    fun start() {

        var readyToExit = false

        do {
            Bot.Talk.sayInputTheAction()
            when (Bot.readData()) {
                ADD -> {
                    Bot.WorkWithCards.addedCard()
                }
                REMOVE -> {
                    Bot.WorkWithCards.removedCard()
                }
                ASK -> {
                    Bot.WorkWithCards.ask()
                }
                IMPORT -> {
                    Bot.Talk.sayFileName()
                    val pathFile = Bot.readData()
                    MyFile().ImportCards().import(pathFile)
                }
                EXPORT -> {
                    Bot.Talk.sayFileName()
                    val pathFile = Bot.readData()
                    MyFile().ExportCards().export(pathFile)
                }
                LOG -> {
                    Bot.Log.log()
                }
                HARDEST_CARD -> {
                    Bot.Talk.sayHardestCard()
                }
                RESET_STATS -> {
                    Bot.WorkWithCards.resetStats()
                }
                EXIT -> {
                    readyToExit = true
                    if (pathFileForExportByArgs != null) {
                        MyFile().ExportCards().export(pathFileForExportByArgs!!)
                    }
                    Bot.Talk.sayBye()
                }
            }
        } while (!readyToExit)
        exitProcess(0)
    }
}