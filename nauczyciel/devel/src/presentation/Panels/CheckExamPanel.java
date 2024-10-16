/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import logic.checkExam.CheckExam;
import logic.checkExam.PointCalc;
import logic.checkExam.StudentGradeAnswers;

/**
 *
 * @author Sokol
 */
public class CheckExamPanel extends JPanel implements ActionListener,
        ListSelectionListener
{
    protected static String EXPORTED_FILE_NAME = "Wyniki-egzaminu-";
    protected static String EXPORTED_FILE_EXT = ".csv";
    protected static String DATE_FORMAT = "dd-MM-yyyy,HH.mm";
    private CheckExam checkExam;
    private List<StudentGradeAnswers> studAnswers;
    private JButton butUpload;
    private JButton butExport;
    private JList scrollListStuds;
    private StudAnsExamPanel studAnsPanel;
    private StudPointPanel studPointPanel;
    private JFileChooser examChooser;
    private JScrollPane scrollAnswers;

    public CheckExamPanel ()
    {
        super();
        checkExam = new CheckExam();
    }

    public void init ()
    {
        JPanel panMain = new JPanel();
        examChooser = new JFileChooser();

        studPointPanel = new StudPointPanel();

        panMain.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        studAnsPanel = new StudAnsExamPanel();
        studAnsPanel.setStudPointPanel(studPointPanel);

        butUpload = new JButton("Importuj wypełnione egzaminy");
        butUpload.addActionListener(this);

        butExport = new JButton("Eksportuj wyniki");
        butExport.addActionListener(this);

        scrollListStuds = new JList();
        JScrollPane scrollStuds = new JScrollPane(scrollListStuds);
        scrollListStuds.addListSelectionListener(this);

        scrollAnswers = new JScrollPane();
        scrollAnswers.setViewportView(studAnsPanel);
        scrollAnswers.setPreferredSize(new Dimension(300, 300));

        cons.gridx = 0;
        cons.gridy = 0;
        panMain.add(butUpload, cons);

        cons.gridx++;
        panMain.add(butExport, cons);

        cons.gridx = 0;
        cons.gridy++;
        panMain.add(scrollStuds, cons);

        cons.gridx++;
        panMain.add(scrollAnswers, cons);

        cons.gridx++;
        cons.anchor = GridBagConstraints.FIRST_LINE_START;
        panMain.add(studPointPanel, cons);

        cons.gridx++;
        panMain.add(new LegendAnsPanel(),cons);


        cons.anchor = GridBagConstraints.CENTER;

        this.add(panMain);
    }

    private void setListStudsData ()
    {
        String[] listData = new String[studAnswers.size()];
        for (int i = 0; i < studAnswers.size(); i++)
        {
            listData[i] = studAnswers.get(i).getIdKey();
        }
        scrollListStuds.setListData(listData);
    }

    public void actionPerformed (ActionEvent evt)
    {
        if (evt.getSource() == butUpload)
        {
            examChooser.showOpenDialog(this);

            if (examChooser.getSelectedFile() != null)
            {
                studAnswers = checkExam.processExam(examChooser.getSelectedFile().
                        getAbsolutePath());
                setListStudsData();
            }
            update();
        }
        else if (evt.getSource() == butExport)
        {
            exportResults();
        }

    }

    private void exportResults ()
    {
        PointCalc pointCalc = new PointCalc();

        List<String> keys = new ArrayList<String>();
        List<ArrayList<Integer>> points = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < studAnswers.size(); i++)
        {
            keys.add(studAnswers.get(i).getIdKey());
            points.add((ArrayList<Integer>) studAnswers.get(i).getGlobPoints());
        }

        pointCalc.export(getExportedFileFullName(), keys, points);
    }

    private String getExportedFileFullName ()
    {
        return EXPORTED_FILE_NAME + getCurrentDateString() + EXPORTED_FILE_EXT;
    }

    private String getCurrentDateString ()
    {
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String today = "[" + formatter.format(date) + "]";

        return today;
    }

    public void valueChanged (ListSelectionEvent e)
    {
        studAnsPanel.removeAllElements();
        if (scrollListStuds.getSelectedIndex() != -1)
        {
            studAnsPanel.init(
                    studAnswers.get(scrollListStuds.getSelectedIndex()));

            studPointPanel.removeAllElements();
            studPointPanel.init(studAnswers.get(
                    scrollListStuds.getSelectedIndex()));
            studPointPanel.update(studAnswers.get(scrollListStuds.
                    getSelectedIndex()));
        }
        update();
    }

    private void update ()
    {
        this.revalidate();
        this.repaint();
    }
}
