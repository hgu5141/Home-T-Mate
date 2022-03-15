package com.example.demo.model;


import com.example.demo.dto.RoomRequestDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


//implements Serializable 추가하였음
@Setter
@Getter
@Entity
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false)
    private  String name;

    @Column
    private Long userCount;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column
    private String content;

    @Column
    private String password;

    @Column
    private Boolean passCheck;

    @Column
    private String roomImg;

    @Column
    private Boolean workOut;







//    public Room(String name, long userCount, User user, String content, String password, Boolean workOut) {
//        this.name = name;
//        this.userCount = userCount;
//        this.user = user;
//        this.content = content;
//        this.password = password;
//        this.workOut = workOut;
//    }
//
//
//    public Room(RoomDto roomDto) {
//        this.name = roomDto.getName();
//        this.userCount = roomDto.getUserCount();
//        this.content = roomDto.getContent();
//        this.password = roomDto.getPassword();
//        this.workOut = roomDto.getWorkOut();
//    }

    public Room() {

    }

    public static Room create(RoomRequestDto roomDto, User user) {
        Room room = new Room();
        room.roomId = UUID.randomUUID().toString();
        room.name = roomDto.getName();
        room.content = roomDto.getContent();
        room.password = roomDto.getPassword();
        room.roomImg = roomDto.getRoomImg();
        room.passCheck = roomDto.getPassCheck();
        room.workOut = roomDto.getWorkOut();
        room.user = user;
        return room;
    }
}
