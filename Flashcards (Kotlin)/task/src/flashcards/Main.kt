package flashcards

val mutableListOfMyCards = mutableListOf<Card>()
const val ADD = "add"
const val REMOVE = "remove"
const val EXIT = "exit"
const val IMPORT = "import"
const val IMPORT_BY_ARGS = "-import"
const val EXPORT = "export"
const val EXPORT_BY_ARGS = "-export"
const val ASK = "ask"
const val LOG = "log"
const val HARDEST_CARD = "hardest card"
const val RESET_STATS = "reset stats"
var pathFileForExportByArgs: String? = null
var pathFileForImportByArgs: String? = null

fun main(args: Array<String>) {

    if (args.contains(IMPORT_BY_ARGS)) {
        pathFileForImportByArgs = args[args.indexOf(IMPORT_BY_ARGS) + 1]
        MyFile().ImportCards().import(pathFileForImportByArgs!!)
    }

    if (args.contains(EXPORT_BY_ARGS)) {
        pathFileForExportByArgs = args[args.indexOf(EXPORT_BY_ARGS) + 1]
    }

    StartProgram().start()
}