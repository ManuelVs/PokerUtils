# HJAPracticas

This repository contains the source code for all the exercises in HJA.
All files must be encoded in UTF-8.

This project can be loaded in any IDE supporting gradle, like IntelliJ, VSCode or even simple command line executing `./gradlew` !

In the future build and tests will be automatized with `wercker` or `jenkins`.

## Build instructions

This project dependencies are managed by gradle. See `build.gradle` and the rest of this document for more details.

### Source

Sources are self-contained, there is no need to include additional libraries to compile.

### Tests

Tests are implemented using the following libraries:

- org.junit.jupiter:junit-jupiter-api:5.3.1
- org.junit.jupiter:junit-jupiter-params:5.3.1

And its corresponding dependencies.
