package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.dto.PersonDto;
import se.ecutb.dto.PersonDtoWithTodo;
import se.ecutb.dto.TodoDto;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.util.List;

@Component
public class PersonDtoConversionServiceImpl implements PersonDtoConversionService {

    @Override
    public PersonDto convertToPersonDto(Person person) {
        return new PersonDto(person.getPersonId(), person.getFirstName(), person.getLastName());
    }

    @Override
    public PersonDtoWithTodo convertToPersonDtoWithTodo(Person person, List<Todo> assignedTodos) {
        return new PersonDtoWithTodo(person.getPersonId(), person.getFirstName(), person.getLastName(), null);
    }
}
