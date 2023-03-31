package com.multicampus.todo.service;

import com.multicampus.todo.dto.TodoDto;
import com.multicampus.todo.dto.PageRequestDto;
import com.multicampus.todo.dto.PageResponseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ITodoService {
    //public List<TodoDto> list();
    public TodoDto view(Long tno);
    public int insert(String title, LocalDate dueDate, String writer);
    public int update(Long tno, String title, LocalDate dueDate, Boolean finished);
    public int delete(Long tno);

    //페이징용 수정 코드
    public List<TodoDto> selectList(PageRequestDto pageRequestDto);
    public int getCount(PageRequestDto pageRequestDto);
    // 페이지 리스폰스에 저장해놓은 list를 불러오는 메서드? 추가
// 안쓰는게 좋을듯. 어떻게 쓰는지 모르겠음
    PageResponseDto<TodoDto> getList(PageRequestDto pageRequestDto);
}
