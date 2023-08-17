/**게시물 저장 서비스를 정의하는 클래스*/
package com.test.models.board;

import com.test.controller.board.BoardDataForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 생성자를 자동으로 생성하는 역할
                         // 밑의 final 로 선언된 validator 를 매개변수로 받는 생성자가 자동으로 생성
public class SaveServiceTest {
    // final 로 선언하여 한번 초기화된 이후 다른 값으로 변경 불가
    // RequiredArgsConstructor 애너테이션으로 인해 생성자 자동 생성, 필드 자동 초기화
    private final BoardSaveValidator validator;

    public void save(BoardDataForm data) {
        validator.check(data);
    }
}
