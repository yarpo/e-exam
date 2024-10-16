/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * main class of a program
 * 
 */
public class Main
{
    final private static int WIDTH = 500;
    final private static int HEIGHT = 500;

    public static void createAndShowGUI ()
    {
        JFrame frame = new JFrame("eEgzaminator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        MainFrame mainFrame = new MainFrame();

        frame.getContentPane().add(mainFrame);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main (String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run ()
            {
                createAndShowGUI();
            }
        });
    }
}
