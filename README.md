# Minesweeper #

This was the final assignment for CS101 at Mount Holyoke College in the spring semester of 2010.

## Project structure ##

All of the source files are in the `/src` directory. These are the classes:

* `MinesweeperApplication`: main entry point for running the application
* `Minefield`: controls the game logic and keeps track of a matrix of MineBoxes
* `MineBox`: logical representation of a single box on the minefield
* `MinesweeperPanel`: graphical representation of a Minefield
* `MinesweeperTile`: graphical representation of a MineBox
* `TestMineSweeper`: for testing the game logic on the command line
* `MinesweeperApplet`: outdated technology; do not use. :)

**Note:** `MinesweeperApplet` can only be used for embedding into a webpage so that the game can be played via a browser plugin. Instead, the app should be run as a Java application via its main class, `MinesweeperApplication`.

## Compiling and running the application ##

You can import all the classes except `MinesweeperApplet` into a Java IDE (Eclipse, NetBeans, IntelliJ) and build/run the application from there.

If you are using a machine that has a bash shell and a Java compiler installed, but no Java IDE, you can execute `build.sh` to build the application into a JAR file and `run.sh` to run the JAR file as a Java application.

## Playing the game ##

Click on a tile to reveal it. If it is a mine, it will explode and end the game.

Since this was written by a first-semester computer science student, it has no bells and whistles like an ability to flag mines or restart the game.