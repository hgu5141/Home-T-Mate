package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodolistRequestDto {

    private String title;
    private Boolean completed;
    private String start;
    private String end;
    private String time;

    @Getter
    @Setter
    public static class Response
    {
        private Boolean result;
    }
}
