package com.test.tests;

import com.test.controller.board.BoardDataForm;
import com.test.model.Board;
import com.test.models.board.BoardValidationException;
import com.test.models.board.SaveServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Board save test")
public class BoardServiceServiceTest {

    @Autowired
    private SaveServiceTest saveServiceTest; // SaveServiceTest 빈 주입

    private BoardDataForm boardData;

    // 테스트 실행할 때마다 객체 초기화하기 위함
    private BoardDataForm getData() {
        BoardDataForm boardData= new BoardDataForm();
        boardData.setId(1);
        boardData.setContent("content");
        boardData.setTitle("title");

        return boardData;
    }

    @BeforeEach // 테스트 시작 전 실행
    void init() {
        boardData = getData();
    }

    @Test
    @DisplayName("Save success test")
    void saveSuccessTest() {
        // 오류가 없으면 true
        assertDoesNotThrow(() -> {
           saveServiceTest.save(boardData);
        });
    }


    /**필수 항목 테스트*/
    @Test
    @DisplayName("Required method check")
    void requiredFieldsTest() {
        assertAll(
                () -> {
                    boardData = getData();
                    boardData.setTitle(" ");
                    requiredFieldTestAll(boardData, "Title required");
                },
                () -> {
                    boardData = getData();
                    boardData.setContent(null);
                    requiredFieldTestAll(boardData, "Content required");
                }
        );
    }

    // requiredFieldTest 에서 여러 데이터를 검증하기 때문에, 일괄로 처리하기 위한 메서드
    private void requiredFieldTestAll(BoardDataForm data, String message) {
        /* public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable) {
                return AssertThrows.assertThrows(expectedType, executable);
            }*/

        // BoardValidationException 이 던져질 것으로 예상
        BoardValidationException thrown = assertThrows(BoardValidationException.class, () -> {
            saveServiceTest.save(data);

        });
        System.out.println("assertThrows : " + thrown.toString());
        System.out.println("Thrown Exception: " + thrown.getClass().getName());
        System.out.println("Exception Message: " + thrown.getMessage());
        assertTrue(thrown.getMessage().contains(message)); // 조건이 성공이면 true
    }

}
