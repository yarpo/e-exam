/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import presentation.Panels.AddExamPanel;
import presentation.Panels.SearchExamPanel;

/**
 *Panel obslugujacy opcje zwiazane z pytaniami:
 * 1.Dodawanie/Edycja pytan
 * 2.Szukanie pytan
 */
public class TabExam extends JPanel
{
    private AddExamPanel addExamPanel;

    public TabExam ()
    {
        super();

        addExamPanel = new AddExamPanel();

        JPanel panMain = new JPanel();
        panMain.setLayout(new BoxLayout(panMain, BoxLayout.X_AXIS));
        panMain.add(addExamPanel);
        panMain.add(Box.createRigidArea(new Dimension(80, 0)));
        panMain.add(new SearchExamPanel(addExamPanel));

        this.add(panMain);
    }

    public void updateChildren ()
    {
        addExamPanel.updateChildren();
    }
}
