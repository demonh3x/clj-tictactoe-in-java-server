# clj-tictactoe-in-java-server

## Description

The [tictactoe implementation in clojure](https://github.com/demonh3x/tictactoe.clj) running over the [http server in java](https://github.com/demonh3x/server.java)

## Dependencies

##### Execution
* Java 1.7
* my [tictactoe implementation in clojure](https://github.com/demonh3x/tictactoe.clj) (with its dependencies)
* my [http server in java](https://github.com/demonh3x/server.java)

##### Testing
* JUnit
* Hamcrest

##### Build tool
* Gradle

## Run tests
`gradle test --info`

## Compile
`gradle shadowJar` will compile the *.jar file at `build/libs/interop.jar`.

`java -jar build/libs/interop.jar` will start the server at port 8080.
