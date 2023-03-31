package com.multicampus.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDto<E> {
    private int page;
    private int size;
    private int total;
    //시작페이지 번호
    private int start;
    //끝페이지 번호
    private int end;
    //이전페이지의 존재 여부
    private boolean prev;
    //다음페이지의 존재 여부
    private boolean next;
    private List<E> dtoList;
    @Builder(builderMethodName = "withAll")
    public PageResponseDto(PageRequestDto pageRequestDto, List<E> dtoList, int total){
        this.page = pageRequestDto.getPage();
        this.size = pageRequestDto.getSize();
        this.total = total;
        this.dtoList = dtoList;
        this.end = (int)(Math.ceil(this.page / 10.0 )) * 10;
        this.start = this.end - 9;
        int last = (int)(Math.ceil((total/(double)size)));
        this.end = Math.min(end, last);
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;
    }
}
