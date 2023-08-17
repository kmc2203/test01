/**필수 항목 검증 validator*/

package com.test.models.board;

import com.test.controller.board.BoardDataForm;
import com.test.validators.RequiredValidator;
import com.test.validators.Validator;
import org.springframework.stereotype.Component;

@Component
public class BoardSaveValidator implements Validator<BoardDataForm>, RequiredValidator {

    @Override
    public void check(BoardDataForm data) {
        // RequiredValidator.checkRequired 메서드
        // default void checkRequired(String str, RuntimeException e)
        // str 에 data.get 전달, RuntimeException e 에 new BoardValidationException("message") 전달
        // RequiredValidator 에서 isBlank() 체크 후 예외 발생 시 throw e

            checkRequired(data.getTitle(), new BoardValidationException("Title required"));
            checkRequired(data.getContent(), new BoardValidationException("Content required"));

    }
}
