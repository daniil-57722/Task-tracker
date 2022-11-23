package com.example.courseWork.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTask;
    private String taskName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_card")
    private Card card;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User issuer;
    private Date closingDate;
    private Boolean status;

    public Task(String text, Card card, Boolean status) {
        this.taskName = text;
        this.card = card;
        this.status = status;
    }
}
