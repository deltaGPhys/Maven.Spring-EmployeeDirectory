package io.zipcoder.persistenceapp.entities;

import javax.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToOne
    private Employee manager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
