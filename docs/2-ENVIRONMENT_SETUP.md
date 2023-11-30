# Environment Setup

After analyzing the challenge, I focused on setting up the development environment with the following key steps:

## 1. Maven Project Template Creation

- **Creating the Project**: Started by creating a Maven project template, which included a `Main` class and a `MainTest` class.
- **Adding Dependencies**: Added JUnit as a dependency in the `pom.xml` file, sourced from Maven Central. This was crucial for setting up the testing framework.

## 2. Modifying `install.sh` Script

- **Purpose**: This script prepares the Java and Maven environment for the project.
- **Installing Java 11 with SDKMAN!**: Used SDKMAN! to install Java 11 (Temurin Distribution).
- **Installing Maven**: Installed Maven via SDKMAN! for managing project dependencies and builds.
- **Building Project Dependencies**: Handled building the project dependencies from the `pom.xml` file.

## 3. Updating `tests.sh` Script

- **Consistency Checks**: Included checks to ensure Java and Maven were correctly installed and configured.
- **Running Tests**: Used `mvn test` to execute all JUnit tests in the `src/test` directory.

## 4. Modifying `run.sh` Script

- **Consistency Checks**: Included checks to ensure Java and Maven were correctly installed and configured.
- **Compiling the Project**: Compiled the project, creating an executable.
- **Executing the Application**: Responsible for running the application by executing the `Main` class.

I also included detailed comments inside the 3 scripts contained inside the `scripts` folder,
explaining step by step the process for each one.

After all the steps, I tried the environment setup on Hackerrank, and it was successful.
