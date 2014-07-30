package com.soldev

import org.testng.annotations.Test

//import org.junit.Test;
//import static org.junit.Assert.*;

class HelloWorldServletPerformanceTest {

    @Test
    void if_i_send_10_requests_to_the_server_it_should_answer_in_100_milliseconds() {

        def timeIt = { String message, Closure cl ->
            def startTime = System.currentTimeMillis()
            cl()
            def deltaTime = System.currentTimeMillis() - startTime
            assert deltaTime < 100
        }

        timeIt("Test 1") {
            for (int i = 0; i < 10; i++) {
                assert 'http://localhost:7070'.toURL().text == "Hello World.PostgreSQL 9.3.4 on x86_64-apple-darwin13.1.0, compiled by Apple LLVM version 5.1 (clang-503.0.38) (based on LLVM 3.4svn), 64-bit"
            }
        }
    }


}


