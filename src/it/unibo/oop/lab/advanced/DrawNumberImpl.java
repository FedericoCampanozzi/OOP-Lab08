package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

/**
 *
 */
public final class DrawNumberImpl implements DrawNumber {

    private int choice;
    private int min;
    private int max;
    private int attempts;
    private int remainingAttempts;
    private final Random random = new Random();

    public DrawNumberImpl() {
        final InputStream in = ClassLoader.getSystemResourceAsStream("config.yml");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            this.min = Integer.parseInt(br.readLine().split(": ")[1]);
            this.max = Integer.parseInt(br.readLine().split(": ")[1]);
            this.attempts = Integer.parseInt(br.readLine().split(": ")[1]);
            this.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param min
     *            minimum number
     * @param max
     *            maximum number
     * @param attempts
     *            the maximum number of attempts
     */
    public DrawNumberImpl(final int min, final int max, final int attempts) {
        this.min = min;
        this.max = max;
        this.attempts = attempts;
        this.reset();
    }

    @Override
    public void reset() {
        this.remainingAttempts = this.attempts;
        this.choice = this.min + random.nextInt(this.max - this.min + 1);
    }

    @Override
    public DrawResult attempt(final int n) throws AttemptsLimitReachedException {
        if (this.remainingAttempts <= 0) {
            throw new AttemptsLimitReachedException();
        }
        if (n < this.min || n > this.max) {
            throw new IllegalArgumentException("The number is outside boundaries");
        }
        remainingAttempts--;
        if (n > this.choice) {
            return DrawResult.YOURS_HIGH;
        }
        if (n < this.choice) {
            return DrawResult.YOURS_LOW;
        }
        return DrawResult.YOU_WON;
    }

}
