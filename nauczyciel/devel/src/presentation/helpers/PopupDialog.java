/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.helpers;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Michal
 */
public class PopupDialog
{
    public void createPopupDialog (String message)
    {
        JOptionPane pane = new JOptionPane(message);
        JDialog dialog = pane.createDialog(new JFrame(), "Dialog");
        dialog.setVisible(true);
    }
}
