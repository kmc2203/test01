/**테이블에 데이터를 추가하거나 수정하는 클래스*/

package com.test.models.board;

import com.test.controller.board.BoardDataForm;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository // 데이터 액세스 가능한 레포지토리임을 명시
@RequiredArgsConstructor // 생성자 인젝션 용도
public class BoardDataDao {
    private final JdbcTemplate jdbcTemplate;

    public boolean save(BoardDataForm data){
        int id = data.getId();
        int affectedRows = 0;
        if(id > 0) { // id 값이 있으므로 수정
            String sql;
            sql = "UPDATE BOARD SET TITLE=?, " + // ? 는 나중에 바인딩되어 실제 값으로 대체
                    " CONTENT=?," +
                    " MODDT=SYSDATE " +
                    " WHERE ID=?"; // ID가 특정 값과 일치하는 행만 업데이트 하겠다는 의미

            affectedRows = jdbcTemplate.update(sql, data.getTitle(), data.getContent(), data.getId());

        } else { // id 값이 없으면 게시글이 없는 것이니 추가
            String sql;
            sql = "INSERT INTO BOARD (ID, TITLE, CONTENT) " +
                    "VALUES (BOARD_SEQ.nextval, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            affectedRows = jdbcTemplate.update((con) -> { // DB 업데이트
                PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"ID"}); // 자동 생성된 key 값을 ID 컬럼에서 가져오겠다
                pstmt.setString(1, data.getTitle()); // 첫 번째 ? 에 값 할당
                pstmt.setString(2, data.getContent()); // 두 번째 ? 에 값 할당

                return pstmt; // DB 에서 업데이트 작업 할 객체 반환
            }, keyHolder);

            id = keyHolder.getKey().intValue();
        }
        data.setId(id);
        return affectedRows > 0;
    }

    public BoardData get(int id){
        try{
            String sql = "SELECT * FROM BOARD WHERE ID=?";
            BoardData data = jdbcTemplate.queryForObject(sql, this::mapper, id);
            return data;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public BoardData mapper(ResultSet rs, Integer i) throws SQLException {
        Timestamp modDt = rs.getTimestamp("MODDT");
        return BoardData.builder()
                .id(rs.getInt("ID"))
                .title(rs.getString("TITLE"))
                .content(rs.getString("CONTENT"))
                .regDt(rs.getTimestamp("REGDT").toLocalDateTime())
                .modDt(modDt == null ? null : modDt.toLocalDateTime()) // null 이면 null 값 넣고, 아니면 뒤에 값 넣기
                .build();
    }
}
