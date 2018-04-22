package my.servlet_v1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBUtil.DBUtil;

/**
 * Servlet implementation class Validate
 */
@WebServlet("/validate")
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet resultset = null; 
		String username = request.getParameter("username");
		System.out.println(username);
		String password = request.getParameter("password");
		System.out.println(password);
		
		try {
//			Step1 : get the driver
//			Step2 : get connection
//			see DBUtil.java
			con = DBUtil.getMeConnectionObj();
			System.out.println("connection established");
//			Step3 : create a statement
			stmt = con.createStatement();

/*
			// bad query will meet SQL injection.
			// by writing code like this, we will face a problem called SQL injection, 
			// which means if you input the password like this "123' or 1='1", whatever you input inside the user name , it will work
			// the solution to this problem is use another strategy to retrieve data from the database.
			// don't write username and password in the same query, we first retrieve the username, and retrieve the password from database
			// then compare the password user input before to the password we retrieved from the database
			String query = "select * \n" + 
					"from servletlogin \n" + 
					"where username = '"+username+"' and password = '"+password+"'"; 
			System.out.println(query);
			
			resultset = stmt.executeQuery(query);  // remove the ";" inside the " "
			System.out.println("executeQuery");
			// validate user information if resultset is not null, it means the form get a value from the database
			System.out.println("resultset.next() is "+resultset);
*/
			String query = "select password \n" + 
					"from servletlogin \n" + 
					"where username = '"+username+"'"; 
			System.out.println(query);
			resultset = stmt.executeQuery(query); 
			System.out.println(resultset);
//			System.out.println("resultset.next() is "+resultset.next());
			
			if(resultset.next())
			{
				// which means there is a record at least 
				String dbpassword = resultset.getString(1);
				System.out.println(dbpassword);
				if(dbpassword.equals(password))
				{
					//which means user is valid
					HttpSession session = request.getSession();
					// the default session time is 30 mins, we can change it by calling this function (second)
//					session.setMaxInactiveInterval(20);
					
					String keep = request.getParameter("keep");
					if(keep!=null)
					{
						// put username and password into cookie
						//Step 1: Create Cookie
						Cookie myUserName = new Cookie("username", username);
						Cookie myPassword = new Cookie("password", password);
						
						//Step 2: Set active time 
						myUserName.setMaxAge(14*24*3600);
						myPassword.setMaxAge(14*24*3600);
						
						//Step 3: Write cookie back to browser 
						response.addCookie(myUserName);
						response.addCookie(myPassword);
						System.out.println("Cookies are added");
						// then you need to modify the welcome servlet
					}
					
					
					
					session.setAttribute("username", username);
					session.setAttribute("password", password);
					//this sessionId is assigned per browser which means, no matter how many tabs I opened in a sing browser, the session id will be the same
					String sessionId = session.getId();
					session.setAttribute("sessionid", sessionId);
//					response.sendRedirect("welcome?username="+ username+"&&password="+password); 
					response.sendRedirect("welcome");
				}
				else
				{
					response.sendRedirect("login?info=invalid user");
				}
			}
			else
			{
				// which means the username is invalid
				response.sendRedirect("login?info=invalid username");
			}
/*			
			if(resultset!=null )
			{
				HttpSession session = request.getSession();
				// the default session time is 30 mins, we can change it by calling this function (second)
//				session.setMaxInactiveInterval(20);
				
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				//this sessionId is assigned per browser which means, no matter how many tabs I opened in a sing browser, the session id will be the same
				String sessionId = session.getId();
				session.setAttribute("sessionid", sessionId);
//				response.sendRedirect("welcome?username="+ username+"&&password="+password); 
				response.sendRedirect("welcome");
			}
			else
			{
				response.sendRedirect("login");
			}
			*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				if(resultset !=null) // to make the program robust
				{
					resultset.close(); // first close this
				}
				if(stmt != null)
				{
					stmt.close(); // 
				}
				if(con != null)
				{
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
