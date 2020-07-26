NAME=Tarea
BUILD_PATH=build
BIN_PATH=$(BUILD_PATH)/bin
JAR_PATH=$(BUILD_PATH)/jar

main: clean

tarea:
	mkdir -p $(BIN_PATH)
	javac src/main/java/inf/junit/*.java	 -d $(BIN_PATH)
	mkdir -p build/jar/
	jar cfm $(JAR_PATH)/$(NAME).jar manifest -C $(BIN_PATH) .
	java -jar $(JAR_PATH)/$(NAME).jar
	rm -rf $(BUILD_PATH)

clean:
	rm -rf $(BUILD_PATH)
