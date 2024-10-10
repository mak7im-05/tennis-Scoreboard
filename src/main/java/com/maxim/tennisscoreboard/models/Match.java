package com.maxim.tennisscoreboard.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Player player1;

    @ManyToOne
    private Player player2;

    @ManyToOne
    private Player winner;

    @Transient
    private Score score = new Score();

    public void setPlayer1(Player player) {
        this.player1 = player;
        score.initPlayer();
    }

    public void setPlayer2(Player player) {
        this.player2 = player;
        score.initPlayer();
    }
}
