# HJAPracticas

This repository contains the source code for all the exercises in HJA.
All files must be encoded in UTF-8.

This project can be loaded in any IDE supporting gradle, like IntelliJ, VSCode... You can even use a simple text editor and use the `./gradlew` command!

The three `practica{1,2,3}-master` branches contains the solution of the exercises.

The `master` branch contains a final result of the three practices, a library. This library can help you represent cards, hands and boards, calculate the best hands and the equity of each player given some cards.

This is not a performance implementation of poker problems. It aims to show a simple algorithm to solve some poker problems.

## Build instructions

This project dependencies are managed by gradle. For more information, you can see `build.gradle`.

You can generate a `jar` of this library executing `./gradlew jar` and use it on your project (you may want to change the name).
Remember that this library has a MIT license.

### Source

Sources are self-contained, there is no need to include additional libraries to compile.

### Tests

Tests are implemented using the following libraries:

- org.junit.jupiter:junit-jupiter-api:5.3.1
- org.junit.jupiter:junit-jupiter-params:5.3.1

And its corresponding dependencies.

You can execute the tests by writting `./gradlew test`.