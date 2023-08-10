# Main Class, project name, change as preferred
MAIN_CLASS := a3b.climate.Main
PRJ_NAME := UniLab

# Directories for where to find build files, libraries and sources, respectively
MANIFEST := ./src/META-INF/MANIFEST.MF
BUILD_DIR := ./bin
LIB_DIR := ./lib
SRC_DIR := ./src
DOC_DIR := ./doc/javadoc

# Names for generated files
TARGET_JAR := $(BUILD_DIR)/$(PRJ_NAME).jar
TARGET_EXE := $(BUILD_DIR)/$(PRJ_NAME)

# Classes are generated in a subdirectory
CLASS_DIR := $(BUILD_DIR)/class

# Classpath for build time and run time
BUILD_CP = "$(SRC_DIR)/:$(LIB_DIR)/*"
RUN_CP = "$(CLASS_DIR)/:$(LIB_DIR)/*"

# List of source files
SRCS := $(shell find $(SRC_DIR) -name '*.java' -print)

# List of class files
CLS := $(SRCS:$(SRC_DIR)/%.java=$(CLASS_DIR)/%.class)
CLS_LIST :=

# List of jar libraries
LIBS := $(shell find $(LIB_DIR) -name '*.jar' -print)

# Compile and run project
run: classes
	java -cp $(RUN_CP) $(MAIN_CLASS)

# Compile and run jar file
runjar: jar
	java -jar $(TARGET_JAR)

# Compile sources to classes
classes: $(CLS)
	if [ ! -z "$(CLS_LIST)" ]; then \
		javac -d $(CLASS_DIR) -cp $(BUILD_CP) $(CLS_LIST); \
	fi

$(CLS): $(CLASS_DIR)/%.class: $(SRC_DIR)/%.java
	   $(eval CLS_LIST+=$$<)

# Extract libraries to class directory
libraries: $(LIBS)
	for lib in $(LIBS); do \
		jar --extract --file $$lib; \
	done
	rm -rf ./META-INF $(CLASS_DIR)/org
	mv -f ./org $(CLASS_DIR)/

# Generate .jar artifact
jar: classes libraries
	jar --create --file $(TARGET_JAR) --manifest $(MANIFEST) -C $(CLASS_DIR) .

# Package jar into an executable
executable: jar
	echo '#!/usr/bin/java -jar' > $(TARGET_EXE)
	cat $(TARGET_JAR) >> $(TARGET_EXE)
	chmod +x $(TARGET_EXE)

# Generate documentation
docs: $(SRCS)
	javadoc -d $(DOC_DIR) -cp $(BUILD_CP) $(SRCS)

# Clean BUILD_DIR by deleting it
clean:
	rm -r $(BUILD_DIR)

# Delete documentation
cleandoc:
	rm -r $(DOC_DIR)

all: classes libraries jar docs

cleanall: clean cleandoc

.PHONY: classes run jar clean docs cleandoc libraries executable
