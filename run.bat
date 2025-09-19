@echo off
javac -d ./class ./src/*.java
java -classpath ./class Main
