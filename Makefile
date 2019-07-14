
run: all
	java -cp . Simulation

all:
	javac *.java

clean:
	rm -f *.class
