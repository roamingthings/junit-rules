package de.roamingthings.junit.rules.jaxrs;

import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static de.roamingthings.junit.rules.jaxrs.HttpMatchers.successful;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class JAXRSClientProviderTest {

    @Rule
    public JAXRSClientProvider provider = JAXRSClientProvider.buildWithURI("https://www.java.com");

    @Test
    public void pingJava() {
        Client client = provider.client();
        assertNotNull(client);
        WebTarget target = provider.target();
        assertNotNull(target);
        assertNotNull(provider.addPath("en"));
        assertNotNull(provider.target("https://www.google.de"));

        Response response = target.request(MediaType.TEXT_HTML).get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void pingJavaAndVerifyWithMatcher() {
        Client client = provider.client();
        assertNotNull(client);
        WebTarget target = provider.target();
        assertNotNull(target);
        assertNotNull(provider.addPath("en"));
        assertNotNull(provider.target("https://www.google.de"));

        Response response = target.request(MediaType.TEXT_HTML).get();
        assertThat(response, is(successful()));
    }

    @Test
    public void buildWithNonExistingSystemProperty() {
        String defaultUri = "http://duke:42";
        JAXRSClientProvider p = JAXRSClientProvider.buildWithURI("--", defaultUri);
        assertNotNull(p);
        String actual = p.target().getUri().toString();
        assertThat(actual, is(defaultUri));
    }

    @Test
    public void buildWithExistingSystemProperty() {
        String expectedUri = "http://configured:21";
        final String key = "server-uri";
        System.setProperty(key, expectedUri);
        String defaultUri = "http://duke:42";
        JAXRSClientProvider p = JAXRSClientProvider.buildWithURI(key, defaultUri);
        assertNotNull(p);
        String actual = p.target().getUri().toString();
        assertThat(actual, is(expectedUri));
    }

}
