# Main Class, project name, change as preferred
MAIN_CLASS := a3b.climate.Main
PRJ_NAME := UniLab
MANIFEST := ./META-INF/MANIFEST.MF

# Names for generated files
TARGET_EXEC := $(PRJ_NAME).jar
TARGET_LIB := lib$(PRJ_NAME).jar

# Directories for where to find build files, libraries and sources, respectively
BUILD_DIR := ./bin
LIB_DIR := ./lib
SRC_DIR := ./src
DOC_DIR := ./doc/javadoc

# Classes are generated in a subdirectory
CLASS_DIR := $(BUILD_DIR)/class

# Classpath for build time and run time
BUILD_CP = "$(SRC_DIR)/:$(LIB_DIR)/*"
RUN_CP = "$(CLASS_DIR)/:$(LIB_DIR)/*"

# List of source files
SRCS := $(shell find $(SRC_DIR) -name '*.java' -print)

# List of class files
CLS := $(SRCS:$(SRC_DIR)/%.java=$(CLASS_DIR)/%.class)

# Compile and run project
run: classes
	java -cp $(RUN_CP) $(MAIN_CLASS)

# Compile and run jar file
runjar: jar
	java -jar $(BUILD_DIR)/$(TARGET_EXEC)

# Compile sources to classes
classes: $(SRCS)
	javac -d $(CLASS_DIR) -cp $(BUILD_CP) $(SRCS)

# Generate .jar artifact
jar: classes
	jar --create --file $(BUILD_DIR)/$(TARGET_EXEC) --manifest $(MANIFEST) $(CLASS_DIR)

# Generate .jar artifact with no main
jarlib: classes
	jar --create --file $(BUILD_DIR)/$(TARGET_LIB) $(CLS) .

# Generate documentation
docs: $(SRCS)
	javadoc -d $(DOC_DIR) -cp $(BUILD_CP) $(SRCS)

# Clean BUILD_DIR by deleting it
clean:
	rm -r $(BUILD_DIR)

# Delete documentation
cleandoc:
	rm -r $(DOC_DIR)

all: classes jar jarlib docs

cleanall: clean cleandoc

.PHONY: classes run jar clean docs cleandoc
