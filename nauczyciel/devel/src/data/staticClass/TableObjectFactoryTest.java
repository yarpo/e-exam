/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.staticClass;

import data.AnswerABCDSrc;
import data.AnswerGapSrc;
import data.ExamSrc;
import data.QuestionSrc;
import data.SQLiteConn;
import data.SectionABCDSrc;
import data.SectionGapSrc;
import data.SectionSrc;
import data.interfaces.IAnswerABCDSrc;
import data.interfaces.IAnswerGapSrc;
import data.interfaces.IExamSrc;
import data.interfaces.IQuestionSrc;
import data.interfaces.ISectionABCDSrc;
import data.interfaces.ISectionGapSrc;
import data.interfaces.ISectionSrc;
import java.sql.SQLException;

/**
 * Fabryka, która zwraca obiekty odwzorowujące bazę danych.
 * Instancję klasy możemy pobrać za pomocą getInstance(). Wówczas można odwołać
 * się do obiektów, które reprezentują tabele w bazie danych, np. getQuestion()
 * dla tabeli "question".
 * @see TableObjectFactory.getInstance()
 * @author michal
 */
public class TableObjectFactoryTest
{
    protected TableObjectFactoryTest ()
            throws ClassNotFoundException, SQLException
    {
        this.conn = new SQLiteConn("jdbc:sqlite:testDB.db");
        this.answerABCD = new AnswerABCDSrc(this.conn.getConnection());
        this.answerGap = new AnswerGapSrc(this.conn.getConnection());
        this.exam = new ExamSrc(this.conn.getConnection());
        this.question = new QuestionSrc(this.conn.getConnection());
        this.taskABCD = new SectionABCDSrc(this.conn.getConnection());
        this.taskGap = new SectionGapSrc(this.conn.getConnection());
        this.taskgroup = new SectionSrc(this.conn.getConnection());
    }

    /**
     * Metoda statyczna.<br />
     * Przykład:<br />
     * IQuestionSrc bazaPytan = TableObjectFactory.getInstance().getQuestion();<br />
     * bazaPytan.delete(idPytania);
     * @return TableObjectFactory, dzięki któremu można uzyskać obiekty odpowiedzialne
     * za obiektowe odwzorowanie bazy danych
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static TableObjectFactoryTest getInstance ()
            throws ClassNotFoundException, SQLException
    {
        if (instance == null)
        {
            instance = new TableObjectFactoryTest();
        }
        return instance;
    }

    public IAnswerABCDSrc getAnswerABCD ()
    {
        return answerABCD;
    }

    public IAnswerGapSrc getAnswerGap ()
    {
        return answerGap;
    }

    public IExamSrc getExam ()
    {
        return exam;
    }

    public IQuestionSrc getQuestion ()
    {
        return question;
    }

    public ISectionABCDSrc getTaskABCD ()
    {
        return taskABCD;
    }

    public ISectionGapSrc getTaskGap ()
    {
        return taskGap;
    }

    public ISectionSrc getTaskgroup ()
    {
        return taskgroup;
    }
    protected static TableObjectFactoryTest instance;
    protected SQLiteConn conn;
    protected IAnswerABCDSrc answerABCD;
    protected IAnswerGapSrc answerGap;
    protected IExamSrc exam;
    protected IQuestionSrc question;
    protected ISectionABCDSrc taskABCD;
    protected ISectionGapSrc taskGap;
    protected ISectionSrc taskgroup;
}
