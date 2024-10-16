package logic.export;

import java.io.IOException;

/**
 *
 * @author michal
 */
public class QuestionMatchException extends IOException
{
    private int sectionId;
    private String sectionName;

    public QuestionMatchException (int sectionId, String sectionName)
    {
        this.sectionId = sectionId;
	this.sectionName = sectionName;
    }

    public int getExceptionSection ()
    {
        return this.sectionId;
    }

    public String getExceptionSectionName() {
	return this.sectionName;
    }
}
