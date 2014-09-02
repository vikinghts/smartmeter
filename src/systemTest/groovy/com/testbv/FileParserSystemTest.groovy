import com.soldev.FileParser
import org.junit.Before
import org.junit.Ignore
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue

public class FileParserSystemTest {
    private static final Logger LOG = LoggerFactory.getLogger(FileParserTest.class);
    FileParser fp;
    SortedMap maps;

    @Before
    public void setup(){
        fp = new FileParser();
        maps = new TreeMap();
    }

    @Ignore
    public static void given_create_a_file_parser_it_should_return_the_contents_of_the_parsed_file_and_the_contents_should_be_bigger_then_0() {
        FileParser fp = new FileParser();

        maps = fp.returnTMAP("/tmp/powerusage1.0.txt");
        assertNotNull(maps);

        Collection entrySet = maps.entrySet();

        // Obtain an Iterator for the entries Set
        Iterator it = entrySet.iterator();

        while (it.hasNext()) {
            Map.Entry me = (Map.Entry) it.next();
            assertTrue(me.getKey().toString().length() > 0);
            assertTrue(me.getValue().toString().length() > 0);
        }
        assertTrue(maps.size() > 0);
    }
}