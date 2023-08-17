// 웹에서 넘어오는 데이터 받아서 관리하는 클래스
package com.test.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Board {
    private Integer id;

    private String title;

    private String content;
}
