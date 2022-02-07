package com.example.w22comp1011gcw3;

import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Formatter;

public class FakeSalesData {

    /**
     * This method will create a file with fake phone sales data in it
     * an example of a valid insert statement is
     *       INSERT INTO camerasales (cameraId, dateSold) VALUES (1, '2020-07-15');
     *
     *       valid cameraId = 1-12
     *       valid dates - anyday up to today
     */
    public static void createSQL()
    {
        // this is a random number generator
        SecureRandom rng = new SecureRandom();

        //open the formatter in the try...with resources block so that it will auto-close
        try(
                Formatter formatter = new Formatter("cameraSales.sql");
                )
        {
            //create the fake date and write it to the file
            for (int i=1 ; i<= 5000; i++)
            {
                int cameraId = rng.nextInt(1,13);
                LocalDate dateSold = LocalDate.now().minusDays(rng.nextInt(1095));
                formatter.format("INSERT INTO camerasales (cameraId, dateSold) VALUES (%d,'%s');\n",cameraId,dateSold);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createSQL();
    }
}
