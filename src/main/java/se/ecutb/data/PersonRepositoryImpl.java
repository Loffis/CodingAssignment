package se.ecutb.data;

import org.springframework.stereotype.Component;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PersonRepositoryImpl implements PersonRepository {

    private List<Person> personList = new ArrayList<>();


    @Override
    public Optional<Person> findById(int personId) {
        return personList.stream()
                .filter(person -> person.getPersonId() == personId)
                .findFirst();
    }

    @Override
    public Person persist(Person person) throws IllegalArgumentException {
        for (Person p : personList) {
            if (p.getEmail().equalsIgnoreCase(person.getEmail())){
                throw new IllegalArgumentException("Error: Duplicate email detected");
            }
        }
        personList.add(person);
        return person;
}

    @Override
    public Optional<Person> findByEmail(String email) {
        return personList.stream()
                .filter(person -> person.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<Person> findByAddress(Address address) {
        List<Person> newList = new ArrayList<>();
        for (Person p : personList) {
            if (address == null) {
                newList.add(p);
                return newList;
            } else if (p.getAddress().equals(address)) {
                newList.add(p);
            }
        }
        return newList;
        /*
        List<Person> newList = new ArrayList<>();
        for (Person p : personList) {
            if (address == null) {
                newList.add(p);
            } else if (p.getAddress().equals(address)) {
                newList.add(p);
            }
        }
        return newList;
         */
    }

    @Override
    public List<Person> findByCity(String city) {
        return personList.stream()
                .filter(person -> person.getAddress() != null)
                .filter(person -> person.getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return personList.stream()
                .filter(person -> person.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByFullName(String fullName) {
        String[] stringArray = fullName.trim().split(" ");
        String firstName = stringArray[0];
        String lastName = stringArray[1];

        return personList.stream()
                .filter(person -> person.getFirstName().equalsIgnoreCase(firstName) &&
                        person.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findAll() {
        return personList;
    }

    @Override
    public boolean delete(int personId) throws IllegalArgumentException {
        Person personToRemove =  findById(personId).orElseThrow(IllegalArgumentException::new);
        return personList.remove(personToRemove);
    }

    @Override
    public void clear() {
        personList.clear();
    }
}
