
import org.testng.annotations.Test

/**
 * Created by kjansen on 28/04/14.
 */
class ConnectToServerTest {
    def retryCount = 0
    def found = false

    @Test
    void if_i_send_a_request_to_the_server_8080_it_should_answer() {
        while (!found && retryCount < 12) {
            try {
                def testSocket = new Socket("ec2-54-186-192-226.us-west-2.compute.amazonaws.com", 22, true)
                testSocket.withStreams { input, output ->
                    def buffer = input.newReader().readLine()
                    println "response = $buffer"
                    if (buffer.contains('SSH')) {
                        found = true
                    }
                    false
                }
            } catch (ConnectException ce) {
                sleep(5000);
                retryCount = retryCount + 1
                println "retryCount = ${retryCount} error : " + ce
            }
        }
    }
}
