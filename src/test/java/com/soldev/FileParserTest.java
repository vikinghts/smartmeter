package com.soldev;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by kjansen on 30/07/14.
 */
public class FileParserTest {
    private static final Logger LOG = LoggerFactory.getLogger(FileParserTest.class);
    private FileParser fp;
    private SortedMap maps;

    @Before
    public void setup(){
        fp = new FileParser();
        maps = new TreeMap();
    }

    @Test
    public void given_create_a_new_instance_it_should_be_not_null() {
        assertNotNull(fp);
    }

    @Test
    public void given_create_read_a_file_with_tree_lines_i_should_get_3_map_elements() {
        //When

        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        try {
            Mockito.when(bufferedReader.readLine()).thenReturn("201408031305,100", "201408031310,200", "201408031315,300",null);
        }   catch (IOException e) {
            LOG.error("IO Error when reading file!", e);
        }

        //then
        maps = fp.readFile(bufferedReader);
        LOG.debug("mapsize is = " + maps.size());
        assertTrue(maps.size() == 3);
    }

    @Test
    public void given_create_read_a_file_with_0_lines_i_should_get_0_map_elements() {
        //When
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        try {
            Mockito.when(bufferedReader.readLine()).thenReturn(null);
        }   catch (IOException e) {
            LOG.error("IO Error when reading file!", e);
        }

        //then
        maps = fp.readFile(bufferedReader);
        LOG.debug("mapsize is = " + maps.size());
        assertTrue(maps.size() == 0);
    }

    @Test
    public void given_create_read_a_file_and_i_thow_an_io_excpetion_it_should_catch_it() {
        //When
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        try {
            Mockito.doThrow(new IOException()).when(bufferedReader).readLine();
        }   catch (IOException e) {
           LOG.error("IO Error when reading file in test", e);
        }

        //then
        maps = fp.readFile(bufferedReader);
        LOG.debug("mapsize is = " + maps.size());
        assertTrue(maps.size() == 0);
    }

}
