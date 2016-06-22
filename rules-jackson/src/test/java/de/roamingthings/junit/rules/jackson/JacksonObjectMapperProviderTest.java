package de.roamingthings.junit.rules.jackson;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author alxs
 * @version 2016/06/22
 */
public class JacksonObjectMapperProviderTest {
    @Rule
    public JacksonObjectMapperProvider provider = new JacksonObjectMapperProvider();

    @Test
    public void testProvider() {
        assertNotNull(provider.getObjectMapper());
    }

}