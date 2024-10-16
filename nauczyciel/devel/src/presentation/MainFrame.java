/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Glowne okno programu
 * @author Sokol
 */
public class MainFrame extends JPanel
{
    final static private String LABEL_QUESTION = "Pytania";
    final static private String LABEL_EXAM_CREATOR = "Egzamin";
    final static private String LABEL_EXAM_CHECKER = "Sprawdź egzamin";
    final static private String LABEL_CATEGORY = "Kategorie";

    public MainFrame ()
    {
        super(new GridLayout(1, 1));
        JTabbedPane mainPane = new JTabbedPane();
        TabQues tabQues = new TabQues();
        TabExam tabExam = new TabExam();
        mainPane.add(LABEL_QUESTION, tabQues);
        mainPane.add(LABEL_EXAM_CREATOR, tabExam);
        mainPane.add(LABEL_EXAM_CHECKER, new TabCheckExam());
        mainPane.add(LABEL_CATEGORY, new TabCategory(tabQues, tabExam));
        this.add(mainPane);
    }
}
