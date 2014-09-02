package com.soldev;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertTrue;


/**
 * Created by kjansen on 18/04/14.
 */
public class SmartMeterWebProviderTest {
    private static final Logger LOG = LoggerFactory.getLogger(SmartMeterWebProvider.class);
    private SmartMeterWebProvider webProvider;

    @Before
    public void setup(){
        webProvider = new SmartMeterWebProvider();

    }

    @Test
    public void given_create_a_new_class_i_should_be_able_to_use_it() {
        Assert.assertNotNull(webProvider);
    }

    @Ignore
    public void given_create_a_new_class_and_generate_a_graph_i_get_a_graph() {
        webProvider.generateGraphFile(99, 3, 8,null);
    }






    @Test
    public void given_create_a_new_class_and_i_get_writeHead_i_get_writeHead() {
        assertTrue(webProvider.writeHead().length() > 0);
    }

    @Test
    public void given_create_a_new_class_and_i_get_writeTail_i_get_writeTail() {
        assertTrue(webProvider.writeTail().length() > 0);
    }

    @Ignore
    public void given_create_a_new_class_and_i_doGet_i_get_doGet() {
        HttpServletRequest reqMock = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse respMock = Mockito.mock(HttpServletResponse.class);
        try {
            webProvider.doGet(reqMock, respMock);
        } catch (IOException|ServletException e) {
            LOG.error("error when doing web request",e);
        }
    }

    //FIXME fuck mocks
    @Ignore
    public void given_create_a_new_class_and_display_an_Image() {
        HttpServletResponse respMock = Mockito.mock(HttpServletResponse.class);
        ServletOutputStream servMock = Mockito.mock(ServletOutputStream.class);
        try {
            Mockito.when(respMock.getOutputStream()).thenReturn(servMock);
            SmartMeterWebProvider webProvider = new SmartMeterWebProvider();
            webProvider.displayImage(respMock,"/tmp/99-3-8.jpg");
            LOG.debug("done test");
        } catch (IOException e) {
            LOG.error("error when doing servlet request", e);
        }
        LOG.debug("done test end");
    }

    //FIXME fuck mocks
    @Ignore
    public void given_create_a_new_class_and_display_an_Image_and_throw_an_error() {
        HttpServletResponse respMock = Mockito.mock(HttpServletResponse.class);
        try {
            Mockito.doThrow(new IOException()).when(respMock).getOutputStream();
            SmartMeterWebProvider webProvider = new SmartMeterWebProvider();
            webProvider.displayImage(respMock,"/tmp/99-3-8.jpg");
        } catch (IOException e) {
            LOG.error("error when doing servlet request", e);
        }
    }
}

/*

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);


        //when(response.getWriter()).thenReturn(writer);
        //response.getOutputStream().write();
        ServletOutputStream outStream = null;
        //Mockito.doReturn(outStream).when(response)
        //        .getOutputStream();
        try {
            when(response.getOutputStream()).thenReturn(outStream);
        }
        catch (IOException ie) {

        }
        try {
            new SmartMeterWebProvider().doGet(request, response);
        }
        catch (ServletException|IOException se) {
        }


// Stub for injecting a mock for the SecurityGroup
class HttpServletRequestStub extends HttpServletRequest {

    HttpServletRequestStub() {
        super("testKees");
    }
}
// Stub for injecting a mock for the SecurityGroup
class HttpServletResponseStub extends HttpServletResponse {

    HttpServletResponseStub() {
        super("testKees");
    }
}
*/

