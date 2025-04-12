package study.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.crud.dto.BoardSaveForm;
import study.crud.dto.BoardUpdateForm;
import study.crud.entity.Board;
import study.crud.repository.BoardRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository repository;
    
    public Board create(BoardSaveForm form){
        Board board = new Board(form.getTitle(),form.getContent(),form.getWriter());
        validateDuplicateTitle(board);
        repository.create(board.getTitle(), board.getContent(), board.getWriter());
        return board;
    }

    public Board findById(int id){
        Board findBoard = repository.findById(id).orElseThrow(()->new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        return findBoard;
    }
    public Board findByTitle(String title){
        return repository.findByTitle(title).orElseThrow(()->new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
    }

    public List<Board> findAll(){
        return repository.findAll();
    }

    public Board update(int id, BoardUpdateForm boardParam){
        repository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        return repository.update(id, boardParam);
    }

    public void delete(int id){
        Board board = repository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        repository.delete(board.getId());
    }

    private void validateDuplicateTitle(Board board) {
        if(!repository.findByTitle(board.getTitle()).isEmpty()){
            throw new IllegalArgumentException("중복된 제목 이름 입니다.");
        }
    }

}
