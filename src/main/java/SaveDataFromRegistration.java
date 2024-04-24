import com.db.DbOperation;
import com.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ConnectIOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/SaveDataFromRegistration")
public class SaveDataFromRegistration extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        fetch input value form HttpServletRequest(request)
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String dateOfBirth =req.getParameter("dateOfBirth");

        // => add input data into User object
        User user = new User();
        user.setUserEmail(email);
        user.setDob(Date.valueOf(dateOfBirth));

    // Insert the input data into DB
        try {
            if(DbOperation.register(user))
            {
                // go to output.html
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        PrintWriter out= resp.getWriter();

//        req.setAttribute("name",name);
//        req.getRequestDispatcher("WEB-INF/output.jsp").forward(req, resp);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/pratap_site_database","root","WorkIsLife@004");

            PreparedStatement preparedStatement = connection.prepareStatement("insert into user_details values(?,?,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);
            preparedStatement.setDate(4, Date.valueOf(dateOfBirth));

            int count = preparedStatement.executeUpdate();

            if(count>0){
                out.println("User Registered Successfully");
                req.setAttribute("name",name);
                req.getRequestDispatcher("WEB-INF/output.jsp").forward(req, resp);
            }else {
                out.println("<h3 style = 'color:red'> User not Registered due to error. try again!!! </h3>");
                req.setAttribute("name",name);
                req.getRequestDispatcher("WEB-INF/output.jsp").forward(req, resp);
            }
        }catch (Exception e){
            throw new ConnectIOException("Failed to connect with DB");
        }
    }

}
