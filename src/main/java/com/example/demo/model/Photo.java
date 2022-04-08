package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.messaging.handler.annotation.SendTo;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Photo extends Timestamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String postImg;

    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    public Photo(String postImg, Post post) {
        this.postImg = postImg;
        this.post = post;
    }
}
