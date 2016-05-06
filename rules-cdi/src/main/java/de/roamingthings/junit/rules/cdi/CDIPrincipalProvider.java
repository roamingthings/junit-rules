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

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.inject.spi.CDIProvider;
import java.security.Principal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author alxs
 * @version 2016/03/24
 */
public class CDIPrincipalProvider implements TestRule {

    public static final String TEST_PRINCIPAL_NAME = "testPrincipalName";

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                try {
                    final CDIProvider cdiProviderMock = mock(CDIProvider.class);
                    final CDI cdiMock = mock(CDI.class);
                    final Principal testPrincipalMock = mock(Principal.class);
                    when(testPrincipalMock.getName()).thenReturn(TEST_PRINCIPAL_NAME);

                    final Instance principalInstanceMock = mock(Instance.class);
                    when(principalInstanceMock.isAmbiguous()).thenReturn(false);
                    when(principalInstanceMock.isUnsatisfied()).thenReturn(false);
                    when(principalInstanceMock.get()).thenReturn(testPrincipalMock);

                    when(cdiMock.select(Principal.class)).thenReturn(principalInstanceMock);

                    when(cdiProviderMock.getCDI()).thenReturn(cdiMock);
                    CDI.setCDIProvider(cdiProviderMock);
                } catch (IllegalStateException e) {
                    // Ignore this exception since it means that a provider has already been set
                }

                base.evaluate();
            }

        };
    }
}
