package my.servlet_v1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBUtil.DBUtil;

/**
 * Servlet implementation class Welcome
 */
@WebServlet("/welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Welcome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String sessionId = (String) session.getAttribute("sessionid");
		
//		System.out.println(username);// to monitor if name is still in session
		System.out.println("==================Pagination=======================");
		PrintWriter pw = response.getWriter();
		
		
		// for store information from cookie
		String myUserName ="";
		String myPassword ="";
		// this is for preventing illegal login by directly go to welcome page.
		if(username == null)
		{
			// if there is no user information in the session, check if it has those information in cookies
			// get all the cookies from client side
			Cookie[] allCookies = request.getCookies();
			int i =0;
			// if allCookies are not null
			if(allCookies != null)
			{
				for(i=0;i<allCookies.length;i++)
				{
					Cookie temp = allCookies[i];
					if(temp.getName().equals("username"))
						myUserName =temp.getValue();
					else if(temp.getName().equals("password"))
						myPassword = temp.getValue();
				}
				if(!(myUserName.equals("")|| myPassword.equals("")))
				{
					response.sendRedirect("validate?username="+myUserName+"&password="+myPassword);
				}
			}
			
			response.sendRedirect("login?info=User name or password error.");
		}
		
		
		pw.println("<html>");
		pw.println("<body><center>");
		pw.println("<img src = imgs/mylogo.png>");
		pw.println("<p>Welcome! </p>");
		pw.append(username).append(" "+password);
		pw.println("<br>");
		pw.append(sessionId);
		pw.println("<br>");
		pw.println("<a href = logout >Logout</a>");
		
		// ============================= Pagination =======================================
		// create 4 variables
		int pageSize = 4; // there will be 4 rows in each page.
		int pageNow = 1; // which page you will be display
		int rowCount = 0; // how many rows are there in database 
		int pageCount = 0; // how many pages will be based on rows
		Connection con = DBUtil.getMeConnectionObj();
		String query = "select count(*) from servletlogin";
		
		// dynamically receive value for pageNow
		String sPageNow = request.getParameter("page");
		// if it is the first time a user enter this welcome page, sPageNow should be null
		if(sPageNow != null)
		{
			pageNow = Integer.valueOf(sPageNow);
		}
		
		// get rowCount;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next())
			{
				System.out.println("count how may rows are retrieved");
				rowCount = resultSet.getInt(1);
				System.out.println(rowCount);
			}
			// count how many pages will be shown in page
			pageCount = (rowCount-1)/pageSize+1;
			
			query = "select * from servletlogin where id not in (select id from servletlogin where rownum <= "+pageSize*(pageNow-1) +") and\n" + 
					"rownum <= "+ pageSize+"";
			// assign the question mark, somehow it doesn't work... so I comment it out.
//			pstmt.setInt(0, pageSize*(pageNow-1));
//			pstmt.setInt(1, pageSize);
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			
			pw.println("<table border = 1 >");
			pw.println("<tr><th>ID</th><th>Username</th><th>Password</th></td>");
			while(resultSet.next())
			{
				System.out.println("Table will be shown.");
				pw.println("<tr>");
				pw.println("<td>"+resultSet.getInt(1)+"</td>");
				pw.println("<td>"+resultSet.getString(2)+"</td>");
				pw.println("<td>"+resultSet.getString(3)+"</td>");
				pw.println("</tr>");
			}
			
			pw.println("</table>");
			
			if(pageNow!=1)
				pw.println("<a href= welcome?page="+(pageNow-1)+">Previous</a>");
			// display hyperlink
			for(int i =pageNow;i<=pageNow+pageSize-1 && i<=pageCount;i++)
			{
				pw.println("<a href= welcome?page="+i+">"+i+"</a>");
				//how to dynamically receive this page?
				// move up to line 73
			}
			if(pageNow != pageCount)
				pw.println("<a href= welcome?page="+(pageNow+1)+">Next</a>");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		pw.println("</center></body>");
		pw.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
