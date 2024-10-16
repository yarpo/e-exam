/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.swing.JPanel;
import presentation.Panels.CheckExamPanel;

/**
 *Panel przedstawiajacy sprawdzanie egzaminu
 * @author Sokol
 */
public class TabCheckExam extends JPanel
{
    public TabCheckExam ()
    {
        CheckExamPanel panExam = new CheckExamPanel();
        panExam.init();
        this.add(panExam);
    }
}
