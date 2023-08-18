/**서비스는 컨트롤러에서 이용*/
package com.test.service;

import com.test.entity.Board;
import com.test.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public void boardWrite(Board board) {
        boardRepository.save(board);
    }
}
