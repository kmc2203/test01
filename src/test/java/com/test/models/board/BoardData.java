package com.test.models.board;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardData {
    private int id;
    private String title;
    private String content;
    private LocalDateTime regDt;
    private LocalDateTime modDt;
}
