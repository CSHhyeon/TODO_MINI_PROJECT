package com.multicampus.todo.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDto {
    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;
    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    // 페이징에 finished를 위해 추가한 부분
    @Builder.Default
    private boolean finished = false;

    @Builder.Default
    private String[] types = new String[]{"t"};

    private String link;

    @Builder.Default
    private String keyword = "";

    @Builder.Default
    private String from = "";

    @Builder.Default
    private String to = "";

    public int getSkip() {
        return (page - 1) * 10;
    }


    // 페이징에 finished를 위해 추가한 부분

    public boolean checkType(String type) {
        for (String t : types) {
            if (t.equals(type)) {
                return true;
            }
        }
        return false;
    }

    public String getLink() {
        if(link == null){
            StringBuilder builder = new StringBuilder();
            builder.append("size=" + this.size);
            for (String type : this.types) {
                builder.append("&types=" + type);
            }
            builder.append("&keyword=" + this.keyword);
            builder.append("&from=" + this.from);
            builder.append("&to=" + this.to);
            builder.append("&page=" + this.page);
            link = builder.toString();
        }
        return link;
    }


}
