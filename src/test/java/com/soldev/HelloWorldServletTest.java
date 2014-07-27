package com.soldev;

import org.testng.annotations.Test;
import org.testng.Assert;



/**
 * Created by kjansen on 18/04/14.
 */
public class HelloWorldServletTest {
    @Test
    public void given_create_a_new_class_i_should_be_able_to_use_it() {
        //String kees = new String();
        HelloWorldServlet helloWorldServlet = new HelloWorldServlet();

        Assert.assertNotNull(helloWorldServlet);
        /*
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);


        //when(response.getWriter()).thenReturn(writer);
        //response.getOutputStream().write();
        ServletOutputStream outStream = null;
        //Mockito.doReturn(outStream).when(response)
        //        .getOutputStream();
        when (response.getOutputStream()).thenReturn(outStream);
        try {
            helloWorldServlet.doGet(request, response);
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
