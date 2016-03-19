package Assignment_2;

/**
 *
 * @author Andrew
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
            {
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) 
                {     
                        HttpSession mySession = request.getSession();

                        Gameazon loggedIn = (Gameazon)mySession.getAttribute("session");

                        String userName = request.getParameter("userName");
                        String password = request.getParameter("password");
                        //String errorMessage;
                        if (loggedIn == null)
                        {
                            out.println("not logged in");
                            if (userName == null || userName.equals("") || 
                            password == null || password.equals(""))
                            {
                                out.println("<!DOCTYPE html>");
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<title>Login</title>");
                                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"main.css\">");
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<a href=\"index\" title=\"Return to Home Page\" id=\"logo\"><img src=\"images/newlogo.png\" alt=\"Gameazon controller logo\"></a>");
                                out.println("<div id=\"container\">");
                                out.println("<div id=\"main-content\">");
                                out.println("<h1>Log in</h1>");
                                out.println("<form action= \"login\" method= \"post\">");
                                out.println("<label for=\"user_name\">User Name</label>");
                                out.println("<input name= \"userName\"type=\"text\" id=\"user_name\">");
                                out.println("<label for=\"password\">Password</label>");
                                out.println("<input name= \"pass\"type=\"password\" id=\"password\">");
                                out.println("<input type=\"submit\" value=\"Login\">");
                                out.println("</form>"); // end form
                                //out.println(errorMessage);
                                out.println("</div>"); // end main-content
                                out.println("</div>");// end container
                                out.println("</body>");
                                out.println("</html>");
                            }
                        }
                        else
                        {
                            String lastName = loggedIn.getLastName();
                            
                            Gameazon checkUser = GameazonUserHashMap.userHashMap.get(lastName);
                            
                                                      
                            if (checkUser != null)
                            {
                                if (userName.equals(checkUser.getUserName()) && password.equals(checkUser.getPassword()))
                                {
                                    out.println("<!DOCTYPE html>");
                                    out.println("<html>");
                                    out.println("<head>");
                                    out.println("<title>GOT EQUIPPED WITH USER ACCOUNT!</title>");
                                    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"main.css\">");
                                    out.println("</head>");
                                    out.println("<body>");
                                    out.println("<a href=\"index\" title=\"Return to Home Page\" id=\"logo\"><img src=\"images/newlogo.png\" alt=\"Gameazon controller logo\"></a>");
                                    out.println("<div id=\"container\">");
                                    out.println("<div id=\"main-content\">");
                                    out.println("<h1>Hi, " + checkUser.getFirstName() + checkUser.getLastName() + "! <br> You are logged in!</h1>");
                                    out.println("<br>");
                                    out.println("<h2><a href=\"storefront\" title=\"Return to Home Page\">Click here to browse our selection!</a></h2>");        
                                    out.println("</div>"); //end main-content
                                    out.println("</div>"); // end container
                                    out.println("</body>");
                                    out.println("</html>");
                                
                                    mySession.setAttribute("session", checkUser);
                                }
                            }
                            else
                            {
                                response.sendRedirect("register");
                            }
                        }
                }
            }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
            {
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) 
                {
                    doGet(request, response);
                }
    }

    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }// </editor-fold>

}