/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.helpers;
/**
 * Klasa pomocnicza, stworzona by gui interfejsu nie zawieralo za duzo logiki.
 * Jej zadaniem jest zliczenie gapow w podanym tekscie
 * @author michal
 */
public class GapCalc
{
    public GapCalc ()
    {
    }

    /**
     * Zlicz liczbe "gapow" w tresci zadania
     * @return liczbe gapow
     */
    public int CalcGapNum (String text)
    {
        int gapNum = 0;
        int index = 0;

        while (index != -1)
        {
            index = text.indexOf("{}", index + 1);

            if (index != -1)
            {
                gapNum++;
            }
        }

        return gapNum;
    }
}
