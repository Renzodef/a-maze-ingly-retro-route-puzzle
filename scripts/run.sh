#!/bin/bash

# Navigate to the project root directory
echo "Navigating to the project root directory..."
cd "$(dirname "$0")/.."

# Source SDKMAN! environment
echo "Sourcing the SDKMAN environment..."
export SDKMAN_DIR="$HOME/.sdkman"
[[ -s "$SDKMAN_DIR/bin/sdkman-init.sh" ]] && source "$SDKMAN_DIR/bin/sdkman-init.sh"
echo "SDKMAN environment sourced."

# Check if Java is in the PATH
echo "Checking if Java is in the PATH..."
if java -version 2>&1 >/dev/null; then
    echo "Java found:"
    java -version
else
    echo "Java not found. Cannot compile or run the application."
    exit 1
fi

# Check if Maven is in the PATH
echo "Checking if Maven is in the PATH..."
if which mvn >/dev/null; then
    echo "Maven found:"
    mvn -v

    # Compile the application
    echo "Compiling the application..."
    mvn compile

    # Run the application
    echo "Running the application..."
    mvn exec:java -Dexec.mainClass="org.example.Main"
else
    echo "Maven not found. Cannot compile or run the application."
    exit 1
fi
