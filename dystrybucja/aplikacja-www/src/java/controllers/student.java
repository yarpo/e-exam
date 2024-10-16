/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import exceptions.UserKeyAllreadyExistsException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import models.AnswerExport.StudentExamAnswer;
import models.ExamHandler.ExamHandler;

/**
 *
 * @author yarpo
 */
public class student extends HttpServlet {

    /**
     * Wektor kluczy wszystkich userow
     */
    private ArrayList<String> usersKeys = new ArrayList<String>();
    /**
     * Stala zawierajaca nazwe pola formualrza, ktore przekazuje dane o kluczu
     */
    private final String KEY = "key";
    /**
     * nazwa strony jsp z formularzem logowania
     */
    private final String LOGIN_SITE = "login.jsp";
    /**
     * nazwa strony jsp z dystrybutorem
     */
    private final String EXAM_SITE = "exam.jsp";
    /**
     * nazwa strony jsp z napisem "Koniec egzamu"
     */
    private final String EXAM_END = "endExam.jsp";

    /**
     * "Logowanie" usera. Jesli jeszcze nikt nie podal takiego klucza - pozwala
     * go ustawic. Gdy istnieje taki klucz, rzuca wyjatek
     */
    private synchronized void addUsersKeys(String key) throws UserKeyAllreadyExistsException {
	if (this.usersKeys.contains(key)) {
	    throw new UserKeyAllreadyExistsException("Nie można po raz kolejny sie zalogować!");
	}
	this.usersKeys.add(key);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
debug("Jestem w kontrolerze student");
	RequestDispatcher view = null;
	String action = request.getParameter("action");
	if (action == null) {
debug("action == null");
	    action = new String("");
	}

	try {
	    if (action.equals("endExam")) {
		StudentExamAnswer studAns = new StudentExamAnswer();
debug("endExam");


		studAns.Export(request);
		view = request.getRequestDispatcher(EXAM_END);
		//view.forward(request, response);
	    }
            else if (action.equals("getQuestions")) {
debug("getQuestions");
		ExamHandler examHandler = ExamHandler.getInstance();
		request.getSession().setAttribute("questionList", examHandler.getQuestions());
		System.out.println("wczytałem");
		view = request.getRequestDispatcher(EXAM_SITE);
	    }
            else if (action.equals("setKey")) {
debug("setKey");
		String userKey = request.getParameter(KEY);
		addUsersKeys(userKey);
		request.getSession().setAttribute("userKey", userKey);
		request.getSession().setAttribute("success", true);
		request.getSession().setAttribute("message", userKey);
		ExamHandler examHandler = ExamHandler.getInstance();
		request.getSession().setAttribute("questionList", examHandler.getQuestions());
debug("wczytałem pytania dla studenta");
		view = request.getRequestDispatcher(EXAM_SITE);
	    }
	    else {
debug("logowanie");
		view = request.getRequestDispatcher(LOGIN_SITE);
	    }

	    view.forward(request, response);
	} catch (UserKeyAllreadyExistsException e) {
	    request.getSession().setAttribute("message", "Działa, ale był taki klucz");
	    request.getSession().setAttribute("success", false);
	    view = request.getRequestDispatcher(LOGIN_SITE);
	    view.forward(request, response);
	}
    }

    private void debug(String s)
    {
        System.out.println(s);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
	try {
	    processRequest(request, response);
	} catch (Exception ex) {
            ex.printStackTrace();
    	    Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	try {
	    processRequest(request, response);
	} catch (ServletException ex) {
	    Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>
}
