# Airline Neueda

## Running

1) `mvn clean install`
2) `mvn exec:java -Dexec.mainClass=com.diego.Main -Dexec.args="inputs/input.txt"` - example the inputs file on this repo /inputs

## Approach

My focus was on implementing the logic for filling the spaces on the airplane.
I started the project with baby steps/goals:

1) Load the file
2) Convert the file on a matrix
3) Create a plane matrix
4) Fill the plane just with the people together - without considering the window
5) Fill the plane with the window
6) Fill the plane separating people
7) Calculate the satisfaction


## Assumptions

1) The project will always receive a valid file
2) The file will never have more people than the header.
    e.g., if the header informs 4x5 - then you will not have more than 20 people