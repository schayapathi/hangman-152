# Hangman
Group Members: Swati Chayapathi, Soyeon Wang, Sonaal Thaker, Alexa Kegarice

### Implementation
Hangman is traditionally a paper and pencil word guessing game that usually requires 2 players: 1 guessing the word and 1 providing the word. This project is designed to play the game with only 1 player and the computer serving as the traditional second player that provides the word and draws the hangman. The game uses a basic graphical user interface(GUI) in Java with JTextAreas to reveal letters and JButtons to allow the user to guess. We also added a feature where the player can finish the game by guessing the whole word at once. 

### Design
In our project, we used the Model-View-Controller pattern. Our model consisted of three variables including String word, String inProgress (keeps track of user’s progress)  & an integer representing the number of guesses left in the game. During the design process, we felt that this would be the best design pattern to be applied to our game since we would have 2 separate views. BoardView was the main view that our user would be interacting with in order to guess letters and see their progress in the word. GallowView was the second view that would draw another Hangman body part after every incorrect guess. Both of these views would need to be updated every time the user has guessed, hence the use of MVC. For the sake of our game, our controller was within one of the view classes (BoardView). This was necessary as the controller is responsible for notifying the model of the user’s input and we were working with the Java Swing and JComponents whose input we would need to access within the same class.

###Usage
To play the game in an IDE, ensure that all .java files are within the same package and that words.txt is in a source folder. Then, run RunHangman.java.

To run it from your terminal, ensure that all files are within the same directory.
Compile RunHangman.java:
```
javac RunHangman.java
```
Run RunHangman.java:
```
java RunHangman
```