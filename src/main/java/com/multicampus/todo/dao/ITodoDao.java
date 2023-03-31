package com.multicampus.todo.dao;

import com.multicampus.todo.dto.PageRequestDto;
import com.multicampus.todo.dto.TodoDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface ITodoDao {
    //public List<TodoDto> listDao();
    public TodoDto viewDao(Long tno);
    public int insertDao(String title, LocalDate dueDate, String writer);
    public int updateDao(Long tno, String title, LocalDate dueDate, Boolean finished);
    public int deleteDao(Long tno);

    // 페이징용 getCount 메서드
    public List<TodoDto> selectList(PageRequestDto pageRequestDto);
    public int getCount(PageRequestDto pageRequestDto);
}
