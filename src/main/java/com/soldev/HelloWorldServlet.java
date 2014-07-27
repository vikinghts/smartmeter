package com.soldev;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getOutputStream().write("Hello World.".getBytes());
        resp.getOutputStream().write(getDbData().getBytes());
    }

    private String getDbData() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;

        String retVal = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/testdb", "test",
                    "pp4pass");
            st = connection.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                retVal = rs.getString(1);
            }

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();

        }


        return retVal;
    }
}
