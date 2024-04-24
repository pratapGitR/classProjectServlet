package com.db;

import com.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginDao {

    public boolean isValidLogin(User userDetail) throws SQLException, ClassNotFoundException {
        boolean isValid = false;
        Connection connection= null;

          connection = DbConnection.getDbConnection();


                PreparedStatement preparedStatement = connection.prepareStatement("select * from user_details where user_email =? and user_password=? ");
                preparedStatement.setString(1, userDetail.getUserEmail());
                preparedStatement.setString(2, userDetail.getPassword());
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    userDetail.setUserName(resultSet.getString("user_name"));
                    userDetail.setDob(resultSet.getDate("user_dob"));
                    isValid=true;
                }



        return isValid;
    }
}
