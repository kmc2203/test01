/**데이터 폼을 정의하는 클래스*/

package com.test.controller.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDataForm {
    private int id;
    private String title;
    private String content;
    private LocalDateTime regDt;
    private LocalDateTime modDt;
}
