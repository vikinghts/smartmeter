package com.soldev;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by kjansen on 24/08/14.
 */
public class DbParserTest {
    private static final Logger LOG = LoggerFactory.getLogger(DbParserTest.class);
    private DbParser dbParser;
    private SortedMap maps;


    @Before
    public void setup(){
        dbParser = new DbParser();
        maps = new TreeMap();
    }

    @Ignore
    public void given_create_a_new_class_and_i_get_dodbData_i_get_dodbData() {
        DriverManager driverManagerMock = Mockito.mock(DriverManager.class);
        //assertTrue(dbParser.doGetDbData(driverManagerMock,"SELECT curpower from measurements").length() == 0);
    }

    @Ignore
    public void given_create_a_new_class_and_i_get_dbData_i_get_dbData() {
        dbParser.driverLoaded();
        //assertTrue(dbParser.getDbData("SELECT curpower from measurements").length() == 0);
    }

    @Ignore
    public void given_create_a_new_class_and_i_get_dodbData_and_throw_error_i_get_dodbData() {
        dbParser.driverLoaded();
        DriverManager driverManagerMock = Mockito.mock(DriverManager.class);
        try {
            Mockito.doThrow(new SQLException()).when(driverManagerMock).getConnection(";jdbc:postgresql://localhost:5432/testdb", "test",
                    "pp4pass");
        } catch (SQLException e) {
            LOG.error("Connection Failed! Check output console test ", e);
        }
        //assertTrue(dbParser.doGetDbData(driverManagerMock,"SELECT curpower from measurements").length() == 0);
    }


    @Test
    public void given_i_do_a_select_all_i_get_all() {
        //When
        dbParser.driverLoaded();
        dbParser.getDbData("SELECT curpower from measurements");

        //then

    }

}
