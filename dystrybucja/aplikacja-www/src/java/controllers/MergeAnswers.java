/*
 * Kontroler dla operacji scalania egzaminow studentow
 */

package controllers;

import beans.imports.Question;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.AnswerExport.ExportQuestion;
import models.AnswerExport.StudentAnswers;

/**
 *
 * @author Michal, yarpo
 */
public class MergeAnswers extends HttpServlet {

    private static final String EXAMS_PATH = "/XML";
    private static final String RESULT_PATH = "/EXAMS";
    private static final String FILE_EXT = ".xml";

    /**
     * zmienic na "application/octet-stream;charset=UTF-8" jesli ma byc pobierany
     */
    private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";

    private XStream xStream = null;
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws Exception
    {
        String xml = MergeXmls();
        displayXml(response, xml);
    }

    /**
     * Wyswietla xml
     */
    private void displayXml(HttpServletResponse response, String xml) throws Exception
    {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.print( xml );
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            processRequest(request, response);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            processRequest(request, response);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Scalanie wyników";
    }// </editor-fold>

    private String MergeXmls() throws Exception
    {
        File[] files = getSavedStudentsFilesList();
        getXStream();
        List<StudentAnswers> answers = getStudentsAnswers(files);
        String resultXmlFileName = getResultFileName();
        String result = saveEverythingToXMLFile(answers, resultXmlFileName);
        deleteFiles(files);
        return result;
    }

    /**
     * Zwraca liste plikow z katalogu, w ktorym powinny sie znajdowac pliki
     * xml z odpowiedziami studentow
     */
    private File[] getSavedStudentsFilesList() throws Exception
    {
        String path = getRealPath(EXAMS_PATH);
        File dir = new File(path);
        if ( null == dir || !dir.exists())
        {
            throw new Exception("Nie ma katalogu " + path);
        }
        return dir.listFiles();
    }

    /**
     * Zwraca liste struktur z odpowiedziami studentow
     */
    private List<StudentAnswers> getStudentsAnswers(File [] files)
            throws Exception
    {
        List<StudentAnswers> answers = new ArrayList<StudentAnswers>();

        for (File file : files)
        {
            answers.add(readFromFile(file));
        }
        return answers;
    }

    /**
     * Zwraca odpowiedzi zawarte w podanym pliku
     */
    private StudentAnswers readFromFile(File file) throws Exception
    {
        FileInputStream fileStream = new FileInputStream(file);
	ObjectInputStream objInput = xStream.createObjectInputStream(fileStream);
	StudentAnswers studentsAnswers = (StudentAnswers) objInput.readObject();
	objInput.close();
        fileStream.close();

        return studentsAnswers;
    }

    /**
     * Tworzy obiekt XStream
     */
    private void getXStream()
    {
        xStream = new XStream(new DomDriver("UTF-8"));
        xStream.alias("Question", Question.class);
        xStream.alias("StudentAnswers",StudentAnswers.class);
        xStream.alias("Question", ExportQuestion.class);
    }

    /**
     * Zwraca prawidlowa sciezke do pliku wynikowego XML
     */
    private String getResultFileName()
    {
        String path = getRealPath(RESULT_PATH);
        return path + new Date().getTime() + FILE_EXT;
    }

    /**
     * Zwraca pelna sciezke do podanego pliku / folderu
     */
    private String getRealPath(String path)
    {
        return getServletContext().getRealPath(path) + "/";
    }

    /**
     * Zapisuje wszystkie dane do pliku XML
     */
    private String saveEverythingToXMLFile(List<StudentAnswers> answers, String file)
            throws Exception
    {
        String xml = xStream.toXML(answers);
        FileOutputStream fos = new FileOutputStream(file);
	ObjectOutputStream objOutput = this.xStream.createObjectOutputStream(fos);
	objOutput.writeObject(answers);
	objOutput.close();
        //fos.write(xml.getBytes());
        fos.close();

        return xml;
    }

    /**
     * Usuwa pliki XML z examinami poszczegolnych studentow (powinno byc wywolane po scaleniu)
     */
    private void deleteFiles(File [] files)
    {
        for (File file : files)
        {
            System.out.println("Nazwa pliku to" + file);
            if (file.exists())
            {
                file.delete();
            }
            System.out.println(file.exists() ? "Nie usunięto." : "Usunięto.");
        }
    }
}
