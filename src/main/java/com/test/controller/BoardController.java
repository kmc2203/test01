/**컨트롤러!*/
package com.test.controller;

import com.test.entity.Board;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 스프링이 해당 클래스가 컨트롤러임을 인식하게 하기 위한 애너테이션
public class BoardController {

    @GetMapping("/board/write") // localhost:3000/board/write
    public String boardWriteForm() {

        return "boardwrite";
    }

    /*@PostMapping("/board/writesubmit")
    public String boardWriteSubmit(String title, String content) {
        System.out.println("title : " + title);
        System.out.println("content : " + content);
        return "";
    }*/

    @PostMapping("/board/writesubmit")
    public String boardWriteSubmit(Board board) {
        System.out.println("title : " + board.getTitle());
        System.out.println("content : " + board.getContent());
        return "";
    }
}
