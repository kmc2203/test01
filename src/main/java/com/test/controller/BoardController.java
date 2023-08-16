package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 스프링이 해당 클래스가 컨트롤러임을 인식하게 하기 위한 애너테이션
public class BoardController {

    @GetMapping("/board/write") // localhost:3000/board/write
    public String boardWriteForm() {

        return "boardwrite";
    }
}
