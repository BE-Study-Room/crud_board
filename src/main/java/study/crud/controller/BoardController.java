package study.crud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import study.crud.dto.UpdateBoardDto;
import study.crud.entity.Board;
import study.crud.service.BoardService;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public String findAll(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "boardList";
    }

    @GetMapping("/form")
    public String createForm() {
        return "boardForm";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Board board) {
         boardService.create(board);
        return "redirect:/board";
    }
    @GetMapping("/{boardId}/edit")
    public String update(@PathVariable int boardId, Model model) {
        Board board = boardService.findById(boardId);
        model.addAttribute("board", board);
        return "boardEditForm";
    }

    @PostMapping("/{boardId}/edit")
    public String update(@PathVariable int boardId, @ModelAttribute UpdateBoardDto boardParam) {
        boardService.update(boardId, boardParam);
        return "redirect:/board";
    }

    @GetMapping("/{boardId}")
    public String findById(@PathVariable int boardId,Model model) {
        Board result = boardService.findById(boardId);
        model.addAttribute("board", result);
        return "boardDetail";
    }

    @PostMapping("/{boardId}/delete")
    public String delete(@PathVariable int boardId) {
        boardService.delete(boardId);
        return "redirect:/board";
    }

}
