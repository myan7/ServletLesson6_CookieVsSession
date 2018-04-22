package my.servlet.cookieTest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieTest3_DelCookie
 */
@WebServlet("/cookietest3")
public class CookieTest3_DelCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieTest3_DelCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.println("<br>");
		pw.println("<a href=index.jsp>Home</a>");
		pw.println("<br>");
		pw.println("<br>");
		
		
		//Step 1 : retrieve all the Cookies from client side
		Cookie[] allCookies = request.getCookies();
		
		int i = 0;
		
		//Step 2 : best practice to check if there is any Cookie in the client side
		if(allCookies != null)
		{
		//Step 3: retrieve Cookie one by one
			for(i=0;i<allCookies.length;i++)
			{
		//Cookie is stored in a table-like form, which contains key-value pair
				Cookie temp = allCookies[i];
				if(temp.getName().equals("name"))
				{
					temp.setMaxAge(0);
					pw.println("Cookie with name as key was deleted");
					pw.println(temp.getValue());
					break;
				}
			}
			// if not found, it probably be outdated
			if(allCookies.length == i)
			{
				pw.println("Cookie is out dated.");
			}
		}
		else
		{
			pw.println("Cookie with name as key is probably outdated or doesn't exist.");
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
