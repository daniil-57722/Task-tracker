package com.example.courseWork.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBoard;
    private String deskName;
    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name = "user_access",
            joinColumns = {@JoinColumn(name = "board_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> user = new HashSet<>();
    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "board")
    private List<Message> messages;

    public Board(String text, User user) {
        this.deskName = text;
        this.user.add(user);
    }
}
