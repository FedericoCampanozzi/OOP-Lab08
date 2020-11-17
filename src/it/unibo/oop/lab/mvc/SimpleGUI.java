package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final Controller ctrl = new ControllerImpl();

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) I has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the frame, 
     * a JTextArea in the center and two buttons below it: "Print", and "Show history". 
     * SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behavior of the program is that, if "Print" is pressed, the
     * controller is asked to show the string contained in the text field on standard output. 
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUI() {

        final JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        final JPanel pButtons = new JPanel();
        pButtons.setLayout(new BorderLayout());
        final JButton print = new JButton("Print");
        final JButton history = new JButton("Show History");
        final JTextField textToPrint = new JTextField();
        final JTextArea textHistory = new JTextArea();
        main.add(pButtons, BorderLayout.CENTER);
        main.add(print, BorderLayout.SOUTH);
        pButtons.add(history, BorderLayout.SOUTH);
        main.add(textToPrint, BorderLayout.NORTH);
        pButtons.add(textHistory, BorderLayout.CENTER);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        frame.setVisible(true); 
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    ctrl.setNextString(textToPrint.getText());
                } catch (NullStringException exc) {
                    JOptionPane.showMessageDialog(null, exc.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
                } finally {
                    textToPrint.setText("");
                }
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                    String t = "";

                    for (final String s : ctrl.getMemoryString()) {
                        t += s + "\n";
                    }

                    textHistory.setText(t);
                    textToPrint.setText("");
            }
        });
    }

    /**
     * @param args ignored
     */
    public static void main(final String... args) {
       new SimpleGUI();
    }
}
