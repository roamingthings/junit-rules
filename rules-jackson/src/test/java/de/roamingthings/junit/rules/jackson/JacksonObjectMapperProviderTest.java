package de.roamingthings.junit.rules.jackson;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class JacksonObjectMapperProviderTest {
    @Rule
    public JacksonObjectMapperProvider provider = new JacksonObjectMapperProvider();

    @Test
    public void testProvider() {
        assertNotNull(provider.getObjectMapper());
    }

}