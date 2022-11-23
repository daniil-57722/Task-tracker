package com.example.courseWork.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCard;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_board")
    private Board board;
    private String cardName;
    private String status;
    private Date creationDate;
    private Date closingDate;
    private String filename;
    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "card")
    private List<Task> tasks;

    public Card(String cardName, Board board) {
        this.cardName = cardName;
        this.board = board;
    }
}
