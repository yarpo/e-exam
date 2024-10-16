/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models.ExamHandler;

import beans.imports.interfaces.IQuestion;
import beans.imports.Section;
import beans.imports.extension.QuestionExt;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author misiakw
 */
public class QuestionMatrix {
    private Map<Section, Map<Integer, IQuestion>> matrix;
    private Map<Section, Integer> remainingSize;
    private List<Section> sections;
    private Random rand = new Random(Calendar.getInstance().getTimeInMillis());

    public QuestionMatrix(List<Section> sekcje, Map<Integer, IQuestion> pytania){
        sections = new ArrayList<Section>();
        matrix = new HashMap<Section, Map<Integer, IQuestion>>();
        remainingSize = new HashMap<Section, Integer>();

        for (int i=0; i<sekcje.size(); i++){
            sections.add(sekcje.get(i).duplicate());
            Map<Integer, IQuestion> tmp = new HashMap<Integer, IQuestion>();
            for (int j=0; j<sekcje.get(i).getQuestionsInSection().size(); j++)
                tmp.put(sekcje.get(i).getQuestionsInSection().get(j),
                        pytania.get(sekcje.get(i).getQuestionsInSection().get(j)).duplicate());

            remainingSize.put(sections.get(i), sections.get(i).getQuestionsNo());
            matrix.put(sections.get(i), tmp);
        }
    }

    //get section with minimal nonnegativ ratio
    private Section getMinRatioSection(){
        Section pos = null;
        float ratio = 0;

        for (int i=0; i<sections.size(); i++){
            float actual;
            if (remainingSize.get(sections.get(i)) > 0)
                    actual = (float) matrix.get(sections.get(i)).size() / (float) remainingSize.get(sections.get(i));
            else
                actual = -1;
            if (ratio == 0 || (actual > 0 && actual < ratio)){
                ratio = actual;
                pos = sections.get(i);
            }
        }
        return pos;
    }

    //get random question
    public IQuestion getQuestion(){
        Section chosen = getMinRatioSection();
        if (chosen == null)
            return null;

        Set<Integer> keys = matrix.get(chosen).keySet();
        Iterator<Integer> iter = keys.iterator();

        int key;
        do{
            key = rand.nextInt(keys.size());
        }while (key < 0 || key >= keys.size());

        for (int i=0; i<key; i++){
            iter.next();
        }
        key = iter.next();

        QuestionExt ret = new QuestionExt(matrix.get(chosen).get(key));
        ret.setPoints(chosen.getPoints());

        for (int i=0; i<sections.size(); i++){
            if (matrix.get(sections.get(i)).containsKey(key)){
                matrix.get(sections.get(i)).remove(key);
                int tmp = remainingSize.get(sections.get(i));
                remainingSize.remove(sections.get(i));
                remainingSize.put(sections.get(i), tmp-1);
            }
            if (matrix.get(sections.get(i)).size() == 0){
                remainingSize.remove(sections.get(i));
                matrix.remove(sections.get(i));
                sections.remove(i);
                i--;
            }
        }

        return ret;
    }

}
