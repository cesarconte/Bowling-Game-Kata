package com.bowlinggamekata;

public class BowlingGame {

    private static final int MAX_FRAMES = 10;
    private static final int MAX_PINS = 10;

    private final int[] rolls = new int[MAX_FRAMES * 2 + 2];
    private int currentRoll = 0;

    public void roll(int pins) {
        validatePins(pins);
        rolls[currentRoll++] = pins;
    }

    public int score() {
        int score = 0;
        int frameIndex = 0;

        for (int frame = 0; frame < MAX_FRAMES; frame++) {
            if (isStrike(frameIndex)) {
                score += 10 + strikeBonus(frameIndex);
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                score += 10 + spareBonus(frameIndex);
                frameIndex += 2;
            } else {
                score += sumOfBallsInFrame(frameIndex);
                frameIndex += 2;
            }
        }

        return score;
    }

    private void validatePins(int pins) {
        if (pins < 0 || pins > MAX_PINS) {
            throw new IllegalArgumentException("The number of knocked down pins must be between 0 and 10.");
        }
    }

    protected boolean isStrike(int frameIndex) {
        return rolls[frameIndex] == MAX_PINS;
    }

    private int sumOfBallsInFrame(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex + 1];
    }

    private int strikeBonus(int frameIndex) {
        return rolls[frameIndex + 1] + rolls[frameIndex + 2];
    }

    private int spareBonus(int frameIndex) {
        return rolls[frameIndex + 2];
    }

    protected boolean isSpare(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex + 1] == MAX_PINS;
    }
}