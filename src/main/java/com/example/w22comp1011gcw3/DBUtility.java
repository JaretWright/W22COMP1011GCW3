package com.example.w22comp1011gcw3;

import java.sql.*;

public class DBUtility {
    private static String user = "student";
    private static String password = "student";
    private static String connectURL = "jdbc:mysql://localhost:3306/w22Java";

    /**
     * This method will send a Camera object into the DB and return the cameraID
     */
    public static int insertCameraIntoDB(Camera camera) throws SQLException {
       int cameraID = -1;
       ResultSet resultSet = null;

       String sql = "INSERT INTO cameras (make, model, resolution, slr, price) VALUES (?,?,?,?,?);";

       //This is called a "try with resources" block.  It will autoclose anything in the ()
       try(
               Connection conn = DriverManager.getConnection(connectURL,user,password);
               PreparedStatement ps = conn.prepareStatement(sql, new String[] {"cameraID"})
               )
       {
           //configure the prepared statement to prevent SQL injection attacks
           ps.setString(1, camera.getMake());
           ps.setString(2,camera.getModel());
           ps.setInt(3,camera.getResolution());
           ps.setBoolean(4,camera.isSlr());
           ps.setDouble(5,camera.getPrice());

           //run the command into the DB
           ps.executeUpdate();

           //get the cameraID
           resultSet = ps.getGeneratedKeys();
           while (resultSet.next())
               cameraID = resultSet.getInt(1);
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       finally {
           if (resultSet != null)
               resultSet.close();
       }

       return cameraID;
    }
}