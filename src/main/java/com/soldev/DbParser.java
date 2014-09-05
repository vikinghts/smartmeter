package com.soldev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by kjansen on 24/08/14.
 */
public class DbParser {
    private static final Logger LOG = LoggerFactory.getLogger(DbParser.class);


    public void driverLoaded() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            LOG.error("failed!", e);
        }
    }

    public String getDbData(String statement) {
        DriverManager driverManager = null;
        return doGetDbData(driverManager,statement);
    }

    public SortedMap getDbDataMap(String statement) {
        DriverManager driverManager = null;
        return doGetDbDataMap(driverManager, statement);
    }

    public Statement buildUpConnection(DriverManager driverManager) {
        Connection connection;
        Statement st = null;

        try {
            connection = driverManager.getConnection(
                    "jdbc:postgresql://192.168.8.1:5432/smartmeterdb", "smartmeteruser",
                    "pp4pass");
            st = connection.createStatement();
        } catch (SQLException e) {
            LOG.error("Connection Failed! Check output console", e);
        }
        return st;
    }

    public String doGetDbData(DriverManager driverManager, String statement) {

        driverLoaded();
        Statement st;
        st = buildUpConnection(driverManager);
        ResultSet rs = null;

        String retVal = "";
        try {
            rs = st.executeQuery(statement);

            Integer nrCols = rs.getMetaData().getColumnCount();

            while ( rs.next() ) {
                for(int i=1; i <= nrCols; i++) {
                    retVal = retVal + "value : " + rs.getString(i) + " - ";
                }
                retVal = retVal + "<BR>";
            }
            rs.close();
        } catch (SQLException e) {
            LOG.error("Connection Failed! Check output console", e);
        }


        LOG.info("result set from query :" + statement + "\n result: " + retVal + "end of result");
        return retVal;
    }

    public SortedMap doGetDbDataMap(DriverManager driverManager, String statement) {

        driverLoaded();
        Statement st;
        st = buildUpConnection(driverManager);
        ResultSet rs;
        SortedMap maps = new TreeMap();

        try {
            rs = st.executeQuery(statement);

            Integer nrCols = rs.getMetaData().getColumnCount();
            LOG.info("number of calls:"+ nrCols.toString());
            while ( rs.next() ) {
                    maps.put(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            LOG.error("Connection Failed! Check output console", e);
        }

        return maps;
    }
}
