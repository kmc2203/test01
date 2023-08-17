/**데이터 폼을 정의하는 클래스*/

package com.test.controller.board;

import lombok.Data;

@Data
public class BoardDataForm {
    private Integer id;
    private String title;
    private String content;
}
