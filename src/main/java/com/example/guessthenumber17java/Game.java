package com.example.guessthenumber17java;

public class Game {
    private String level;
    private int retryCount;
    private int number;
    private int lastResult;
    private boolean finished = false;

    public Game(String level) {
        this.level = level;
        switch (level) {
            case ("Простой"):
                retryCount = 10;
                break;
            case ("Средний"):
                retryCount = 6;
                break;
            case ("Сложный"):
                retryCount = 3;
                break;
        }
        do {
            number = (int) (Math.random() * 100);
        } while (number == 0 || number == 100);
    }

    public Boolean checkNumber(int number) {
        retryCount--;
        return this.number == number;
    }

    public int compareNumber(int number) {
        retryCount--;
        this.lastResult = this.number - number;
        if (lastResult == 0 || retryCount == 0) {
            this.finished = true;
        }
        return lastResult;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public int getLastResult() {
        return lastResult;
    }

    public int getNumber() {
        return number;
    }

    public boolean getFinished() {
        return finished;
    }

    public String getLevel() {
        return level;
    }
}
