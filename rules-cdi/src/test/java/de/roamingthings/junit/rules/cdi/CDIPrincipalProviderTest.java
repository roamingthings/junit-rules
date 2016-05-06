package de.roamingthings.junit.rules.cdi;

/*
 * #%L
 * rules-cdi
 * %%
 * Copyright (C) 2016 Alexander Sparkowsky
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;

import java.security.Principal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author alxs
 * @version 2016/03/24
 */
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