JCC = javac
JCR = java
JFLAGS = -cp
D= -d
DIR=classes/
JAR = ../lib/commons-math3-3.6.1/*:../lib/jogamp-all-platforms/jar/*:

Task: clean
	$(JCC) $(D) $(DIR) $(JFLAGS) $(JAR). Task.java

clean:
	$(RM) $(DIR)*.class

run:
	$(JCR) $(JFLAGS) $(JAR)$(DIR) Task

do: Task run
