# Design and Assumptions

It is assumed that this Geometry Game is a console-based application. Therefore, to keep things light, no Spring framework was added. The only thing added was the testing framework. 

In terms of implementation:

1. Java was chosen as simply due to preference and experience. 
2. There are no layering of codes (e.g. separating them into packages) to keep things simple and more maintainable. Sometimes, simple is the best solution. 
3. Key entities of the game are identified and classes are created for them. As per Object-Oriented Programming, fields and behaviours that belongs to an entity stays within the entity.
4. TDD was done to implement the Game class.

# Running the Application

The application is built using IntelliJ on MacOS with Java 17. 

Maven is used as the dependency and build tool.

1. From the terminal/commandline: `cd geometrygame`
2. Run `mvn compile`
3. Run `mvn package`
4. Run `Java -jar target/geometrygame-0.0.1.jar` to start the game
