package flashcards

import java.util.Random
import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path

class Bot {

    companion object {
        fun readData(): String {
            val inputData = readln()
            Log.logMyHistory(inputData)
            return inputData
        }
    }

    object Talk {
        fun sayHowManyTimeToAsk() {
            val outputData = "How many times to ask?"
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayPrintTheDefinitionOf(term: String) {
            val outputData = "Print the definition of \"$term\":"
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayCorrect() {
            val outputData = "Correct!"
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayWrongButDefinitionIsExist(rightAnswer: String, rightTermForDefinition: String) {
            val outputData =
                    "Wrong. The right answer is \"$rightAnswer\", but your definition is correct for \"$rightTermForDefinition\"."
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayWrong(rightAnswer: String) {
            val outputData = "Wrong. The right answer is \"$rightAnswer\"."
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayInputTheAction() {
            val outputData =
                    "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):"
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayInputCard() {
            val outputData = "The card:"
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayInputCardDefinition() {
            val outputData = "The definition of the card:"
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayTermAlreadyExists(term: String) {
            val outputData = "The card \"$term\" already exists."
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayDefinitionAlreadyExists(definition: String) {
            val outputData = "The definition \"$definition\" already exists. Try again:"
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayPairAdded(term: String, definition: String) {
            val outputData = "The pair (\"$term\":\"$definition\") has been added."
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayWhichCard() {
            val outputData = "Which card?"
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayCardRemoved() {
            val outputData = "The card has been removed."
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayCanNotRemove(term: String) {
            val outputData = "Can't remove \"$term\": there is no such card."
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayBye() {
            val outputData = "Bye bye!"
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayFileNotFound() {
            val outputData = "File not found."
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayCardsHaveBeenLoaded(numberOfLoadedCards: Int) {
            val outputData = "$numberOfLoadedCards cards have been loaded."
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayFileName() {
            val outputData = "File name:"
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayCardSaved(numberOfCards: Int) {
            val outputData = "$numberOfCards cards have been saved."
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayCardStatisticsReset() {
            val outputData = "Card statistics have been reset."
            Log.logMyHistory(outputData)
            println(outputData)
        }

        fun sayHardestCard() {
            var outputData = ""
            val maxNumErrors =
                    mutableListOfMyCards.maxByOrNull { it.numberOfErrorInCard }?.numberOfErrorInCard
            val hardestCards =
                    mutableListOfMyCards.filter { it.numberOfErrorInCard == maxNumErrors }
            val listStringTerm = mutableListOf<String>()
            if (hardestCards.isNotEmpty() && hardestCards.size > 1 && maxNumErrors != 0) {
                hardestCards.forEach { listStringTerm.add(it.term) }
                outputData += "The hardest cards are "
                listStringTerm.forEach { outputData += if (listStringTerm.last() != it) "\"${it}\", " else "\"${it}\"" }
                outputData += ". You have $maxNumErrors errors answering them."
            } else if (hardestCards.size == 1 && maxNumErrors != 0) {
                hardestCards.forEach { listStringTerm.add(it.term) }
                outputData = "The hardest card is \"${listStringTerm[0]}\". You have $maxNumErrors errors answering it."
            } else {
                println("There are no cards with errors.")
            }
            println(outputData)
            Log.logMyHistory(outputData)
        }

        fun sayLogHasBeenSaved() = println("The log has been saved.")
    }

    object WorkWithCards {
        fun addedCard() {
            Talk.sayInputCard()
            val termToAdd = readData()

            mutableListOfMyCards.forEach {
                if (it.term == termToAdd) {
                    Talk.sayTermAlreadyExists(termToAdd)
                    return
                }
            }

            Talk.sayInputCardDefinition()
            val definition = readData()
            mutableListOfMyCards.forEach {
                if (it.definition == definition) {
                    Talk.sayDefinitionAlreadyExists(definition)
                    return
                }
            }

            mutableListOfMyCards += Card(termToAdd, definition)
            Talk.sayPairAdded(termToAdd, definition)
        }

        fun removedCard() {
            Talk.sayWhichCard()
            val termOfCardToRemove = readData()
            val mutableListToRemove = mutableListOf<Card>()
            mutableListOfMyCards.forEach {
                if (it.term == termOfCardToRemove) {
                    mutableListToRemove.add(it)
                }
            }
            if (mutableListToRemove.isNotEmpty()) {
                mutableListOfMyCards.removeAll(mutableListToRemove)
                Talk.sayCardRemoved()
            } else {
                Talk.sayCanNotRemove(termOfCardToRemove)
            }

        }

        fun ask() {
            Talk.sayHowManyTimeToAsk()
            val numberOfQuestions = readData().toInt()
            val newListOfMyCards = mutableListOf<Card>()
            newListOfMyCards.addAll(mutableListOfMyCards)
            repeat(numberOfQuestions) {
                val randomCard =
                        newListOfMyCards[Random().nextInt(newListOfMyCards.size)]
                Talk.sayPrintTheDefinitionOf(randomCard.term)
                val answer = readData()

                val isItAnswerInCards = mutableListOfMyCards.filter { it.definition == answer }

                if (answer == randomCard.definition) {
                    Talk.sayCorrect()
                } else if (isItAnswerInCards.isNotEmpty()) {
                    mutableListOfMyCards.forEach { if (it == randomCard) it.numberOfErrorInCard++ }
                    Talk.sayWrongButDefinitionIsExist(
                            randomCard.definition,
                            isItAnswerInCards[0].term
                    )
                } else {
                    mutableListOfMyCards.forEach { if (it == randomCard) it.numberOfErrorInCard++ }
                    Talk.sayWrong(randomCard.definition)
                }
                newListOfMyCards.remove(randomCard)
            }
        }

        fun resetStats() {
            mutableListOfMyCards.forEach { it.numberOfErrorInCard = 0 }
            Talk.sayCardStatisticsReset()
        }
    }

    object Log {
        fun logMyHistory(data: String) {
            val pathFileForLog = "MyLog.txt"
            if (File(pathFileForLog).exists()) {
                File(pathFileForLog).appendText("${data}\n")
            } else {
                File(pathFileForLog).writeText("${data}\n")
            }
        }

        fun log() {
            Talk.sayFileName()
            val pathFileForLog = readData()
            Files.move(Path("MyLog.txt"), Path(pathFileForLog))
            Talk.sayLogHasBeenSaved()
        }
    }

}