package com.db;

import com.dto.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbOperation {

    public static boolean register(User user) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into user_details (user_name ,user_email , user_password , user_dob) values(?,?,?,?)");
        preparedStatement.setString(1,user.getUserName());
        preparedStatement.setString(2,user.getUserEmail());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.setDate(4, user.getDob());

        int count = preparedStatement.executeUpdate();

       return count>=1 ;
    }
}
