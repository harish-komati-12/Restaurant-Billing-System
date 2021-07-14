package harish;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/bill")
public class bill extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String[] checkedIds = new String[6];
		
		checkedIds[0]=request.getParameter("v0");
		checkedIds[1]=request.getParameter("v1");
		checkedIds[2]=request.getParameter("v2");
		checkedIds[3]=request.getParameter("v3");
		checkedIds[4]=request.getParameter("v4");
		checkedIds[5]=request.getParameter("v5");
		for(String name : request.getParameterMap().keySet()){      
	        System.out.println(name +": " + request.getParameter(name));        
	    }
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/wt","root","harish");
		    Statement stmt = con.createStatement();
		    ResultSet resultset = stmt.executeQuery("select * from menu") ; 
		    int cost[]=new int[6];
		    String item[]=new String[6];
		    int i=0;
		    int sum=0;
		    while(resultset.next())
		    {
		    	item[i]=resultset.getString(1);
		    	 cost[i]=resultset.getInt(2);
			     i++;
		    }
		    int size=i;
		    out.print("<html><head><title>servlet 2</title></head><body><style>"
		    		+ "body{"
					+ "background-image:url(\"https://previews.123rf.com/images/mohol/mohol1211/mohol121100004/16461008-menu-card-design-for-restaurant.jpg\");"
					+ "}"
					+ "table, th, td { "
					+ "	 border: 1px solid black;  "
					+ "  border-collapse: collapse;"
					+ "  font-size:30px;  "
					+ "}  "
					+ "th, td {  "
					+ "  padding: 10px;  "
					+ "} "
					+ ".center{"
					+ "padding-left:30px;"
					+ "text-align:center;"
					+ "margin-left:400px;"
					+ "margin-right:400px;"
					+ "}"
					+ ".aa{"
					+ "background-color:skyblue;"
					+ "border-radius:40px;"
					+ "width:500px;"
					+ "padding:40px;"
					+ "}"
					+ "</style>"
					+ "<div class='center'>"
					+ "<h1 style=\"margin-left:-60px;\">Bill Reciept</h1>"
					+ "<div class='aa'>"
					+ "<table>"
					+ "<tr><th>item</th><th>cost</th><th>no.of.items</th></tr>");
		    for(i=0;i<size;i++)
		    {
		    	System.out.println(checkedIds[i]);
		    	if(!checkedIds[i].equalsIgnoreCase("0"))
		    	{
		    		out.print("<tr><td>"+item[i]+"</td><td>"+cost[i]+"</td><td>"+checkedIds[i]+"</td></tr>");
		    		
		    		sum=sum+(cost[i]*(checkedIds[i].charAt(0)-'0'));
		    	}
		    }
		    out.print("<h1 >TOTAL BILL :"+sum+"</h1></table></body></div></div></html>");
		    
		}
	    catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

