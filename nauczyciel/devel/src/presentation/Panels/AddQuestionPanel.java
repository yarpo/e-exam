package presentation.Panels;

import data.interfaces.ICategorySrc;
import data.staticClass.TableObjectFactory;
import data.staticClass.TypeCalculator;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import logic.question.AnswerABCD;
import logic.question.Answers;
import logic.question.Question;
import logic.question.Questions;
import logic.question.interfaces.IQuestion;
import presentation.Panels.communicates.AddQuestion;
import presentation.helpers.GapCalc;
import presentation.helpers.PopupDialog;
import presentation.interfaces.IClearPanel;

/**
 * Panel dodawania pytan
 * @author Sokol
 */
public class AddQuestionPanel extends JPanel implements DocumentListener,
        ActionListener, IClearPanel
{
    static final int MAX_GRADE = 10;
    static final int MIN_GRADE = 1;
    static final int MIN_ABCD_ANSWERS_NO = 3;
    static final String FONT_FAMILY = "Verdana";
    static final int FONT_SIZE_NORMAL = 12;
    static final int FONT_SIZE_SMALL = 10;
    private JTextArea texAreaCont;
    private JTextArea texAreaComm;
    private JComboBox comBoxGrade;
    private JComboBox comBoxCat;
    private JButton butSave;
    private JButton butClear;
    private JButton butChange;
    private GapPanel gapPan;
    private ABCPanel abcPan;
    private OpenPanel openPan;
    private GapCalc oGapCalc;
    private Questions quesMan;
    private SearchQuestionPanel searchPan;
    private int quesId = 0;

    public AddQuestionPanel ()
    {
        oGapCalc = new GapCalc();
        quesMan = new Questions();

        JLabel labCont = new JLabel(AddQuestion.QUESTION_CONTENT);
        texAreaCont = new JTextArea(6, 30);
        Font font = new Font(FONT_FAMILY, Font.ROMAN_BASELINE, FONT_SIZE_NORMAL);
        texAreaCont.setFont(font);
        texAreaCont.getDocument().addDocumentListener(this);
        JScrollPane scrollCont = new JScrollPane(texAreaCont);

        JLabel labComm = new JLabel(AddQuestion.TEACHER_COMMENT);
        texAreaComm = new JTextArea(3, 30);
        font = new Font(FONT_FAMILY, Font.ROMAN_BASELINE, FONT_SIZE_SMALL);
        texAreaComm.setFont(font);
        JScrollPane scrollComm = new JScrollPane(texAreaComm);

        JLabel labGrade = new JLabel(AddQuestion.QUESTION_GRADE_LABEL);
        comBoxGrade = new JComboBox();
        setComBoxGrade();

        JLabel labCat = new JLabel(AddQuestion.CATEGORY_LABEL);
        comBoxCat = new JComboBox();
        setCategoriesToComboBox();

        JTabbedPane tabPanQues = buildQuesTabPane();
        butSave = new JButton(AddQuestion.SAVE_BUTTON);
        butSave.addActionListener(this);

        butChange = new JButton(AddQuestion.EDIT_BUTTON);
        butChange.addActionListener(this);
        butChange.setVisible(false);

        butClear = new JButton(AddQuestion.RESET_BUTTON);
        butClear.addActionListener(this);

        JPanel panMain = new JPanel(new GridBagLayout());
        ExamGridBagConstraints cons = new ExamGridBagConstraints(0);

        cons.setAtGrid(0, 0);
        panMain.add(butClear, cons);

        cons.nextRow();
        panMain.add(labCont, cons);

        cons.nextRow();
        panMain.add(scrollCont, cons);

        cons.gridwidth = 1;
        cons.nextRow();
        panMain.add(labGrade, cons);

        cons.nextColumn();
        panMain.add(comBoxGrade, cons);

        cons.nextRow();
        panMain.add(labCat, cons);

        cons.nextColumn();
        panMain.add(comBoxCat, cons);

        cons.gridwidth = 0;
        cons.nextRow();
        panMain.add(labComm, cons);

        cons.nextRow();
        panMain.add(scrollComm, cons);

        cons.nextRow();
        panMain.add(tabPanQues, cons);

        cons.fill = GridBagConstraints.CENTER;
        cons.nextRow();
        cons.ipadx = 2;
        panMain.add(butSave, cons);

        cons.nextColumn();
        panMain.add(butChange, cons);

        this.add(panMain);
    }

    private JTabbedPane buildQuesTabPane ()
    {
        JTabbedPane tabPan = new JTabbedPane();
        JPanel panABC = new JPanel();
        JPanel panGap = new JPanel();
        JPanel panOpen = new JPanel();

        gapPan = new GapPanel();
        abcPan = new ABCPanel();
        openPan = new OpenPanel();

        setExcludingPanels();

        panABC.setLayout(new BoxLayout(panABC, BoxLayout.Y_AXIS));
        panABC.add(abcPan);

        panGap.setLayout(new BoxLayout(panGap, BoxLayout.Y_AXIS));
        panGap.add(gapPan);

        panOpen.setLayout(new BoxLayout(panOpen, BoxLayout.Y_AXIS));
        panOpen.add(openPan);

        tabPan.add(AddQuestion.TAB_ABCD_LABEL, panABC);
        tabPan.add(AddQuestion.TAB_GAP_LABEL, panGap);
        tabPan.add(AddQuestion.TAB_OPEN_LABEL, panOpen);

        return tabPan;
    }

    public void insertUpdate (DocumentEvent arg0)
    {
        if (gapPan.isActive())
        {
            int numGap = oGapCalc.CalcGapNum(texAreaCont.getText());
            gapPan.setGapNum(numGap);
        }
    }

    public void removeUpdate (DocumentEvent arg0)
    {
        if (gapPan.isActive())
        {
            int numGap = oGapCalc.CalcGapNum(texAreaCont.getText());
            gapPan.setGapNum(numGap);
        }
    }

    public void changedUpdate (DocumentEvent arg0)
    {
    }

    public void actionPerformed (ActionEvent evt)
    {
        if (evt.getSource() == butSave)
        {
            if (checkFill())
            {
                Question ques = prepareQuestion();
                try
                {
                    quesMan.add(ques);
                    new PopupDialog().createPopupDialog(
                            AddQuestion.SUCCESS_QUESTION_ADD);
                    searchPan.refresh();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                new PopupDialog().createPopupDialog(
                        AddQuestion.ERROR_QUESTION_ADD);
            }
        }
        else if (evt.getSource() == butChange)
        {
            if (checkFill())
            {
                Question ques = prepareQuestion();
                try
                {
                    quesMan.update(ques);
                    new PopupDialog().createPopupDialog(
                            AddQuestion.SUCCESS_QUESTION_EDIT);
                    searchPan.refresh();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                new PopupDialog().createPopupDialog(
                        AddQuestion.ERROR_QUESTION_EDIT);
            }
        }
        else if (evt.getSource() == butClear)
        {
            clearFields();
            butChange.setVisible(false);
        }
        else if (evt.getSource() == comBoxCat)
        {
            setCategoriesToComboBox();
        }
    }

    /**
     * wypelnij wszystkie pola w panelu danymi pochadzacymi z bazy danych o
     * konkretnym pytaniu
     */
    public void FillPanel (IQuestion ques)
    {
        texAreaCont.setText(ques.getContent());
        texAreaComm.setText(ques.getComment());
        comBoxGrade.setSelectedItem(ques.getGrade());
        comBoxCat.setSelectedItem(ques.getCategory());

        if (ques.isTypeABCD())
        {
            abcPan.setActive();
            abcPan.fillPanel(ques);
        }
        if (ques.isTypeGap())
        {
            gapPan.setActive();
            gapPan.fillPanel(ques);
        }
        if (ques.isTypeOpened())
        {
            System.out.println("Ustawiam open na aktywne");
            openPan.setActive();
        }
        quesId = ques.getId();
    }

    public void clearFields ()
    {
        texAreaCont.setText("");
        texAreaComm.setText("");
        comBoxGrade.setSelectedIndex(0);
        comBoxCat.setSelectedIndex(0);
        gapPan.clearFields();
        abcPan.clearFields();
        openPan.clearFields();
        butChange.setVisible(true);
        quesId = 0;
    }

    private int calcType ()
    {
        int type = 0;
        if (abcPan.isActive())
        {
            type += TypeCalculator.setABCD();
            System.out.println("typ abcd");
        }
        if (gapPan.isActive())
        {
            type += TypeCalculator.setGap();
            System.out.println("typ gap");
        }
        if (openPan.isActive())
        {
            type += TypeCalculator.setOpen();
            System.out.println("typ open");
        }

        return type;
    }

    /**
     * Wypelnij pytanie danymi pochodzacymi z okreslonych paneli/pol
     * @return Question wypelnione danymi pytanie
     */
    private Question prepareQuestion ()
    {
        int quesType = calcType();
        int quesGrade = (Integer) comBoxGrade.getSelectedItem();

        Question tempQues = new Question(quesId, quesType, texAreaCont.getText(),
                quesGrade);
        tempQues.setComment(texAreaComm.getText());

        Answers ans = new Answers();
        ans.setAnswersABCD(abcPan.getAnswers());
        ans.setAnswersGap(gapPan.getAnswers());
        tempQues.setAnswers(ans);
        String categ = comBoxCat.getSelectedItem().toString();
        System.out.println("Categ" + categ);
        tempQues.setCategory(categ);

        return tempQues;
    }

    /**
     * Sprawdz czy wszystkie wymagane pola sa wypelnione
     * @return true  - wszystkie pola sa wypelnione i sa poprawne
     *         false -brak wypelnienia
     */
    private boolean checkFill ()
    {
        if (texAreaCont.getText().equals(""))
        {
            return false;
        }

        if (abcPan.isActive())
        {
            return checkCorrectQuesNum();
        }
        return true;
    }

    public void setSearchPanel (SearchQuestionPanel searchPan)
    {
        this.searchPan = searchPan;
    }

    private void setCategoriesToComboBox ()
    {
        List<String> categoriesList = null;
        comBoxCat.removeAllItems();

        try
        {
            ICategorySrc categories = TableObjectFactory.getInstance().
                    getCategory();
            categoriesList = categories.get();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        for (String category : categoriesList)
        {
            comBoxCat.addItem(category);
        }
    }

    private void setComBoxGrade ()
    {
        for (int i = MIN_GRADE; i <= MAX_GRADE; i++)
        {
            comBoxGrade.addItem(i);
        }
    }

    public void updateCategories ()
    {
        setCategoriesToComboBox();
    }

    private void setExcludingPanels ()
    {
        gapPan.setPanel1(openPan);
        gapPan.setPanel2(abcPan);

        abcPan.setPanel1(openPan);
        abcPan.setPanel2(gapPan);

        openPan.setPanel1(gapPan);
        openPan.setPanel2(abcPan);
    }

    private boolean checkCorrectQuesNum ()
    {
        ArrayList answers = abcPan.getAnswers();

        if (answers.size() < MIN_ABCD_ANSWERS_NO)
        {
            return false;
        }
        else
        {
            return checkIfIsCorrectAnswer(answers);
        }
    }

    private boolean checkIfIsCorrectAnswer (ArrayList answers)
    {
        for (int i = 0; i < answers.size(); i++)
        {
            AnswerABCD answer = (AnswerABCD) answers.get(i);
            if (answer.isCorrect())
            {
                return true;
            }
        }
        return false;
    }
}
