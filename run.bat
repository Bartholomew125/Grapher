@echo off
echo Compiling
javac Main.java
echo Running
java Main.java
echo Deleting .class files
del *.class
