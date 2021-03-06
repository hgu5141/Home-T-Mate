package com.example.demo.repository;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByroomId(String roomId);

    Room findByName(String name);

    Page<Room> findAllByOrderByCreatedAtDesc(Pageable pageable);

    List<Room> findByUser(User user);

    List<Room> findByNameContainingIgnoreCase(String keyword);
}
