import com.db.DbConnection;
import com.db.UserLoginDao;
import com.dto.User;
import lombok.Data;
import org.hibernate.boot.Metadata;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

@WebServlet("/loginCheck")
public class LoginCheckDB extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // fetch userEmail and password form request obj
        String userEmail= req.getParameter("emailCheck");
        String userPassword = req.getParameter("passwordCheck");

        // add input data to user object

        User userDetail= new User();
        userDetail.setUserEmail(userEmail);
        userDetail.setPassword(userPassword);
        UserLoginDao userDoa= new UserLoginDao();

        try {
            PrintWriter out = resp.getWriter();
            resp.setContentType("text/html");
            out.println("<html><body>");
            // pass the user data into validate login to validate form db
            if(userDoa.isValidLogin(userDetail)){
               System.out.println("succes") ;
                out.println("<h1>WELCOME " + userDetail.getUserName().toUpperCase() + " TO THE WORLD OF PRATAP!!!");
                out.println("<table border = '1'>");
                out.println("<tr><th>User Name :</th><td>" + userDetail.getUserName() + "</td></tr>");
                out.println("<tr><th>User Email :</th><td>" + userDetail.getUserEmail() + "</td></tr>");
                out.println("<tr><th>user Date of Birth :</th><td>" + userDetail.getDob().toString() + "</td></tr>");
                out.println("<tr><th>password :</th><td>" + userPassword + "</td></tr>");
                out.println("</table>");
            } else {
                System.out.println("failed") ;
                out.println("<font color = red size = 15 > LOGIN FAILED. No ACCESS TO YOU YET!!!!");
                out.println("<a href = registraion.jsp> Go to first page.");
            }
            out.println("</body></html>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }






        /*Connection connection= null;
        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/pratap_site_database","root","WorkIsLife@004");
            connection = DbConnection.getDbConnection();

            try {
                PreparedStatement preparedStatement= connection.prepareStatement("select * from user_details where user_email =? and user_password=? ");
                preparedStatement.setString(1, userEmail);
                preparedStatement.setString(2,userPassword);
                ResultSet resultSet=preparedStatement.executeQuery();
                // PreparedStatement preparedStatement1 = connection.prepareStatement("Select * from user_details where user_email = userEmail");


                try {
                    if(resultSet.next()){
                        String userName= resultSet.getString("user_name");
                        Date dob = resultSet.getDate("user_dob");





                    }else {

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }catch (Exception e){
           e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            ;
        }*/
    }
}
