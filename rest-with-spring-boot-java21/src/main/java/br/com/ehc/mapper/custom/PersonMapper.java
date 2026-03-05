package br.com.ehc.mapper.custom;

import br.com.ehc.data.dto.v2.PersonDTOV2;
import br.com.ehc.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO(Person person) {
        PersonDTOV2 dto = new PersonDTOV2();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setBirthDay(new Date());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());
        return dto;
    }

    public Person convertDTOToEntity(PersonDTOV2 personDTOV2) {
        Person entity = new Person();
        entity.setId(personDTOV2.getId());
        entity.setFirstName(personDTOV2.getFirstName());
        entity.setLastName(personDTOV2.getLastName());
        // entity.setBirthDay(new Date());
        entity.setAddress(personDTOV2.getAddress());
        entity.setGender(personDTOV2.getGender());
        return entity;
    }
}
