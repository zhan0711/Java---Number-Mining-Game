package com.example.myapplication;

public class CheckGuessResult {
    private String message; // status message
    private boolean isWin; // true is win, false is loose

    /*
     * No argument constructor, sets message to "no game" and isWin to false
     */
    public CheckGuessResult() {
        this("no game", false);
    }

    /*
     * Overloaded Constructor for message and isWin
     */
    public CheckGuessResult(String message, boolean isWin) {
        this.message = message;
        this.isWin = isWin;
    }

    /*
     * Accessor for message
     */
    public String getMessage() {
        return message;
    }

    /*
     * Mutator for message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /*
     * accessor for isWin
     */
    public boolean isWin() {
        return isWin;
    }

    /*
     * mutator for isWin
     */
    public void setWin(boolean isWin) {
        this.isWin = isWin;
    }

    /*
     * Returns a String representation in the format of
     * String.format("CheckGuessResult: message %s, isWin %b", message, isWin)
     */
    public String toString() {
        String report;
        report = String.format(
                "CheckGuessResult: message %s, isWin %b", message, isWin);
        return report;
    }
}
