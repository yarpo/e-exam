/*
 * Klasa odpowiedzialna za panel wyswietlajacy liste istniejacych egzaminow
 */
package presentation.Panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import logic.exams.Exam;
import logic.exams.Exams;
import presentation.Panels.communicates.SearchExam;
import presentation.helpers.PopupDialog;
import presentation.table.ExamTableModel;

/**
 * Panel obslugujacy szukanie pytan
 * @author Sokol
 */
public class SearchExamPanel extends JPanel implements ActionListener,
        ListSelectionListener
{
    private final static int NOT_SELECTED = -1;
    private JButton butRefresh;
    private JButton butDel;
    private ExamTableModel tabModel = new ExamTableModel();

    ;
    private Exams examMan = new Exams();
    private JTable tabExam;
    private ArrayList<Exam> exams;
    private AddExamPanel examPan;
    private JScrollPane scrollPane;

    public SearchExamPanel (AddExamPanel examPan)
    {
        this.examPan = examPan;

        butRefresh = new JButton(SearchExam.REFRESH_BUTTON_LABEL);
        butRefresh.addActionListener(this);
        butDel = new JButton(SearchExam.DELETE_BUTTON_LABEL);
        butDel.addActionListener(this);

        tabExam = new JTable(tabModel);
        tabExam.getSelectionModel().addListSelectionListener(this);
        tabExam.setPreferredScrollableViewportSize(new Dimension(250, 350));
        tabExam.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(tabExam);

        JPanel panMain = new JPanel();
        panMain.setLayout(new BoxLayout(panMain, BoxLayout.Y_AXIS));
        panMain.add(butRefresh);
        panMain.add(scrollPane);
        panMain.add(butDel);

        this.add(panMain);
        refresh();
    }

    public void actionPerformed (ActionEvent evt)
    {
        if (evt.getSource() == butRefresh)
        {
            refresh();
        }
        else if (evt.getSource() == butDel)
        {
            deleteExam();
        }
    }

    private void refresh ()
    {
        try
        {
            exams = examMan.getAllExams();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return;
        }

        tabModel.setData(exams);
        tabExam.setModel(tabModel);

        scrollPane.updateUI();
        scrollPane.repaint();
        repaint();
    }

    public void valueChanged (ListSelectionEvent evt)
    {
        int selRow = tabExam.getSelectedRow();
        examPan.clearFields();
        examPan.fillPanel(exams.get(selRow));
        examPan.calcPoints();
    }

    public void deleteExam ()
    {
        int selRow = tabExam.getSelectedRow();
        if (NOT_SELECTED == selRow)
        {
            return;
        }
        Exam tempEx = exams.get(selRow);

        System.out.println("Kasuje exam o id:" + tempEx.getId());

        try
        {
            examMan.delete(tempEx.getId());
        }
        catch (Exception ex)
        {
            new PopupDialog().createPopupDialog(SearchExam.EX_EXAM_DELETE);
            ex.printStackTrace();
        }
        refresh();
    }
}
