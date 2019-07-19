package guessgame;

import static java.lang.System.out;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Welcome to Guess The Movie Game!");

        GuessWord game = new GuessWord();

        game.initGame();
    }

}

