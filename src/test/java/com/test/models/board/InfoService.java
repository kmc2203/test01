package com.test.models.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InfoService {
    private final BoardDataDao boardDataDao;
    public BoardData get(int id) {
        return boardDataDao.get(id);
    }
}
