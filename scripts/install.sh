#!/bin/bash

# Navigate to the project root directory
echo "Navigating to the project root directory..."
cd "$(dirname "$0")/.."

# Install SDKMAN!
echo "Installing SDKMAN..."
curl -s "https://get.sdkman.io" | bash && source "$HOME/.sdkman/bin/sdkman-init.sh"
echo "SDKMAN installed successfully."

# Install Java Temurin distribution version 11.0.21 using SDKMAN!
echo "Installing Java 11.0.21-tem (Temurin)..."
sdk install java 11.0.21-tem
echo "Java 11.0.21-tem (Temurin) installed successfully."

# Install Maven using SDKMAN!
echo "Installing Maven..."
sdk install maven
echo "Maven installed successfully."

# Clean and install the project dependencies using Maven
echo "Cleaning and installing project dependencies..."
mvn clean install

# Final completion message
echo "You have configured the environment successfully!"
