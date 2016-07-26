package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import model.HdzEmployee;

/**
 * Servlet implementation class ActionSubmit
 */
@WebServlet("/ActionSubmit")
public class ActionSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActionSubmit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		HdzEmployee employee = (HdzEmployee) session.getAttribute("employee");
		String urlToRedirect = null;
		if (employee == null) {
			request.setAttribute("message", "Log in!!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			String role = (String) session.getAttribute("role");
			session.setAttribute("applicationid", request.getAttribute("applicationid"));
			if (role.equals("ComplianceOfficer")) {
				urlToRedirect = "/Nationalityform";

			} else if (role.equals("HRAssistant")) {
				urlToRedirect = "/Workhistoryreferenveform";

			} else if (role.equals("HRManager")) {
				urlToRedirect = "/InterviewForm";

			} else if (role.equals("HRSpecialist")) {
				urlToRedirect = "/EducationForm";

			} else if (role.equals("HealthCareProfessional")) {
				urlToRedirect = "/DrugScreenForm";

			} else if (role.equals("HiringManager")) {
				urlToRedirect = "/InterviewForm";

			} else if (role.equals("Employee")) {
				urlToRedirect = "/InterviewForm";
			}

			JSONObject jobj = new JSONObject();

			try {
				jobj.put("url", urlToRedirect);
			} catch (JSONException e) {
				request.setAttribute("message", "Something went wrong!!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			response.getWriter().write(jobj.toString());
		}
	}

}