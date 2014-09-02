package com.soldev;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 * Created by kjansen on 27/07/14.
 */
@SuppressWarnings("SameParameterValue")
public class FileParser {

    private static final Logger LOG = LoggerFactory.getLogger(FileParser.class);

    public SortedMap returnTMAP(String csvFile) {
        SortedMap maps = new TreeMap();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            maps = readFile(br);
        } catch (IOException e) {
            LOG.error("IO Error when reading file!", e);
        }
        return maps;
    }

    public SortedMap readFile(BufferedReader br) {
        String line;
        String cvsSplitBy = ",";
        SortedMap maps = new TreeMap();
        try {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] measureDateTime = line.split(cvsSplitBy);
                LOG.debug("read from file [time= " + measureDateTime[0] + " , watt= " + measureDateTime[1] + " ]" );
                maps.put(measureDateTime[0], measureDateTime[1]);
            }
        } catch (IOException e) {
            LOG.error("IO Error when reading Line from file!", e);
        }

        LOG.info("Read in file done");
        return maps;
    }
}
