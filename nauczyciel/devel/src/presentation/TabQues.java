/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import presentation.Panels.AddQuestionPanel;
import presentation.Panels.SearchQuestionPanel;

/**
 *Panel obslugujacy opcje zwiazane z pytaniami:
 * 1.Dodawanie/Edycja pytan
 * 2.Szukanie pytan
 */
public class TabQues extends JPanel
{
    private SearchQuestionPanel searchPan;
    private AddQuestionPanel quesPan;

    public TabQues ()
    {
        super();
        searchPan = new SearchQuestionPanel();
        quesPan = new AddQuestionPanel();

        searchPan.setAddPanel(quesPan);
        quesPan.setSearchPanel(searchPan);

        JPanel panMain = new JPanel();
        panMain.setLayout(new BoxLayout(panMain, BoxLayout.X_AXIS));
        panMain.add(quesPan);
        panMain.add(Box.createRigidArea(new Dimension(80, 0)));
        panMain.add(searchPan);

        this.add(panMain);
    }

    public void updateChildren ()
    {
        searchPan.updateCategories();
        quesPan.updateCategories();
    }
}
