package com.soldev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

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
        SortedMap maps = null;
        maps = doGetDbDataMap(driverManager,statement);
        Collection entrySet = maps.entrySet();
        String retVal = "";
        for (Iterator it = entrySet.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            LOG.debug("Db Data contains[  " + entry.getKey() + " = " + entry.getValue() + " ]" );
            retVal = retVal  + "Value = " + entry.getValue().toString() + " Key = " + entry.getKey().toString() + "<BR>";
        }
        return retVal;
    }

    public SortedMap getDbDataMap(String statement) {
        DriverManager driverManager = null;
        return doGetDbDataMap(driverManager, statement);
    }

    public Connection buildUpConnection(DriverManager driverManager) {
        try {
            return  driverManager.getConnection("jdbc:postgresql://192.168.8.1:5432/smartmeterdb", "smartmeteruser","pp4pass");
        } catch (SQLException e) {
            LOG.error("build up connection  Failed!", e);
        }
        return null;
    }

    public String doGetDatabaseVersion() {
        DriverManager driverManager = null;
        return getDatabaseVersion(driverManager);
    }

    public String getDatabaseVersion(DriverManager driverManager) {
        driverLoaded();
        Connection connection = null;
        Statement st= null;
        ResultSet rs = null;
        String retVal = "";

        try {
            connection = buildUpConnection(driverManager);
            st = connection.createStatement();
            rs = st.executeQuery("SELECT version();");

            Integer nrCols = rs.getMetaData().getColumnCount();
            LOG.info("number of calls:"+ nrCols.toString());
            while ( rs.next() ) {
                retVal = retVal + rs.getString(1);
            }
            rs.close();
            st.close();
            connection.close();
        } catch (SQLException e) {
            LOG.error("do Get Db Data Map Failed!", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException eIn) {
                LOG.error("do Get Db Data Map double failedFailed!", eIn);
            }
        }

        return retVal;
    }

    public SortedMap doGetDbDataMap(DriverManager driverManager, String statement) {
        driverLoaded();
        SortedMap maps = new TreeMap();
        Connection connection = null;
        Statement st= null;
        ResultSet rs = null;

        try {
            connection = buildUpConnection(driverManager);
            st = connection.createStatement();
            rs = st.executeQuery(statement);

            Integer nrCols = rs.getMetaData().getColumnCount();
            LOG.info("number of calls:"+ nrCols.toString());
            while ( rs.next() ) {
                    maps.put(rs.getString(1), rs.getString(2));
            }
            rs.close();
            st.close();
            connection.close();
        } catch (SQLException e) {
            LOG.error("do Get Db Data Map Failed!", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException eIn) {
                LOG.error("do Get Db Data Map double failedFailed!", eIn);
            }
        }

        return maps;
    }
}
