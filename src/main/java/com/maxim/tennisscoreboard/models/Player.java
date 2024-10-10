package com.maxim.tennisscoreboard.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", unique = true)
    private String name;
}
