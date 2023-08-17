/**예외 메세지 던져주는 클래스*/

package com.test.models.board;

public class BoardValidationException extends RuntimeException{
    public BoardValidationException(String message){
        super(message);
    }
}
