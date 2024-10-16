/*
 * Obsluga dodawania egzaminow
 */
package presentation.Panels;

import logic.export.QuestionMatchException;
import presentation.Panels.ExamSections.ExamSectionPanel;
import data.bean.SectionABCDSrcBean;
import data.bean.SectionGapSrcBean;
import data.bean.SectionSrcBean;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import logic.exams.*;
import logic.export.ExamExport;
import logic.export.XMLExporter;
import logic.question.Questions;
import logic.question.interfaces.IQuestions;
import presentation.Panels.ExamSections.ExamSectionABCDPanel;
import presentation.Panels.ExamSections.ExamSectionGapPanel;
import presentation.Panels.ExamSections.SectionABCDPanel;
import presentation.Panels.ExamSections.SectionGapPanel;
import presentation.Panels.ExamSections.SectionOpenPanel;
import presentation.Panels.ExamSections.SectionPanel;
import presentation.Panels.communicates.AddExam;
import presentation.helpers.ExamNameValidator;
import presentation.helpers.PopupDialog;

/**
 * Panel obslugujacy dodawanie egzaminu
 * @author Sokol
 */
public class AddExamPanel extends JPanel implements ActionListener
{
    private final int TFSIZE = 10;
    private final String EXAM_FILE_EXT = ".xml";
    private final int REQUIRED_FIELDS_FILLED = 1;
    private JTextField texfPoints;
    private JTextField texfExName;
    private JButton butSave;
    private JButton butExport;
    private JButton butClearFields;
    private JPanel panMain;
    private JPanel panSec;
    private JScrollPane scrollPane;
    private ExamGridBagConstraints cons;
    private SectionGapPanel gapsPanel;
    private SectionOpenPanel openPanel;
    private SectionABCDPanel abcdPanel;
    private PopupDialog popupDialog;
    private Exam exam;

    public AddExamPanel ()
    {
        exam = new Exam(AddExam.DEFAULT_EXAM_NAME);
        popupDialog = new PopupDialog();

        scrollPane = new JScrollPane();
        panMain = new JPanel(new GridBagLayout());
        cons = new ExamGridBagConstraints();

        JLabel labExName = new JLabel(AddExam.DEFAULT_EXAM_LABEL);
        texfExName = new JTextField(TFSIZE);
        texfExName.setText(AddExam.DEFAULT_EXAM_LABEL);

        butSave = new JButton(AddExam.SAVE_EXAM_BUTTON);
        butSave.addActionListener(this);

        butExport = new JButton(AddExam.EXPORT_EXAM_BUTTON);
        butExport.addActionListener(this);

        butClearFields = new JButton(AddExam.CLEAR_FORM_BUTTON);
        butClearFields.addActionListener(this);

        texfPoints = new JTextField(TFSIZE);
        texfPoints.setEditable(false);
        texfPoints.setText(AddExam.DEFAULT_POINTS_NUMBER);
        JLabel labPoints = new JLabel(AddExam.POINTS_NUMBER_LABEL);

        cons.setAtGrid(0, 0);
        panMain.add(labExName, cons);

        cons.nextColumn();
        panMain.add(texfExName, cons);

        cons.nextColumn();
        panMain.add(labPoints, cons);

        cons.nextColumn();
        panMain.add(texfPoints, cons);

        cons.nextRow();
        cons.gridwidth = 1; // TODO: co to znaczy?

        panMain.add(butSave, cons);

        cons.nextColumn();
        panMain.add(butExport, cons);

        cons.nextColumn().nextColumn();
        panMain.add(butClearFields, cons);

        cons.nextRow();
        cons.gridwidth = 6; // TODO: co to znaczy?

        panMain.add(buildSectionPanel(), cons);
        panSec = new JPanel();
        panSec.setLayout(new GridBagLayout());
        scrollPane.setViewportView(panSec);
        scrollPane.setPreferredSize(new Dimension(200, 300));

        this.add(panMain);
    }

    private JTabbedPane buildSectionPanel ()
    {
        JTabbedPane tabPan = new JTabbedPane();
        abcdPanel = new SectionABCDPanel(this);
        gapsPanel = new SectionGapPanel(this);
        openPanel = new SectionOpenPanel(this);

        tabPan.setSize(200, 300);

        tabPan.add(AddExam.OPENED_TAB_LABEL, openPanel);
        tabPan.add(AddExam.ABCD_TAB_LABEL, abcdPanel);
        tabPan.add(AddExam.GAPS_TAB_LABEL, gapsPanel);

        return tabPan;
    }

    public void actionPerformed (ActionEvent evt)
    {

        if (evt.getSource() == butSave)
        {
            saveExam();
        }
        else if (evt.getSource() == butExport)
        {
            exportExam();
        }
        else if (evt.getSource() == butClearFields)
        {
            clearFields();
        }

    }

