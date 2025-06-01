package study.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.crud.dto.BoardSaveForm;
import study.crud.dto.BoardUpdateForm;
import study.crud.entity.Board;
import study.crud.repository.BoardRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository repository;
    
    public Board create(BoardSaveForm form){
        Board board = Board.builder()
                .content(form.getContent())
                .title(form.getTitle())
                .writer(form.getWriter())
                .build();

        validateDuplicateTitle(board);
        repository.save(board);
        return board;
    }

    public Board findById(UUID id){
        Board findBoard = repository.findById(id).orElseThrow(()->new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        return findBoard;
    }

    public Board findByTitle(String title){
        return repository.findByTitle(title).orElseThrow(()->new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
    }

    public List<Board> findAll(){
        return repository.findAll();
    }

    public Board update(UUID id, BoardUpdateForm boardParam){
        Board board = repository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        board.updateBoard(boardParam);
        return board;
    }

    public void delete(UUID id){
        Board board = repository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        repository.deleteById(board.getId());
    }

    private void validateDuplicateTitle(Board board) {
        if(!repository.findByTitle(board.getTitle()).isEmpty()){
            throw new IllegalArgumentException("중복된 제목 이름 입니다.");
        }
    }

}
