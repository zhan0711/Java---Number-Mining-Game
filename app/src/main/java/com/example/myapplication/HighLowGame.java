package com.example.myapplication;

import java.util.Random;

public class HighLowGame {
    private Random random = new Random();
    private int fuelAvailable;
    private int numberToGuess;
    private int guessCount;
    private static final int MIN = 1;
    private static final int MAX = 10;

    public enum Status {
        WIN,
        LOSE,
        LOW,
        HIGH
    }

    public HighLowGame() {
        reset();
    }

    public int getFuelAvailable() {
        return this.fuelAvailable;
    }

    public void setFuelAvailable(int fuelAvailable) {
        this.fuelAvailable = fuelAvailable;
    }

    public int getNumberToGuess() {
        return this.numberToGuess;
    }

    public void setNumberToGuess(int numberToGuess) {
        this.numberToGuess = numberToGuess;
    }

    public int getGuessCount() {
        return this.guessCount;
    }


    public void reset() {
        int sum = 0;
        // reset numbertoguess
        this.numberToGuess = random.nextInt(MAX) + 1;
        // reset available fuel
        for(int value = 1; value<=MAX; value++) {
            sum = sum + value;
        }
        this.fuelAvailable = sum / 2;
        this.guessCount = 0;
    }

    public void run() {
        CheckGuessResult checkGuessResult = null;
        String message;
        boolean isWon = false;
        int guessCount = 0;
        int userNumber;

        System.out.println("Guess the number from " + MIN + " to " + MAX);
        System.out.println("You have " + fuelAvailable + " guess-fuel remaining");

        while (fuelAvailable > 0 && isWon == false) {
            guessCount ++;
//            userNumber = user.inputInteger("guess: ");
            userNumber = 5;
            fuelAvailable = fuelAvailable - userNumber;
            if (fuelAvailable < 0) {
                fuelAvailable = 0;
            }

//            checkGuessResult = checkGuess(userNumber);

            isWon = checkGuessResult.isWin();
            message = checkGuessResult.getMessage();
            System.out.println(message);
        }

        message = reportGameResult(isWon, guessCount);
        System.out.println(message);
    }

//    public CheckGuessResult checkGuess(int guess) {
//        boolean isWin = false;
//        String message;
//
//        // Increase guessCount.
//        guessCount++;
//
//        // Calculate fuel.
//        fuelAvailable = fuelAvailable - guess;
//        if (fuelAvailable < 0) {
//            fuelAvailable = 0;
//        }
//
//        // Check guess result.
//        if (guess == numberToGuess) {
//            message = "you_win";
//            isWin = true;
//        } else {
//            if (fuelAvailable <= 0) {
//                message = "you_lose";
//            } else {
//                if (guess < numberToGuess) {
//                    message = "too_low";
//                } else {
//                    message = "too_high";
//                }
//            }
//        }
//
//        CheckGuessResult checkGuessResult = new CheckGuessResult(message, isWin);
//        return checkGuessResult;
//    }

    public Status checkGuess(int guess) {
        Status status = Status.WIN;

        // Increase guessCount.
        guessCount++;

        // Calculate fuel.
        fuelAvailable = fuelAvailable - guess;
        if (fuelAvailable < 0) {
            fuelAvailable = 0;
        }

        // Check guess result.
        if (guess != numberToGuess) {
            if (fuelAvailable <= 0) {
                status = Status.LOSE;
            } else {
                if (guess < numberToGuess) {
                    status = Status.LOW;
                } else {
                    status = Status.HIGH;
                }
            }
        }

        return status;
    }

    private String reportGameResult(boolean isWon, int guessCount) {
        String message;

        if (isWon == true) {
            message = "You win! It took you ";
        }else {
            message = "You did not win, you used ";
        }
        message = message + guessCount + " guesses.";
        return message;
    }
}
