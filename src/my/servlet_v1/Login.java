package my.servlet_v1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBUtil.DBUtil;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		Connection con = null;
//		String jdbcURL = "jdbc:oracle:thin:@localhost:1521/orcl";
//		String jdbcUser = "system";
//		String jdbcPsw = "oracle";
//		try {
////			Step1 : get the driver
//			Class.forName("oracle.jdbc.driver.OracleDriver"); 
////			Step2 : get connection
//			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPsw);
//			con = DBUtil.getMeConnectionObj();
////			Step3 : create a statement
//			Statement stmt = con.createStatement();
//			ResultSet resultset = stmt.executeQuery("select top 1");
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//make it Chinese friendly
		response.setContentType("text/html; charset=gbk");
		
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<body>");
		// get error message
		String info = (String)request.getParameter("info");
		if(info !=null)
			pw.println("<h1>Info: "+info+"</h1><br>");
		
		pw.println("<h1>login page</h1>");
		pw.println("<form action = validate method = post>");
		pw.println("Username:<input type='text' name='username'><br>");
		pw.println("Password:<input tpye='password' name='password'><br>");
	
		pw.println("<input type=checkbox name=keep value=2>");
		pw.println("keep password for 2 weeks");
		pw.println("<br>");
		
		pw.println("<input type  = 'submit' value='login'>");
		
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

}
