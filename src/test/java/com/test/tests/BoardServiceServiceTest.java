/**테스트 진행 클래스
 * 1. 저장 테스트
 * 2. 필수 항목 입력 테스트
 * 3. 보내는 데이터, 저장된 데이터 일치 여부 테스트*/

package com.test.tests;

import com.test.controller.board.BoardDataForm;
import com.test.models.board.BoardData;
import com.test.models.board.BoardValidationException;
import com.test.models.board.InfoService;
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

    @Autowired
    private InfoService infoService; // 빈 주입

    private BoardDataForm boardData;

    // 테스트 실행할 때마다 객체 초기화하기 위함
    private BoardDataForm getData() {
        BoardDataForm boardData= new BoardDataForm();
        boardData.setId(100);
        boardData.setContent("content");
        boardData.setTitle("title");

        return boardData;
    }

    @BeforeEach // 테스트 시작 전 실행
    void init() {
        boardData = getData();
    }

    /**1. 저장 테스트*/
    @Test
    @DisplayName("Save success test")
    void saveSuccessTest() {
        // 오류가 없으면 true
        assertDoesNotThrow(() -> {
           saveServiceTest.save(boardData);
        });
    }


    /**2. 필수 입력 항목 테스트*/
    @Test
    @DisplayName("Required method check")
    void requiredFieldsTest() {
        assertAll(
                // Id 는 작성자가 입력하는 항목이 아니기 때문에 테스트 하지 않음
                () -> {
                    boardData = getData();
                    boardData.setTitle(" "); // 빈 값 보내보기
                    requiredFieldTestAll(boardData);
                },
                () -> {
                    boardData = getData();
                    boardData.setContent(null); // null 값 보내보기
                    requiredFieldTestAll(boardData);
                }
        );
    }

    // requiredFieldTest 에서 여러 데이터를 검증하기 때문에, 일괄로 처리하기 위한 메서드
    private void requiredFieldTestAll(BoardDataForm data) {
        /* public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable) {
                return AssertThrows.assertThrows(expectedType, executable);
            }*/

        // BoardValidationException 이 던져질 것으로 예상
        BoardValidationException thrown = assertThrows(BoardValidationException.class, () -> {
            saveServiceTest.save(data);

        });
        // 받아오는 값이 헷갈려서 콘솔에 출력해봤습니다
        System.out.println("assertThrows : " + thrown.toString());
        System.out.println("Thrown Exception: " + thrown.getClass().getName());
        System.out.println("Exception Message: " + thrown.getMessage());
        // 발생한 예외의 메세지가 message 문자열을 확인하는지 체크
        // assertTrue(thrown.getMessage().contains(message)); // 조건이 성공이면 true

        /**던져진 예외 메세지가 있다면, 보낸 값이 null 이거나 빈 값이라는 의미이기 때문에,
         * thrown 의 메세지가 null 이거나 빈 값인지만 확인하면 된다고 생각합니다.*/
        assertFalse(thrown.getMessage().isBlank()); // thrown 의 메세지가 null || empty 인 경우 true
    }

    /**3. 저장 전 데이터와 저장 후 데이터 일치 여부 확인*/
    @Test
    @DisplayName("saved content == sending content??")
    void saveResultTest() {
        saveServiceTest.save(boardData);
        BoardData result = infoService.get(boardData.getId());
        System.out.println(infoService.get(boardData.getId()));

        if(result != null){
            assertAll(
                    () -> assertEquals(boardData.getTitle(), result.getTitle()),
                    () -> assertEquals(boardData.getContent(), boardData.getContent())
            );
        } else {
            fail("result is null");
        }
    }

}
