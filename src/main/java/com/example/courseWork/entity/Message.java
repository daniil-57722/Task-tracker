package com.example.courseWork.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_board")
    private Board board;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User author;

    public Message(User user, String text, Board board) {
        this.text = text;
        this.author = user;
        this.board = board;
    }

    public String getAuthorName(){
        return author != null? author.getUsername():"<none>";
    }
}
