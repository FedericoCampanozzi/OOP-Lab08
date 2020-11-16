package it.unibo.oop.lab.iogui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class is a simple application that writes a random number on a file.
 * 
 * This application does not exploit the model-view-controller pattern, and as
 * such is just to be used to learn the basics, not as a template for your
 * applications.
 */
public class BadIOGUI {

    private static final String TITLE = "A very simple GUI application";
    private static final String PATH = System.getProperty("user.home")
            + System.getProperty("file.separator")
            + BadIOGUI.class.getSimpleName() + ".txt";
    private static final int PROPORTION = 5;
    private final Random rng = new Random();
    private final JFrame frame = new JFrame(TITLE);

    /**
     * 
     */
    public BadIOGUI() {
        final JPanel canvas = new JPanel();
        final JPanel canvasCenter = new JPanel();
        final JPanel canvasBorder = new JPanel();
        canvas.setLayout(new BorderLayout());
        canvasCenter.setLayout(new BoxLayout(canvasCenter, BoxLayout.X_AXIS));
        canvasBorder.setLayout(new BorderLayout());
        canvas.add(canvasCenter, BorderLayout.CENTER);
        canvasCenter.add(canvasBorder);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JButton write = new JButton("Write on file");
        final JButton read = new JButton("Read on file");
        final JLabel res = new JLabel("Read on file");
        canvasBorder.add(res, BorderLayout.SOUTH);
        canvasBorder.add(write, BorderLayout.EAST);
        canvasBorder.add(read, BorderLayout.WEST);
        /*
         * Handlers
         */
        write.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                /*
                 * This would be VERY BAD in a real application.
                 * 
                 * This makes the Event Dispatch Thread (EDT) work on an I/O
                 * operation. I/O operations may take a long time, during which
                 * your UI becomes completely unresponsive.
                 */
                try (PrintStream ps = new PrintStream(PATH)) {
                    final int rndInt = rng.nextInt();
                    ps.append(Integer.toString(rndInt));
                    System.out.println("Write Number : " + rndInt);
                    res.setText("Write Number : " + rndInt);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });

        read.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try (BufferedReader r = new BufferedReader(new FileReader(PATH))) {
                    final String line = r.readLine();
                    System.out.println("Read Number : " + line);
                    res.setText("Read Number : " + line);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                } catch (IOException e2) {
                    JOptionPane.showMessageDialog(frame, e2, "Error", JOptionPane.ERROR_MESSAGE);
                    e2.printStackTrace();
                }
            }
        });
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to pull the frame onscreen
         */
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * @param args ignored
     */
    public static void main(final String... args) {
       new BadIOGUI().display();
    }
}
