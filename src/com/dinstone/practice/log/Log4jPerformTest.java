
package com.dinstone.practice.log;

import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4jPerformTest {

    private final static int BUFFERSIZE = 128; // but check that this matches the setting in the Log4jAsyncTest.xml file

    private final static int CYCLES = BUFFERSIZE * 20 + 1; // default async buffer is 128 messages

    public void test() throws Exception {
        // Run non asynchronous 3 times
        // Note: Timing of first test always takes about 100ms longer, so order of tests matter.
        // Reason is unknown. May be OS I/O caching.
        doNoAsync("1");
        doNoAsync("2");
        doNoAsync("3");
        // run non asynchronous with bufferedIO
        doNoAsyncBuffered("1");
        doNoAsyncBuffered("2");
        doNoAsyncBuffered("3");
        // run non asynchronous with bufferedIO and immediateFlush=false
        doNoAsyncBufferedNoFlush("1");
        doNoAsyncBufferedNoFlush("2");
        doNoAsyncBufferedNoFlush("3");
        // run non asynchronous with immediateFlush=false
        doNoAsyncNoFlush("1");
        doNoAsyncNoFlush("2");
        doNoAsyncNoFlush("3");
        // run asynchronous 3 times
        doAsync("1");
        doAsync("2");
        doAsync("3");
        // run asynchronous 3 times with buffering and no immediateFlush
        doAsyncFast("1");
        doAsyncFast("2");
        doAsyncFast("3");

    }

    /**
     * Log4j AsyncAppender places messages in a buffer and when the buffer gets full will write out to disk.
     * AsyncAppender does not always increase performance. If the buffer fills up quickly then the overhead of managing
     * a buffer may take longer than just doing a direct FileAppender. But AsyncAppender does work well when I/O access
     * takes long or logging is done over the network.
     * <p>
     * setImmediateFlush defaults to true but this also hurts performance - but if the app crashes you'll be guaranteed
     * the latest logs, else the latest few log messages will likely be lost as they were held in memory before writing
     * out. Setting to false can increase logging performance by 20%.
     * <p>
     * setBufferIO to true only marginally increases performance under most conditions, but some have claimed that in
     * some environments it vastly increases performance.
     * <p>
     * If you're looking for max performance, setImmediateFlush=false, setBufferIO=true and only if you are doing
     * network logging use an AsyncAppender else don't bother as it doesn't help when writing logs on the same machine.
     * <p>
     * Lastly, setImmediateFlush=true (default) and setBufferIO=true doesn't make sense - you can't buffer anything if
     * you immediately send it off, so <code>
     * if(bufferedIO) { 
     *    setImmediateFlush(false); 
     * }
     * </code>
     * 
     * @author FredPuls 7/25/2006
     * @param id
     * @throws Exception
     */
    private void doNoAsync(String id) throws Exception {
        Logger loggerx = Logger.getLogger("test.func"); // get a regular non-asysc logger
        FileAppender ap = (FileAppender) loggerx.getAppender("testAppender");
        ap.setBufferedIO(false);
        ap.setImmediateFlush(true);
        Logger logger = Logger.getLogger("test.func"); // get a regular non-asysc logger
        logIt(logger, id, "noasync : nobuffer : flush");
    }

    private void doNoAsyncBuffered(String id) throws Exception {
        Logger logger = Logger.getLogger("test.func"); // get a regular non-asysc logger
        FileAppender ap = (FileAppender) logger.getAppender("testAppender");
        ap.setBufferedIO(true);
        ap.setImmediateFlush(true);
        // this configuration does not make sense in real life - see Javadco above
        // but it's interesting to see the results - the buffer won't do
        // anything if we flush immediately.
        logIt(logger, id, "noasync :   buffer :   flush");
    }

    private void doNoAsyncBufferedNoFlush(String id) throws Exception {
        Logger logger = Logger.getLogger("test.func"); // get a regular non-asysc logger
        FileAppender ap = (FileAppender) logger.getAppender("testAppender");
        ap.setBufferedIO(true);
        ap.setImmediateFlush(false);
        logIt(logger, id, "noasync :   buffer : noflush");
    }

    private void doNoAsyncNoFlush(String id) throws Exception {
        Logger logger = Logger.getLogger("test.func"); // get a regular non-asysc logger
        FileAppender ap = (FileAppender) logger.getAppender("testAppender");
        ap.setBufferedIO(false);
        ap.setImmediateFlush(false);
        logIt(logger, id, "noasync : nobuffer : noflush");
    }

    private void doAsync(String id) throws Exception {
        Logger loggerx = Logger.getLogger("test.func"); // get a regular non-asysc logger
        FileAppender ap = (FileAppender) loggerx.getAppender("testAppender");
        ap.setBufferedIO(false);
        ap.setImmediateFlush(true);
        Logger logger = Logger.getLogger("asynclogger"); // get an asysc logger
        logIt(logger, id, "async   : nobuffer :   flush");
    }

    private void doAsyncFast(String id) throws Exception {
        Logger loggerx = Logger.getLogger("test.func"); // get a regular non-asysc logger
        FileAppender ap = (FileAppender) loggerx.getAppender("testAppender");
        ap.setBufferedIO(true);
        ap.setImmediateFlush(false);
        Logger logger = Logger.getLogger("asynclogger"); // get an asysc logger
        logIt(logger, id, "async   :   buffer : noflush");
    }

    private void logIt(Logger logger, String id, String name) {
        // first log some number of lines one larger than the buffer to have the file created.
        for (int i = 0; i < BUFFERSIZE + 1; i++) {
            logger.info("test" + i);
        }
        // now log and time the logging
        long start = System.currentTimeMillis();
        for (int i = 0; i < CYCLES; i++) {
            logger.info("test" + i);
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println(name + id + ": " + elapsed);
    }

    protected void clean() throws Exception {
        LogManager.shutdown();
    }

    protected void config() throws Exception {
        // initialize log4j with an xml file
        String configFile = "resource/log4jPerformTest.xml";
        DOMConfigurator.configure(configFile);
    }

    public static void main(String[] args) throws Exception {
        Log4jPerformTest log4jPerformTest = new Log4jPerformTest();
        log4jPerformTest.config();
        log4jPerformTest.test();
        log4jPerformTest.clean();
    }
}
