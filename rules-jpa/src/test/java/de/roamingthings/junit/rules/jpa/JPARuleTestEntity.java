package de.roamingthings.junit.rules.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JPARuleTestEntity {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String description;

    public JPARuleTestEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public JPARuleTestEntity() {
    }

}
