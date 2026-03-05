package br.com.ehc.services;

import br.com.ehc.controllers.PersonController;
import br.com.ehc.data.dto.v1.PersonDTO;
import br.com.ehc.data.dto.v2.PersonDTOV2;
import br.com.ehc.exception.RequiredObjectIsNullException;
import br.com.ehc.exception.ResourceNotFoundException;
import static br.com.ehc.mapper.ObjectMapper.parseObject;
import static br.com.ehc.mapper.ObjectMapper.parseListObjects;

import br.com.ehc.mapper.custom.PersonMapper;
import br.com.ehc.model.Person;
import br.com.ehc.repository.PersonRepository;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public List<PersonDTO> findAll() {
        logger.info("Pesquisando todos as pessoas!");

        var persons = parseListObjects(repository.findAll(), PersonDTO.class);
        persons.forEach(this::addHateoasLinks);
        return persons;
    }

    public PersonDTO findById(Long id) {
        logger.info("Procurando uma pessoa!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Este ID não foi localizado!"));

        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO create(PersonDTO personDTO) {
        logger.info("Inserindo uma pessoal!");

        if (personDTO == null) throw new RequiredObjectIsNullException();

        var entity = parseObject(personDTO, Person.class);
        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTOV2 createV2(PersonDTOV2 personDTO) {
        logger.info("Inserindo uma pessoal - V2!");

        var entity = converter.convertDTOToEntity(personDTO);
        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO personDTO) {
        logger.info("Atualizando uma pessoal!");

        if (personDTO == null) throw new RequiredObjectIsNullException();

        Person entity = repository.findById(personDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Este ID não foi localizado!"));

        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());

        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("Excluindo uma pessoal!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Este ID não foi localizado!"));

        repository.delete(entity);
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).getAll()).withRel("getAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
