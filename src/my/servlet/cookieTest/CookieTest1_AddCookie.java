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
 * Servlet implementation class CookiesTest1
 */
@WebServlet("/cookietest1")
public class CookieTest1_AddCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieTest1_AddCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=gbk");
		PrintWriter pw = response.getWriter();
		
//		Step 1 : create a Cookie object;
		Cookie cookie = new Cookie("name", "Alex_Yan");  // value in Cookie should not contain blank space
//		Step 2: set Cookie's life time in second, if you set the life time as 0, Cookie will not be stored. see document
		cookie.setMaxAge(30);
//		Step 3: Write Cookie back to Client side
		response.addCookie(cookie);
		
		pw.println("Cookie already added.");
		
		pw.println("<br>");
		pw.println("<a href = index.jsp >Home</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
