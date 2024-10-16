package models.upload;

import beans.imports.Exam;
import beans.imports.interfaces.IQuestion;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import models.ExamHandler.ExamHandler;
import models.imports.ExamImport;
import models.imports.XMLImporter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 *
 * @author michal
 */
public class FileUploader {

    protected HttpServletRequest request;
    protected int maxMemorySize;
    protected String tempDirectory;

    public FileUploader(HttpServletRequest request) {
	this.request = request;
	this.tempDirectory = "pliki";
	this.maxMemorySize = 1000000; //1MB
    }

    public void uploadFile(HttpServletRequest request) throws FileUploadException, IOException, ClassNotFoundException {

	if (!ServletFileUpload.isMultipartContent(request)) {
	    throw new FileUploadException();
	}
	// Create a factory for disk-based file items
	DiskFileItemFactory factory = new DiskFileItemFactory();

	// Set factory constraints
	factory.setSizeThreshold(maxMemorySize);
	factory.setRepository(new File(tempDirectory));

	// Create a new file upload handler
	ServletFileUpload upload = new ServletFileUpload(factory);

	// Parse the request
	List <FileItem> items = upload.parseRequest(request);
	processUploadedItems(items);
    }

    protected void processUploadedItems(List<FileItem> items) throws IOException, ClassNotFoundException {
	// Process the uploaded items
	Iterator iter = items.iterator();
	while (iter.hasNext()) {
	    FileItem item = (FileItem) iter.next();

	    if (item.isFormField()) {
		processFormField(item);
	    } else {
		processUploadedFile(item);
	    }
	}
    }

    protected void processFormField(FileItem item) {
	System.out.println("FormFieldName: "+item.getFieldName());
	System.out.println("FormFieldString: "+item.getString());
    }

    protected void processUploadedFile(FileItem item) throws IOException, ClassNotFoundException {
	InputStream uploadedStream = item.getInputStream();
	ExamImport importer = new ExamImport(new XMLImporter(uploadedStream));
	importer.importFromInputStream();

	Exam examBean = importer.getExam();
	List<IQuestion> questionList = importer.getQuestions();
	this.request.setAttribute("exam", examBean);
	this.request.setAttribute("questions", questionList);
	this.request.setAttribute("size", questionList.size());
	ExamHandler handler = ExamHandler.getInstance();
	handler.setExam(examBean);
	handler.setQuestionList(questionList);
	uploadedStream.close();
    }
}
