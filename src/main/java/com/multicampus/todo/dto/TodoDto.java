package com.multicampus.todo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoDto {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private String writer;
    private boolean finished;
}