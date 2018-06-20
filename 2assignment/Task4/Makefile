JCC = javac
JCR = java
JFLAGS = -cp
D= -d
DIR=classes/
JAR = ../lib/commons-math3-3.6.1/*:../lib/jogamp-all-platforms/jar/*:

default: clean
	$(JCC) $(D) $(DIR) $(JFLAGS) $(JAR). Task.java

run:
	$(JCR) $(JFLAGS) $(JAR)$(DIR) Task

clean:
	$(RM) $(DIR)*.class

run all:default run
