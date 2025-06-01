package study.crud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.crud.dto.BoardSaveForm;
import study.crud.dto.BoardUpdateForm;
import study.crud.entity.Board;
import study.crud.service.BoardService;

import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
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

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("board", new Board());
        return "boardForm";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("board") BoardSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            log.error("error발생: {}", bindingResult);
            return "boardForm";
        }
        Board board = boardService.create(form);
        redirectAttributes.addAttribute("boardId",board.getId());
        return "redirect:/board/{boardId}";
    }

    @GetMapping("/{boardId}/edit")
    public String update(@PathVariable UUID boardId, Model model) {
        Board board = boardService.findById(boardId);
        model.addAttribute("board", board);
        return "boardEditForm";
    }

    @PostMapping("/{boardId}/edit")
    public String update(@PathVariable UUID boardId, @Validated @ModelAttribute("board") BoardUpdateForm boardParam,BindingResult bindingResult
    , RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            log.error("error발생: {}", bindingResult);
            return "boardEditForm";
        }

        boardService.update(boardId, boardParam);
        redirectAttributes.addAttribute("boardId",boardId);
        return "redirect:/board";
    }

    @GetMapping("/{boardId}")
    public String findById(@PathVariable UUID boardId,Model model) {
        Board result = boardService.findById(boardId);
        model.addAttribute("board", result);
        return "boardDetail";
    }

    @PostMapping("/{boardId}/delete")
    public String delete(@PathVariable UUID boardId) {
        boardService.delete(boardId);
        return "redirect:/board";
    }

}
