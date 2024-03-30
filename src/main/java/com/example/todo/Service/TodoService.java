package com.example.todo.Service;

import com.example.todo.DTO.TodoDTO;
import com.example.todo.Entity.TodoEntity;
import com.example.todo.Repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public Page<TodoDTO> list(Pageable page){
        int currentPage = page.getPageNumber()-1;
        int pageLimit = 5;
        Pageable pageable = PageRequest.of(currentPage, pageLimit, Sort.by(Sort.Direction.DESC,"id"));
        Page<TodoEntity> todoEntities = todoRepository.findAll(pageable);
        Page<TodoDTO> todoDTOS = todoEntities.map(data -> modelMapper.map(data, TodoDTO.class));
        return todoDTOS;
    }

    public void register(TodoDTO todoDTO){
        TodoEntity todoEntity = modelMapper.map(todoDTO, TodoEntity.class);
        System.out.println(todoEntity);
        todoRepository.save(todoEntity);
    }

    public void remove(int id) {
        todoRepository.deleteById(id);
    }

    public void modify(TodoDTO todoDTO) {
        int id = todoDTO.getId();
        Optional<TodoEntity> result = todoRepository.findById(id);
        TodoEntity todoEntity = result.orElseThrow();

        TodoEntity update = modelMapper.map(todoDTO, TodoEntity.class);
        update.setId(todoEntity.getId());
        System.out.println(update);
        todoRepository.save(update);
    }

    public TodoDTO read(int id){
        Optional<TodoEntity> read = todoRepository.findById(id);
        TodoDTO todoDTO = modelMapper.map(read, TodoDTO.class);
        return todoDTO;
    }






}
