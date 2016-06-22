package de.roamingthings.junit.rules.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

/**
 * @author alxs
 * @version 2016/06/22
 */
public class JacksonObjectMapperProvider implements TestRule {

    private final ObjectMapper objectMapper;

    public JacksonObjectMapperProvider() {
        objectMapper = new ObjectMapper()
                .findAndRegisterModules()
                .disable(WRITE_DATES_AS_TIMESTAMPS)
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(INDENT_OUTPUT);
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                base.evaluate();
            }

        };
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
