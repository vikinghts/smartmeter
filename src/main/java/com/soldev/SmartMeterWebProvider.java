package com.soldev;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


@SuppressWarnings("SameParameterValue")
public class SmartMeterWebProvider extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(SmartMeterWebProvider.class);

    private static final long serialVersionUID = 1L;

    private static final String TMP_FOLDER = "/tmp/";
    private static final String WATT = "watt";
    private static final Integer IGNORE = 99;
    private static final Integer CURMONTH = 9;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        LOG.debug("test = " + req.getRequestURI());

        LOG.debug("resp.getOutputStream().write(\"Hello World.\".getBytes()");

        if ( req.getRequestURI().contains("daypicfromfile")) {
            DateTime dt = new DateTime();
            int hour = IGNORE;
            int day = dt.getDayOfMonth();
            int month = CURMONTH;
            generateGraphFile(hour, day, month, resp);
        }

        if ( req.getRequestURI().contains("test")) {
            resp.getOutputStream().write(writeHead().getBytes());
            resp.getOutputStream().write("BURP".getBytes());
            resp.getOutputStream().write(writeTail().getBytes());
        }

        if ( req.getRequestURI().contains("sql")) {
            if ( req.getRequestURI().contains("curpower")) {
                generateGraphSql("SELECT datetime,curpower from measurements",resp);
            }
            if ( req.getRequestURI().contains("totalgas")) {
                generateGraphSql("SELECT datetime,totalgas from measurements",resp);
            }
            if ( req.getRequestURI().contains("totaldaypower")) {
                generateGraphSql("SELECT datetime,totaldaypower from measurements",resp);
            }
            if ( req.getRequestURI().contains("totalnightpower")) {
                generateGraphSql("SELECT datetime,totalnightpower from measurements",resp);
            }
        }

        if ( req.getRequestURI().contains("dbdata")) {
            DbParser dbParser = new DbParser();
            dbParser.driverLoaded();
            resp.getOutputStream().write(writeHead().getBytes());
            if ( req.getRequestURI().contains("curpower")) {
                resp.getOutputStream().write(dbParser.getDbData("SELECT datetime,curpower from measurements").getBytes());
            }
            if ( req.getRequestURI().contains("totalgas")) {
                resp.getOutputStream().write(dbParser.getDbData("SELECT datetime,totalgas from measurements").getBytes());
            }
            if ( req.getRequestURI().contains("totaldaypower")) {
                resp.getOutputStream().write(dbParser.getDbData("SELECT datetime,totaldaypower from measurements").getBytes());
            }
            if ( req.getRequestURI().contains("totalnightpower")) {
                resp.getOutputStream().write(dbParser.getDbData("SELECT datetime,totalnightpower from measurements").getBytes());
            }
            resp.getOutputStream().write(writeTail().getBytes());
        }

    }

    public String writeHead() {
        String webOut = "";
        webOut = webOut + "<html>";
        webOut = webOut + "<head><title>SmartMeter</title></head>";
        webOut = webOut + "<body>";
        return webOut;
    }

    public String writeTail() {
        String webOut = "";
        webOut = webOut + "</body></html>";
        return webOut;
    }

    public void generateGraphSql(String statement,HttpServletResponse response) {
        SortedMap maps = new TreeMap();
        DbParser dbParser = new DbParser();
        dbParser.driverLoaded();

        maps = dbParser.getDbDataMap(statement);

        String graphName = TMP_FOLDER + "sqlResult.jpg";
        GraphGenerator gg = new GraphGenerator();
        gg.printGraph(maps,graphName,"toJPG");
        displayImage(response, TMP_FOLDER + "sqlResult.jpg");
    }

    public void generateGraphFile(Integer hour, Integer day, Integer month,HttpServletResponse response) {
        FileParser fp = new FileParser();
        SortedMap maps = new TreeMap();
        SortedMap filteredMaps = new TreeMap();

        maps = fp.returnTMAP("/tmp/powerusage1.0.txt");

        Collection entrySet = maps.entrySet();
        Iterator it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            //debug purpose
            LOG.debug("Get map contains unfilterd [time= " + entry.getKey() + WATT + entry.getValue() + " ] " );
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMddHHmm");
            DateTime dt = formatter.parseDateTime(entry.getKey().toString());
            if ((day == dt.getDayOfMonth() || day == IGNORE) && (hour == dt.getHourOfDay() || hour == IGNORE) && (month == dt.getMonthOfYear() || hour == IGNORE)) {
                filteredMaps.put(entry.getKey(), entry.getValue());
            }
        }

        //Log the sorted file
        entrySet = filteredMaps.entrySet();
        it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            LOG.debug("Get map contains filterd [time= " + entry.getKey().toString() + WATT + entry.getValue() + " ]");
        }

        String graphName = TMP_FOLDER + hour.toString() + "-" + day.toString() + "-" + month.toString() + ".jpg";
        GraphGenerator gg = new GraphGenerator();
        gg.printGraph(filteredMaps,graphName,"toJPG");
        displayImage(response, graphName);

    }

    public void displayImage(HttpServletResponse response,String fileName) {
        response.setContentType("image/jpeg");
        try {
            ServletOutputStream out = null;
            out = response.getOutputStream();
            FileInputStream fin = new FileInputStream(fileName);

            BufferedInputStream bin = new BufferedInputStream(fin);
            BufferedOutputStream bout = new BufferedOutputStream(out);
            int ch = 0;

            while ((ch = bin.read()) != -1) {
                bout.write(ch);
            }

            bin.close();
            fin.close();
            bout.close();
            out.close();
        } catch (IOException ie) {
            LOG.error("Error displaying file:" ,ie);
        }

    }

}
