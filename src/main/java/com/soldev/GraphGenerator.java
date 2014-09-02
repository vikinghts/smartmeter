package com.soldev;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;


/**
 * Created by kjansen on 27/07/14.
 */
public class GraphGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(SmartMeterWebProvider.class);

    public void printGraph(SortedMap maps, String graphFileName, String mode) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet = fillDataSet(maps);
        ChartUtilities chartUtil = null;

        JFreeChart chart = setChartProperties(dataSet);

        switch (mode) {
            case "toJPG":
                safeGraphToJpg(chartUtil, chart,graphFileName);
                break;
            case "toPNG":
                safeGraphToPng(chartUtil, chart, graphFileName);
                break;
            case "toScreen":
                safeGraphToByte(chart);
                break;
            default :
                break;
        }
    }

    public JFreeChart setChartProperties(DefaultCategoryDataset dataSet) {
        JFreeChart chart = ChartFactory.createBarChart
                ("Power Usage", "Time", "Watt", dataSet,
                        PlotOrientation.VERTICAL, false, true, false);
        chart.setBackgroundPaint(Color.green);
        chart.getTitle().setPaint(Color.blue);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.black);
        return chart;
    }

    public DefaultCategoryDataset fillDataSet(SortedMap maps) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

        Collection entrySet = maps.entrySet();

        for (Iterator it = entrySet.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            LOG.debug("PrintGraph Get map contains [time= " + entry.getKey() + " watt= " + entry.getValue() + " ]" );
            dataSet.setValue(Integer.valueOf(entry.getValue().toString()), "Marks", entry.getKey().toString());
        }
        return dataSet;
    }

    public void safeGraphToJpg(ChartUtilities chartUtil, JFreeChart chart,String graphFileName) {
        try {
            chartUtil.saveChartAsJPEG(new File(graphFileName), chart, 900, 450);
        } catch (IOException e) {
            LOG.error("Problem occurred creating chart jpg.", e);
        }
    }

    public void safeGraphToPng(ChartUtilities chartUtil, JFreeChart chart,String graphFileName) {
        try {
            chartUtil.saveChartAsPNG(new File(graphFileName), chart, 900, 450);
        } catch (IOException e) {
            LOG.error("Problem occurred creating chart as png.", e);
        }
    }

    public byte[] safeGraphToByte(JFreeChart chart) {
        BufferedImage objBufferedImage = chart.createBufferedImage(600, 800);
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            ImageIO.write(objBufferedImage, "png", bas);
        } catch (IOException e) {
            LOG.error("Problem occurred creating chart as byte stream.", e);
        }

        return bas.toByteArray();
    }

}