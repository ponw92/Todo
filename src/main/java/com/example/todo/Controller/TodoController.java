package com.example.todo.Controller;

import com.example.todo.DTO.TodoDTO;
import com.example.todo.Service.TodoService;
import com.example.todo.Util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.todo.Util.PaginationUtil.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
public class TodoController {
    private final TodoService todoService;

    @GetMapping({"/", "/todo/list"})
    public String list(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<TodoDTO> todoDTOS = todoService.list(pageable);

        Map<String, Integer> pageInfo = Pagination(todoDTOS);

        model.addAllAttributes(pageInfo);
        model.addAttribute("list", todoDTOS);

        return "todo/list";
    }

    @GetMapping("/todo/register")
    public String register(Model model){
        TodoDTO todoDTO = new TodoDTO();
        model.addAttribute("data", todoDTO);

        return "todo/register";
    }

    @PostMapping("/todo/register")
    public String registerProc(@Validated TodoDTO todoDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "todo/register";
        }
        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping("/todo/modify/{id}")
    public String modify(@PathVariable int id, Model model){
        TodoDTO todoDTO = todoService.read(id);
        model.addAttribute("data", todoDTO);

        return "todo/modify";
    }

    @PostMapping("/todo/modify/{id}")
    public String modifyProc(@Validated TodoDTO todoDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "todo/modify";
        }
        todoService.modify(todoDTO);

        return "redirect:/todo/list";

    }

    @GetMapping("/todo/remove/{id}")
    public String removeProc(@PathVariable int id){
        todoService.remove(id);
        return "redirect:/todo/list";
    }





}
