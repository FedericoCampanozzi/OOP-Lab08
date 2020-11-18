package it.unibo.oop.lab.advanced;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller.
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final List<DrawNumberView> views;

    public DrawNumberApp() throws FileNotFoundException {
        this.model = new DrawNumberImpl();
        views = new ArrayList<>();
        views.add(new DrawNumberViewImpl());
        views.add(new PrintStreamView(System.out));
        views.add(new PrintStreamView("output.log"));
        views.forEach((DrawNumberView v) -> { 
            v.setObserver(this);
            v.start();
            }
        );
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view : this.views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view : this.views) {
                view.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView view : this.views) {
                view.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp();
    }

}