    private void reval ()
    {
        panMain.revalidate();
        panMain.repaint();
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    private void exportExam ()
    {
        if (checkFill() != REQUIRED_FIELDS_FILLED)
        {
            popupDialog.createPopupDialog(AddExam.ERROR_EXAM_INCORRECT);
            return;
        }

        exam = new Exam(texfExName.getText());

        try
        {
            addSectionsToExam(exam);
        }
        catch (Exception ex)
        {
            popupDialog.createPopupDialog(AddExam.EX_INFO_ADD_SECTION);
            return;
        }

        IQuestions questions = new Questions();
        XMLExporter exporter = new XMLExporter(getExamFileName());
        ExamExport export = new ExamExport(exporter, questions, exam);

        try
        {
            export.export2File();
            popupDialog.createPopupDialog(AddExam.SUCCESS_EXAM_EXPORT);
        }
        catch (QuestionMatchException ex)
        {
            // TODO: dokladniejszy opis, ktore sekcje, nie spelniaja
            // ktorych kryteriow
            popupDialog.createPopupDialog(AddExam.EX_INFO_SECTION_NOT_MATCHED + ex.getExceptionSectionName());
        }
        catch (Exception ex)
        {
            popupDialog.createPopupDialog(AddExam.EX_EXAM_EXPORT);
        }
    }

    private String getExamFileName ()
    {
        return exam.getName() + EXAM_FILE_EXT;
    }

    private void saveExam ()
    {
        String name = getExamName();
        if (!ExamNameValidator.valid(name))
        {
            popupDialog.createPopupDialog(AddExam.ERROR_EXAM_NAME
                    + " pattern: " + ExamNameValidator.PATTERN);
            return;
        }

        exam = new Exam(name);

        if (checkFill() != REQUIRED_FIELDS_FILLED)
        {
            popupDialog.createPopupDialog(AddExam.ERROR_EXAM_INCORRECT);
            return;
        }

        try
        {
            addSectionsToExam(exam);
            exam.save();
            popupDialog.createPopupDialog(AddExam.SUCCESS_EXAM_SAVE);
        }
        catch (Exception ex)
        {
            popupDialog.createPopupDialog(AddExam.EX_EXAM_SAVE);
        }
    }

    private String getExamName ()
    {
        if (texfExName.getText().equals(("")))
        {
            return AddExam.DEFAULT_EXAM_NAME;
        }

        return texfExName.getText();
    }

    public void calcPoints ()
    {
        int numPoints = 0;

        numPoints += gapsPanel.calcPoints();
        numPoints += openPanel.calcPoints();
        numPoints += abcdPanel.calcPoints();

        texfPoints.setText(Integer.toString(numPoints));
    }

    void clearFields ()
    {
        texfExName.setText(AddExam.DEFAULT_EXAM_LABEL);

        openPanel.clearFields();
        abcdPanel.clearFields();
        gapsPanel.clearFields();
        texfPoints.setText(AddExam.DEFAULT_POINTS_NUMBER);

        reval();
    }

    void fillPanel (Exam searchExam)
    {
        texfExName.setText(searchExam.getName());

        for (int i = 0; i < searchExam.getSections().size(); i++)
        {
            Section tempSec = (Section) searchExam.getSections().get(i);

            if (tempSec.getType() == Section.OPENED)
            {
                openPanel.addSection(tempSec);
            }
            else if (tempSec.getType() == Section.GAP)
            {
                gapsPanel.addSection((SectionGap) tempSec);
            }
            else if (tempSec.getType() == Section.ABCD)
            {
                abcdPanel.addSection((SectionABCD) tempSec);
            }
        }
    }

    /**
     * Wypelnij egzamin sekcjami
     * @param exam
     */
    private void addSectionsToExam (Exam exam)
            throws Exception
    {

        for (int i = 0; i < abcdPanel.getSections().size(); i++)
        {
            ExamSectionABCDPanel tempPan = (ExamSectionABCDPanel) ((SectionABCDPanel) abcdPanel).
                    getSections().get(i);

            SectionABCDSrcBean abcdBean = new SectionABCDSrcBean();
            SectionSrcBean secBean = new SectionSrcBean();

            setBasicSectionBean(secBean, exam, tempPan);
            secBean.setType(ISection.ABCD);

            abcdBean.setCorrect(tempPan.getCorrectNum());
            abcdBean.setAll(tempPan.getAnsNum());

            SectionABCD secAbcd = new SectionABCD(secBean, abcdBean);

            exam.addSection(secAbcd);
        }

        for (int i = 0; i < gapsPanel.getSections().size(); i++)
        {
            ExamSectionGapPanel tempPan = (ExamSectionGapPanel) ((SectionGapPanel) gapsPanel).
                    getSections().get(i);

            SectionGapSrcBean gapBean = new SectionGapSrcBean();
            SectionSrcBean secBean = new SectionSrcBean();
            setBasicSectionBean(secBean, exam, tempPan);
            secBean.setType(ISection.GAP);

            SectionGap sec = new SectionGap(secBean, gapBean);

            exam.addSection(sec);
        }

        for (int i = 0; i < openPanel.getSections().size(); i++)
        {
            ExamSectionPanel tempPan = (ExamSectionPanel) ((SectionPanel) openPanel).
                    getSections().get(i);

            SectionSrcBean secBean = new SectionSrcBean();
            secBean.setType(ISection.OPENED);

            setBasicSectionBean(secBean, exam, tempPan);

            Section sec = new Section(secBean);

            exam.addSection(sec);
        }
    }

    private void setBasicSectionBean (SectionSrcBean secBean, Exam exam,
            ExamSectionPanel tempPan)
    {
        secBean.setName(tempPan.getTaskName());
        secBean.setCategoryId(tempPan.getCategory());
        secBean.setGradeMin(tempPan.getMinDifficulty());
        secBean.setGradeMax(tempPan.getMaxDifficulty());
        secBean.setPoints(tempPan.getPoints());
        secBean.setHowMuch(tempPan.getQuantity());
        secBean.setExamId(exam.getId());
    }

    /**
     * Sprawdz czy wypelniono wszystkie pola w formularzu dodawania egzaminu
     * @return
     */
    private int checkFill ()
    {
        if (texfExName.getText().equals("")
                || texfExName.getText().equals(AddExam.DEFAULT_EXAM_LABEL)
                || openPanel.checkFill() == 0
                || gapsPanel.checkFill() == 0
                || abcdPanel.checkFill() == 0)
        {
            return 0;
        }

        return REQUIRED_FIELDS_FILLED;
    }

    public void updateChildren ()
    {
        gapsPanel.updateCategories();
        openPanel.updateCategories();
        abcdPanel.updateCategories();
    }
}
