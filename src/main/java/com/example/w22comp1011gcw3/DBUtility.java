package com.example.w22comp1011gcw3;

import javafx.scene.chart.XYChart;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DBUtility {
    private static String user = DBCredentials.user;
    private static String password = DBCredentials.password;
    private static String connectURL = "jdbc:mysql://172.31.22.43:3306/Jaret001";

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

    /**
     * This method will return a list of all Camera's and their associated number of sales
     */
    public static ArrayList<Camera> getCamerasFromDB()
    {
        ArrayList<Camera> cameras = new ArrayList<>();

        //query the DB and create Camera objects / add them to the list
        String sql = "SELECT cameras.cameraId, make, model, resolution, price, slr, COUNT(salesId) AS 'units sold'\n" +
                    "FROM cameras INNER JOIN camerasales on cameras.cameraId = camerasales.cameraId\n" +
                    "GROUP BY cameras.cameraId;";

        try(
                Connection conn = DriverManager.getConnection(connectURL, user, password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                ) {

            while (resultSet.next())
            {
                int cameraID = resultSet.getInt("cameraId");
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                int resolution = resultSet.getInt("resolution");
                double price = resultSet.getDouble("price");
                boolean slr = resultSet.getBoolean("slr");
                int unitsSold = resultSet.getInt("units sold");

                Camera newCamera = new Camera(cameraID,make,model,resolution,slr,price,unitsSold);
                cameras.add(newCamera);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cameras;
    }


    public static XYChart.Series<String, Integer> getUnitsSold() {
        XYChart.Series<String, Integer> unitsSold = new XYChart.Series<>();
        unitsSold.setName("2022");
        ArrayList<Camera> cameras = getCamerasFromDB();

        //loop over each camera and add it to the XYChart.Series
        for (Camera camera : cameras)
            unitsSold.getData().add(new XYChart.Data<>(camera.getMakeAndModel(),camera.getUnitsSold()));

        return unitsSold;
    }
}
