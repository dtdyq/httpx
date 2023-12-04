package org.test.dyq;

import org.dyq.httpx.util.Config;
import org.junit.Test;

public class TestConfig {
    @Test
    public void testConfig() {
        System.out.println(Config.PORT.getInt());
        Config.PORT.set(12);
        Config.PORT.set(121);
        System.out.println(Config.PORT.getInt());
    }
}
