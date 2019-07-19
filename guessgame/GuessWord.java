package guessgame;

import java.io.*;
import java.lang.*;
import java.util.*;

class GuessWord {

    private String randomTitle;
    private int pointsLost;
    private String wrongLetters;
    private String rightLetters;
    private boolean gameWon;


    public String ReadRandomTitle() {

        ArrayList<String> moviesList = new ArrayList<>();

        File file = new File( "src/movies.txt" );

        try {
            Scanner scanner = new Scanner( file );
            while (scanner.hasNextLine()) {
                moviesList.add( scanner.nextLine() );
            }


        } catch (FileNotFoundException e) {
            System.out.println( "File doesn't exist!" );
        }
        // Read the title from the file


        int randomIndex = (int) (Math.random() * moviesList.size());
        String randomTitle = moviesList.get( randomIndex );

        // Output the result

        return randomTitle;

    }

    public String underscoreTitle(String movieTitle, String correctLetters) {
        if (correctLetters == "") {
            return movieTitle.replaceAll( "[a-zA-Z]", " _ " );
        } else {
            return movieTitle.replaceAll( "[a-zA-Z&&[^" + correctLetters + "]]", " _ " );
        }
    }


    public String userProidedLetter() {
        pointsLost = 0;
        rightLetters = "";
        wrongLetters = "";
        gameWon = false;
        System.out.println( "\nGuess a letter:" );
        //Create a scanner to get user's guess.
        Scanner userScanner = new Scanner( System.in );
        //Store user guess for processing
        String letter = userScanner.nextLine().toLowerCase();

        if (!letter.matches( "[a-z]" )) {
            System.out.println( "This is not a letter. Try again." );
            return userProidedLetter();
        }

        return letter;

    }


    public boolean checkGuess(String movieTitle, String guess) {
        return movieTitle.contains( guess );
    }


    public void initGame() {
        // Store correct guesses
        String correctLetters = "";
        // Store incorrect guesses
        String wrongLetters = "";
        // User chances
        int livesLeft = 10;
        // Get a movie title
        String movie = ReadRandomTitle();
        // Game over
        boolean gameOver = false;

        while (!gameOver) {
            // Obscure the title
            String hidenMovie = underscoreTitle( movie, correctLetters );
            System.out.println( "You are guessing a movie:" );
            System.out.println( hidenMovie );
            if (!hidenMovie.contains( "_" )) {
                gameOver = true;
                System.out.println( "You WIN!!!");
            } else {
                // Get a user guess
                String guess = userProidedLetter();
                //Check to see if the users guess is in the title
                if (checkGuess( movie, guess )) {
                    correctLetters += guess;
                    if (correctLetters.contains( guess )) {
                        System.out.println( "You already guessed that!" );
                    } else {
                        System.out.println( "Correct!" );
                    }
                } else {
                    wrongLetters += guess;
                    livesLeft--;
                    System.out.println( "Sorry, try again!" );
                    System.out.println( "You have " + livesLeft + " lives left." );
                    if (!wrongLetters.equals( " " ))
                        System.out.println( "You have wrong guessed: " + wrongLetters );

                    if (livesLeft == 0) {
                        gameOver = true;
                        System.out.println( "You're out of lives! Game over!" );
                        System.out.println( "The movie was \"" + movie + "\"!" );
                    }
                }
            }
        }


    }
}