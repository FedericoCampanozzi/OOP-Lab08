package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */

    private final JFrame frame = new JFrame();
    private final Controller controller = new Controller();

    public SimpleGUIWithFileChooser() {
        final JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        final JPanel mainTopPanl = new JPanel();
        mainTopPanl.setLayout(new BorderLayout());
        final JButton save = new JButton("Save");
        final JTextArea text = new JTextArea();
        final JTextField outPath = new JTextField(controller.getPath());
        final JButton browse = new JButton("Browse file ...");
        final JLabel label = new JLabel("Write something .... ");
        main.add(mainTopPanl, BorderLayout.NORTH);
        mainTopPanl.add(label, BorderLayout.SOUTH);
        mainTopPanl.add(outPath, BorderLayout.CENTER);
        mainTopPanl.add(browse, BorderLayout.EAST);
        main.add(text, BorderLayout.CENTER);
        main.add(save, BorderLayout.SOUTH);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        frame.setVisible(true); 
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.saveText(text.getText());
                } catch (IOException exc) {
                    JOptionPane.showMessageDialog(null, exc.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
                } finally {
                    JOptionPane.showMessageDialog(null, "File saved correctly", "Success", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                final int response = chooser.showSaveDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    controller.setFile(chooser.getSelectedFile());
                    outPath.setText(controller.getPath());
                } else {
                    JOptionPane.showMessageDialog(null, "And error raise or file is not selected correctly", "Something wrong", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    /**
     * @param args ignored
     */
    public static void main(final String... args) {
       new SimpleGUIWithFileChooser();
    }

}
