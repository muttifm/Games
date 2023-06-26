# Flashcards (Kotlin)

This is a command-line application written in Kotlin for managing flashcards. It allows you to add, remove, import, export, ask, and perform other operations on flashcards. The progress can be saved to files, and you can resume from where you left off.

## Features

- Add new flashcards with unique terms and definitions.
- Remove existing flashcards by their term.
- Import flashcards from an external file.
- Export flashcards to an external file.
- Test yourself by answering flashcard questions.
- View the hardest card(s) with the most errors.
- Reset the statistics for all flashcards.
- Save the program's log to a file.

## Usage

1. Clone the repository to your local machine.
2. Make sure you have Kotlin and Gradle installed.
3. Open the project in your preferred Kotlin IDE.
4. Build the project using the provided Gradle wrapper: `./gradlew build`.
5. Run the application: `./gradlew run --args="-import cards.txt -export saved_cards.txt"`.
   - You can specify the `-import` argument followed by the path to an external file to import flashcards.
   - You can specify the `-export` argument followed by the path to an external file to export flashcards.
6. Follow the on-screen instructions to interact with the flashcards.

## Dependencies

The project uses the following dependencies:
- Kotlin: The programming language used for the application.
- Gradle: The build system for managing dependencies and building the project.
- Moshi: A modern JSON library for Kotlin that handles serialization and deserialization of flashcards.

## Contributing

Contributions to this project are welcome. If you encounter any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
