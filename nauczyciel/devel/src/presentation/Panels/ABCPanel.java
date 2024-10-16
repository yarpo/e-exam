/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import logic.question.AnswerABCD;
import logic.question.interfaces.IAnswers;
import logic.question.interfaces.IQuestion;

/**
 * panel zawierajacy grupe odpowiedzi abcd
 * @author sokol
 */
public class ABCPanel extends SelPanel
{
    private ArrayList lAnsPans;
    private ArrayList lDelButs;
    private JButton butAddAns;
    private JPanel panAbcAns;
    private int iCurGridY = 0;

    public ABCPanel ()
    {
        super();
        lAnsPans = new ArrayList<ABCAnsPanel>();
        lDelButs = new ArrayList<JButton>();
        butAddAns = new JButton("Dodaj");
        butAddAns.addActionListener(this);
        panAbcAns = new JPanel(new GridBagLayout());

        JPanel panTemp = buildActivePanel();
        buildAnswerPanel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panTemp);
        this.add(panAbcAns);
        panAbcAns.setVisible(false);
    }

    private void buildAnswerPanel ()
    {
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));

        tempPanel.add(butAddAns);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 1;
        panAbcAns.add(tempPanel, cons);

        iCurGridY = 2;
    }

    /**
     * Zwroc wszystki odpowiedzi z calego panelu
     * @return
     */
    public ArrayList getAnswers ()
    {
        AnswerABCD tempAns;
        ArrayList<AnswerABCD> list = new ArrayList();
        for (int i = 0; i < lAnsPans.size(); i++)
        {
            ABCAnsPanel tempPan;
            tempAns = new AnswerABCD();
            tempPan = (ABCAnsPanel) lAnsPans.get(i);
            tempAns.setContent(tempPan.getAnswer());
            tempAns.setCorrect(tempPan.getCorrectness());
            list.add(tempAns);
        }
        return list;
    }

    public void addAnswer ()
    {

        ABCAnsPanel panAns = new ABCAnsPanel();
        JButton butDel = new JButton("usun");
        butDel.addActionListener(this);
        cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = iCurGridY;
        lAnsPans.add(panAns);
        panAbcAns.add(panAns, cons);

        cons.gridx = 1;
        cons.gridy = iCurGridY;

        panAbcAns.add(butDel, cons);
        lDelButs.add(butDel);

        iCurGridY++;
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {

        for (int i = 0; i < lDelButs.size(); i++)
        {
            JButton but = (JButton) lDelButs.get(i);

            if (e.getSource() == but)
            {

                panAbcAns.remove((JPanel) lAnsPans.get(i));
                lAnsPans.remove(i);
                panAbcAns.remove((JButton) lDelButs.get(i));
                lDelButs.remove(i);
                reval();
                break;
            }
        }
        //w skrocie czyscimy ekran i budujemy od nowa
        if (e.getSource() == butAddAns)
        {
            addAnswer();
            reval();
        }
        //TODO skasowac
        else if (e.getSource() == chBoxActive)
        {
            bActive = chBoxActive.isSelected();
            panel1.setUnactive();
            panel2.setUnactive();
            if (bActive == false)
            {
                panAbcAns.setVisible(false);
            }
            else
            {
                panAbcAns.setVisible(true);
            }
        }
    }

    /**
     * Odswierz ekran po dodaniu/usunieciu odpowiedzi
     */
    private void reval ()
    {
        panAbcAns.revalidate();
        panAbcAns.repaint();
    }

    @Override
    public void clearFields ()
    {
        for (int i = lAnsPans.size() - 1; i >= 0; i--)
        {
            panAbcAns.remove((JPanel) lAnsPans.get(i));
            panAbcAns.remove((JButton) lDelButs.get(i));
        }
        lAnsPans.clear();
        lDelButs.clear();
        panAbcAns.setVisible(false);

        chBoxActive.setSelected(false);
        bActive = false;

        reval();
    }

    public void fillPanel (IQuestion ques)
    {
        IAnswers ans = ques.getAnswers();
        for (int i = 0; i < ans.getAnswersABCD().size(); i++)
        {
            addAnswer();
            ABCAnsPanel tempPan = (ABCAnsPanel) lAnsPans.get(i);
            tempPan.fillData(ans.getAnswersABCD().get(i));
        }
        panAbcAns.setVisible(true);
        reval();

    }
}
