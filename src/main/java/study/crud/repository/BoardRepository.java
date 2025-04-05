package study.crud.repository;

import study.crud.dto.UpdateBoardDto;
import study.crud.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    void create(String title, String content,String author);
    Board update(int id, UpdateBoardDto boardParam);
    void delete(int id);
    Optional<Board> findById(int id);
    Optional<Board> findByTitle(String title);
    List<Board> findAll();
}
