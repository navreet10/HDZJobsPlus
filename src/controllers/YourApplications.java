package controllers;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ApplicationsDao;
import dao.TestsDao;
import model.HdzApplicant;
import model.HdzApplication;
import model.HdzJobquestion;
import model.HdzPosition;

/**
 * Servlet implementation class YourApplications
 */
@WebServlet("/YourApplications")
public class YourApplications extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YourApplications() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in YourApplications");
		HttpSession session = request.getSession();
		String nextURL = "yourapplications.jsp";
		HdzApplicant applicant = (HdzApplicant)session.getAttribute("user");		
		List<HdzApplication> applications = null;
		HdzPosition position;
		HdzJobquestion question = null;
		boolean notestsfound = true;
		request.setAttribute("pendingcodingtest", "no");
		
		if (applicant == null) {
			request.setAttribute("message", "Please login in!");			
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {	
			// get all applications for applicantid
			applications = ApplicationsDao.getapplicationsByApplicantid(applicant.getApplicantid()+"");		
			for (HdzApplication application : applications) {	
				//System.out.println("application: "+application.getHdzJob().getHdzPosition().getPosition());
				
				// if the application has not failed and the application coding flag = 'G' (assigned)				
				if (!application.getAppstatus().equals("Fail") && application.getCodingtest().equals("G")) {
					position = application.getHdzJob().getHdzPosition();
					//System.out.println("Position type: "+position.getPositiontype());
					if (position.getPositiontype() != null) {	
						if (position.getPositiontype().equals("developer")) {	
							request.setAttribute("pendingcodingtest", "yes");
							notestsfound = false;
							nextURL = "yourapplications.jsp";
							break;						
						}
					}
				}
			}		
		}
		
		if (notestsfound) {
			request.setAttribute("message", "No tests found.");
		}
		request.getRequestDispatcher(nextURL).forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
