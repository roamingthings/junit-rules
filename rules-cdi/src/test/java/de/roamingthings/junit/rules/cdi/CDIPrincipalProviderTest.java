package de.roamingthings.junit.rules.cdi;

import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;

import java.security.Principal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class CDIPrincipalProviderTest {
    @Rule
    public CDIPrincipalProvider principalProvider = new CDIPrincipalProvider();

    @Test
    public void crud() {
        final CDI<Object> currentCDI = CDI.current();
        assertNotNull(currentCDI);

        final Instance<Principal> principalInstance = currentCDI.select(Principal.class);
        assertNotNull(principalInstance);
        assertFalse(principalInstance.isUnsatisfied());
        assertFalse(principalInstance.isAmbiguous());

        final Principal principal = principalInstance.get();
        assertNotNull(principal);
        assertThat(principal.getName(), is(CDIPrincipalProvider.TEST_PRINCIPAL_NAME));
    }

}