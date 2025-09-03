#!/bin/bash
javac -d ./class ./src/*.java
java -classpath ./class Main
