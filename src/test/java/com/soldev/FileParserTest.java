package com.soldev;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kjansen on 30/07/14.
 */
public class FileParserTest {
    @Test
    public void given_create_a_new_class_i_should_be_able_to_use_it() {
        FileParser fp = new FileParser();
        Map<String, String> maps = new HashMap<String, String>();

        maps = fp.returnValue();
    }
}
