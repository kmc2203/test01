/**컨트롤러!*/
package com.test.controller;

import com.test.entity.Board;
import com.test.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 스프링이 해당 클래스가 컨트롤러임을 인식하게 하기 위한 애너테이션
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") // localhost:3000/board/write
    public String boardWriteForm() {

        return "boardwrite";
    }

    @PostMapping("/board/writesubmit")
    public String boardWriteSubmit(Board board) {
        boardService.boardWrite(board);
        return "";
    }
}
