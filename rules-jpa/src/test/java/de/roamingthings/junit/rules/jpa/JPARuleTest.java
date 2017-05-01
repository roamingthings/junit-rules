package de.roamingthings.junit.rules.jpa;

import org.junit.Rule;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.assertNotNull;

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
