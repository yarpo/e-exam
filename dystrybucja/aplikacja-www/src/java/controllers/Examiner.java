/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.upload.FileUploader;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author yarpo
 */
public class Examiner extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, ClassNotFoundException {

	String action = request.getParameter("action");
	RequestDispatcher view;
	if (action == null) {
	    action = new String("");
	}

	if (action.equals("uploaded")) {
	    FileUploader uploader = new FileUploader(request);
	    try {
		uploader.uploadFile(request);
		request.setAttribute("status", true);
		
	    } catch (FileUploadException ex) {
		request.setAttribute("status", false);
		Logger.getLogger(Examiner.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
		view = request.getRequestDispatcher("uploaded.jsp");
	    }
	}
	else {
	    view = request.getRequestDispatcher("upload.jsp");
	}
	view.forward(request, response);
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
	    Logger.getLogger(Examiner.class.getName()).log(Level.SEVERE, null, ex);
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
    throws ServletException, IOException {
	try {
	    processRequest(request, response);
	} catch (ClassNotFoundException ex) {
	    Logger.getLogger(Examiner.class.getName()).log(Level.SEVERE, null, ex);
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
