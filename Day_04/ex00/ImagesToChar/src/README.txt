#How to use :

rm -rf target

mkdir target

javac -sourcepath ./src/java  ./src/java/edu/school21/printer/app/Program.java  -d ./target

#java -classpath ./target edu.school21.printer.app.Program {first_symbol} {second_symbol} {path_to_image}

java -classpath ./target edu.school21.printer.app.Program . o /Users/gtaggana/Java_Piscine/Day_04/ex00/it.bmp
