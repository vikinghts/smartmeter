package com.soldev;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by kjansen on 05/08/14.
 */
public class GraphGeneratorTest {
    private static final Logger LOG = LoggerFactory.getLogger(FileParserTest.class);
    private GraphGenerator gg;
    private SortedMap maps;
    private DefaultCategoryDataset dataSet;
    private JFreeChart chart;

    @Before
    public void setup(){
        gg = new GraphGenerator();
        maps = new TreeMap();
        dataSet = null;
        chart = null;
    }

    @Test
    public void given_create_a_new_class_i_should_be_able_to_use_it() {
        assertNotNull(gg);
    }

    @Test
    public void given_i_fill_a_map_with_3_elements_the_dataset_should_contain_3_elements() {
        //When
        maps.put("201408031305","100");
        maps.put("201408031405","200");
        maps.put("201408031505","300");
        //then
        dataSet = gg.fillDataSet(maps);
        LOG.info("dataSet getColumnCount is = " + dataSet.getColumnCount());
        assertTrue(dataSet.getColumnCount() == 3);
    }

    @Ignore
    public void given_i_fill_a_dataset_with_3_elements_the_graph_should_able_to_be_created() {
        //When
        dataSet.setValue(100, "Marks", "201408031305");
        dataSet.setValue(200, "Marks", "201408031405");
        dataSet.setValue(300, "Marks", "201408031505");
        //then
        chart = gg.setChartProperties(dataSet);
        assertNotNull(chart);
    }


    @Test
    public void given_create_a_new_class_i_should_able_to_be_create_a_jpg_graph() {
        gg.printGraph(maps, "/tmp/test.jpg", "toJPG");
    }

    @Test
    public void given_create_a_new_class_i_should_able_to_be_create_a_png_graph() {
        gg.printGraph(maps, "/tmp/test.png", "toPNG");
    }


    @Test
    public void given_create_a_new_class_i_should_able_to_be_create_a_byte_graph() {
        gg.printGraph(maps, "dummy", "toScreen");
    }



    /*
    DefaultCategoryDataset defaultCategoryDatasetMock = Mockito.mock(DefaultCategoryDataset.class);
    try {
        Mockito.when(defaultCategoryDatasetMock.readLine()).thenReturn("201408031305,100", "201408031310,200", "201408031315,300",null);
    }   catch (IOException e) {
        LOG.error("IO Error when reading file!", e);
    }
    */

}
