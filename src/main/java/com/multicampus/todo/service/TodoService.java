package com.multicampus.todo.service;

import com.multicampus.todo.dto.PageRequestDto;
import com.multicampus.todo.dao.ITodoDao;
import com.multicampus.todo.dto.PageResponseDto;
import com.multicampus.todo.dto.TodoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TodoService implements ITodoService {
    @Autowired
    ITodoDao dao;

    /*@Override
    public List<TodoDto> list() {
        return dao.listDao();
    }*/

    @Override
    public TodoDto view(Long tno) {
        return dao.viewDao(tno);
    }

    @Override
    public int insert(String title, LocalDate dueDate, String writer) {
        return dao.insertDao(title, dueDate, writer);
    }

    @Override
    public int update(Long tno, String title, LocalDate dueDate, Boolean finished) {
        return dao.updateDao(tno, title, dueDate, finished);
    }

    @Override
    public int delete(Long tno) {
        return dao.deleteDao(tno);
    }

    //페이징용 코드
    @Override
    public List<TodoDto> selectList(PageRequestDto pageRequestDto) {
        return dao.selectList(pageRequestDto);
    }

    @Override
    public int getCount(PageRequestDto pageRequestDto) {
        return dao.getCount(pageRequestDto);
    }

    public PageResponseDto<TodoDto> getList(PageRequestDto pageRequestDto) {
        List<TodoDto> DtoList = selectList(pageRequestDto);
        int total = getCount(pageRequestDto);
        return PageResponseDto.<TodoDto>withAll()
                .dtoList(DtoList)
                .total(total)
                .pageRequestDto(pageRequestDto)
                .build();
    }
}
