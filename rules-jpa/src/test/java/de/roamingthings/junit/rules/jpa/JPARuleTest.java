package de.roamingthings.junit.rules.jpa;

/*
 * #%L
 * rules-jpa
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

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class JPARuleTest {

    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.providerForPersistenceUnit("it-rules-jpa");

    @Test
    public void crud() {
        EntityManager em = emProvider.em();
        assertNotNull(em);
        EntityTransaction tx = emProvider.tx();
        assertNotNull(tx);

        tx.begin();
        em.merge(new JPARuleTestEntity("html5", "html5 for javaee developers"));
        tx.commit();
    }
}
