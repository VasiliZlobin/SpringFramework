package ru.vasili_zlobin.springsecurity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rules")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
