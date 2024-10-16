/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.swing.JPanel;
import presentation.Panels.CategoryPanel;

/**
 *
 * @author Sokol
 * "Tab" zawierajacy Panel do zarzadzania kategoriami(dodawanie usuwanie,
 * potem mzoe modyfikacja)
 */
public class TabCategory extends JPanel
{
    public TabCategory (TabQues tabQues, TabExam tabExam)
    {
        super();
        this.add(new CategoryPanel(tabQues, tabExam));
    }
}
