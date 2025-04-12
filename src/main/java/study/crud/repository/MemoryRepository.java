package study.crud.repository;

import org.springframework.stereotype.Repository;
import study.crud.dto.BoardUpdateForm;
import study.crud.entity.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryRepository implements BoardRepository {
    Map<Integer,Board> data = new HashMap<>();

    @Override
    public void create(String title, String content,String author) {
        Board board = new Board(title, content, author);
        data.put(board.getId(), board);
    }

    @Override
    public Optional<Board> findById(int id) {
        if(data.containsKey(id)) {
            return Optional.of(data.get(id));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Board> findByTitle(String title) {
        List<Board> boards = findAll();
        for(Board board : boards) {
            if(board.getTitle().equals(title)) {
                return Optional.of(board);
            }
        }
        return Optional.empty();
    }


    @Override
    public List<Board> findAll() {
        if(data.isEmpty()) {
            System.out.println("data is empty");
            return new ArrayList<>(data.values());
        }
        return new ArrayList<>(data.values());
    }

    @Override
    public Board update(int id, BoardUpdateForm boardParam) {
        Board board = data.get(id);
        board.setTitle(boardParam.getTitle());
        board.setContent(boardParam.getContent());
        return board;
    }

    @Override
    public void delete(int id) {
        data.remove(id);
    }
}
