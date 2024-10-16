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
import javax.swing.JPanel;
import logic.question.AnswerGap;
import logic.question.interfaces.IAnswers;
import logic.question.interfaces.IQuestion;

/**
 * Panel zawierajacy wszystkie minipanele typu gap
 * @author michal
 */
public class GapPanel extends SelPanel
{
    private ArrayList lGapPans;
    private JPanel panAllAns;
    private int iCurGridY = 0;
    private int iGapNum;

    public GapPanel ()
    {
        lGapPans = new ArrayList<GapAnsPanel>();
        panAllAns = new JPanel(new GridBagLayout());

        JPanel panTemp = buildActivePanel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panTemp);
        this.add(panAllAns);
        panAllAns.setVisible(false);
    }

    /**
     * Zwroc wszystki odpowiedzi z calego panelu
     * @return
     */
    public ArrayList getAnswers ()
    {
        AnswerGap tempAns = new AnswerGap();
        ArrayList<AnswerGap> list = new ArrayList();
        for (int i = 0; i < lGapPans.size(); i++)
        {
            tempAns = new AnswerGap();
            GapAnsPanel tempPan = (GapAnsPanel) lGapPans.get(i);
            tempAns.setContent(tempPan.getAnswer());
            tempAns.setGapNumber(i);
            list.add(tempAns);
        }
        return list;
    }

    public void addAnswer ()
    {

        GapAnsPanel panAns = new GapAnsPanel();
        cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = iCurGridY;
        lGapPans.add(panAns);
        panAllAns.add(panAns, cons);

        cons.gridx = 1;
        cons.gridy = iCurGridY;

        iCurGridY++;
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource() == chBoxActive)
        {
            bActive = chBoxActive.isSelected();
            panel1.setUnactive();
            panel2.setUnactive();
            if (bActive == false)
            {
                panAllAns.setVisible(false);
            }
            else
            {
                panAllAns.setVisible(true);
            }
        }
    }

    /**
     * Odswierz ekran po dodaniu/usunieciu odpowiedzi
     */
    private void reval ()
    {
        panAllAns.revalidate();
        panAllAns.repaint();
    }

    public void setGapNum (int gapNum)
    {
        if (gapNum != iGapNum)
        {
            update(gapNum);
            iGapNum = gapNum;
        }
    }

    /**
     * zaktualizuj panel(odejmij dodaj pola na odpowiedzi)
     * @param gapNum
     */
    private void update (int gapNum)
    {
        int gapDif = Math.abs(iGapNum - gapNum);
        if (gapNum < iGapNum)
        {
            for (int i = 0; i < gapDif; i++)
            {
                int ind = lGapPans.size() - 1;
                panAllAns.remove((JPanel) lGapPans.get(ind)); //usuniecie ostatiego elementu
                lGapPans.remove(ind);
            }
        }
        else
        {
            for (int i = 0; i < gapDif; i++)
            {
                addAnswer();
            }
        }
        reval();

    }

    @Override
    public void clearFields ()
    {
        iGapNum = lGapPans.size();
        for (int i = lGapPans.size() - 1; i >= 0; i--)
        {
            panAllAns.remove((JPanel) lGapPans.get(i));
        }
        lGapPans.clear();
        chBoxActive.setSelected(false);
        bActive = false;

    }

    public void fillPanel (IQuestion ques)
    {
        IAnswers ans = ques.getAnswers();

        for (int i = 0; i < ans.getAnswersGap().size(); i++)
        {
            addAnswer();
            GapAnsPanel tempPan = (GapAnsPanel) lGapPans.get(i);
            tempPan.fillData(ans.getAnswersGap().get(i));
        }
        panAllAns.setVisible(true);
        reval();
    }
}
