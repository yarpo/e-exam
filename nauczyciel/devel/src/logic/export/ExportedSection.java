package logic.export;

import java.util.ArrayList;
import java.util.List;
import logic.exams.Section;

/**
 *
 * @author michal
 */
public class ExportedSection extends Section
{
    protected List<Integer> questionsInSection;

    public ExportedSection (int id, int examId, int category, int type,
            int questionsNo, int gradeMin, int gradeMax, int points, String name)
    {
        super(id, examId, category, type, questionsNo,
                gradeMin, gradeMax, points, name);
        this.questionsInSection = new ArrayList<Integer>();
    }

    public ExportedSection (Section section)
    {
        this(section.getId(), section.getExamId(), section.getCategory(), section.
                getType(),
                section.getQuestionsNo(), section.getGradeMin(), section.
                getGradeMax(),
                section.getPoints(), section.getName());
    }

    /**
     *
     * @return Lista Integer z id pytań, które pasują do sekcji
     */
    public List<Integer> getQuestionsInSection ()
    {
        return this.questionsInSection;
    }

    public void setQuestionsInSection (List<Integer> questions)
    {
        this.questionsInSection = questions;
    }

    public void addQuestionInSection (int id)
    {
        this.questionsInSection.add(new Integer(id));
    }
}
