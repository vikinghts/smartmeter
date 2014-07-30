package com.soldev;

import org.junit.Assert;
import org.junit.Test;




/**
 * Created by kjansen on 18/04/14.
 */
public class SmartMeterWebProviderTest {
    @Test
    public void given_create_a_new_class_i_should_be_able_to_use_it() {
        SmartMeterWebProvider webProvider = new SmartMeterWebProvider();

        Assert.assertNotNull(webProvider);
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
        */

    }
}

/*

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

