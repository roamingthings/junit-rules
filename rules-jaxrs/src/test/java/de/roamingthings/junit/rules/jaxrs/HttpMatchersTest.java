package de.roamingthings.junit.rules.jaxrs;

import org.hamcrest.Matcher;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HttpMatchersTest {

    @Test
    public void successfulMatcher() {
        Matcher<Response> successful = HttpMatchers.successful();
        Response response = mock(Response.class);
        Response.StatusType statusType = mock(Response.StatusType.class);
        when(response.getStatusInfo()).thenReturn(statusType);
        when(statusType.getFamily()).thenReturn(Response.Status.Family.SUCCESSFUL);
        assertThat(response, is(successful));
    }

    @Test
    public void createdMatcher() {
        Matcher<Response> created = HttpMatchers.created();
        Response response = mock(Response.class);
        when(response.getHeaderString("Location")).thenReturn("http://something");
        when(response.getStatus()).thenReturn(201);
        assertThat(response, is(created));
    }

    @Test
    public void noContentMatcher() {
        Matcher<Response> noContent = HttpMatchers.noContent();
        Response response = mock(Response.class);
        when(response.getStatus()).thenReturn(204);
        assertThat(response, is(noContent));
    }

}
