package harish;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/menu")
public class menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("<html><head><title>servlet 1</title></head><body><style>"
				+ "body{"
				+ "background-image:url(\"https://previews.123rf.com/images/mohol/mohol1211/mohol121100004/16461008-menu-card-design-for-restaurant.jpg\");"
				+ "}"
				+ ".center{"
				+ "text-align:center;"
				+ "margin-left:400px;"
				+ "margin-right:400px;"
				+ "}"
				+ "table, th, td { "
				+ "  border: 1px solid black;  "
				+ "  border-collapse: collapse;"
				+ "  font-size:30px;  "
				+ "}  "
				+ "th, td {  "
				+ "  padding: 10px;  "
				+ "} "
				+ ".aa{"
				+ "background-color:skyblue;"
				+ "border-radius:40px;"
				+ "padding:30px;"
				+ "}"
				+ "</style>");
		out.print("<div class='center'><h1>MENU</h1><div class=\"aa\"><form method=\"get\" action=\"./bill\"><table><tr><th>ITEMS</th><th>COST</th><th>ORDER FROM USER</th></tr>");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/wt","root","harish");
		    Statement stmt = con.createStatement();
		    ResultSet resultset = stmt.executeQuery("select * from menu") ; 
		    String z="0";
		    int i=0;
		    while(resultset.next())
		    {
		    	 
			     out.print("<tr><td>"+resultset.getString(1)+"</td><td>"+ resultset.getInt(2)+"</td><td><select name=\"v"+z+"\">"
			     		+ "    <option value=\"0\">0</option>"
			     		+ "    <option value=\"1\">1</option>"
			     		+ "    <option value=\"2\">2</option>"
			     		+ "</select></td></tr>");
			     i=1+Integer.parseInt(z); 
			     z=String.valueOf(i);
			     
			     
		    }
		    out.print("</table><input type=\"submit\" name=\"btn_submit\" value=\"GET BILL\"></form></div></div>"
		
		    		+ "</body></html>");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
}
