package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.data.IdSequencers;
import se.ecutb.dto.TodoDto;
import se.ecutb.model.Todo;

@Component
public class TodoDtoConversionServiceImpl implements TodoDtoConversionService {

    @Autowired private IdSequencers idSequencers;

    @Override
    public TodoDto convertToDto(Todo todo) {
        if (todo.getAssignee() == null ){
            return new TodoDto(todo.getTodoId(), todo.getTaskDescription(),
                    todo.getDeadLine(), null, todo.isDone());
        }
        return new TodoDto(todo.getTodoId(), todo.getTaskDescription(),
                todo.getDeadLine(), todo.getAssignee().getPersonId(), todo.isDone());
    }
}
