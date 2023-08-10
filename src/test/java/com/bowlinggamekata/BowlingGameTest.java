package com.bowlinggamekata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BowlingGameTest {
    private BowlingGame bowlingGame;

    @BeforeEach
    public void setUp() {
        bowlingGame = new BowlingGame();
    }

    private void rollRegularFrame(int roll1Pins, int roll2Pins) {
        bowlingGame.roll(roll1Pins);
        bowlingGame.roll(roll2Pins);
    }

    private void rollMany(int rolls, int pins) {
        for (int i = 0; i < rolls; i++) {
            bowlingGame.roll(pins);
        }
    }

    private void rollSpare(int firstRoll, int secondRoll) {
        bowlingGame.roll(firstRoll);
        bowlingGame.roll(secondRoll);
    }

    private void rollStrike() {
        bowlingGame.roll(10);
    }

    @Test
    public void testGutterGame() {
        rollMany(20, 0);
        assertEquals(0, bowlingGame.score());
    }

    @Test
    public void testAllOnes() {
        rollMany(20, 1);
        assertEquals(20, bowlingGame.score());
    }

    @Test
    public void testSingleRoll() {
        bowlingGame.roll(7);
        assertEquals(7, bowlingGame.score());
    }

    @Test
    public void testOneSpare() {
        rollSpare(5, 5);
        bowlingGame.roll(3);
        rollMany(17, 0);
        assertEquals(16, bowlingGame.score());
    }

    @Test
    public void testOneStrike() {
        rollStrike();
        bowlingGame.roll(3);
        bowlingGame.roll(4);
        rollMany(16, 0);
        assertEquals(24, bowlingGame.score());
    }

    @Test
    public void testPerfectGame() {
        rollMany(12, 10);
        assertEquals(300, bowlingGame.score());
    }

    @Test
    public void testAllStrikes() {
        rollMany(12, 10);
        assertEquals(300, bowlingGame.score());
    }

    @Test
    public void testAllSpares() {
        rollMany(21, 5);
        assertEquals(150, bowlingGame.score());
    }

    @Test
    public void testMixedSparesAndStrikes() {
        rollStrike();
        rollSpare(6, 4);
        rollRegularFrame(3, 4);
        rollStrike();
        rollSpare(3, 7);
        rollStrike();
        rollRegularFrame(3, 4);
        rollStrike();
        rollSpare(8, 2);
        rollRegularFrame(3, 4);
        assertEquals(144, bowlingGame.score());
    }

    @Test
    public void testAllGutterGame() {
        rollMany(20, 0);
        assertEquals(0, bowlingGame.score());
    }

    @Test
    public void testInvalidRoll() {
        assertThrows(IllegalArgumentException.class, () -> bowlingGame.roll(-1));
        assertThrows(IllegalArgumentException.class, () -> bowlingGame.roll(11));
    }

    @Test
    public void testRandomRolls() {
        rollRegularFrame(2, 5);
        rollSpare(2, 8);
        rollRegularFrame(2, 3);
        rollStrike();
        rollRegularFrame(2, 4);
        rollSpare(5, 5);
        rollStrike();
        rollStrike();
        rollStrike();
        rollStrike();
        bowlingGame.roll(4);
        bowlingGame.roll(3);
        assertEquals(167, bowlingGame.score());
    }

    @Test
    public void testLastFrameSpare() {
        rollMany(18, 0);
        rollSpare(7, 3);
        bowlingGame.roll(5); // Bonus roll for spare
        assertEquals(15, bowlingGame.score());
    }

    @Test
    public void testLastFrameStrike() {
        rollMany(18, 0);
        rollStrike();
        bowlingGame.roll(5);
        bowlingGame.roll(3); // Bonus rolls for strike
        assertEquals(18, bowlingGame.score());
    }
}
