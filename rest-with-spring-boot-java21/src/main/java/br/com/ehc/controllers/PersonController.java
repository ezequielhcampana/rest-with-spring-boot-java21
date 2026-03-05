package br.com.ehc.controllers;

import br.com.ehc.data.dto.v1.PersonDTO;
import br.com.ehc.services.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@Tag(name = "Pessoas", description = "Endpoits para Gerenciamento de Pessoas")
public class PersonController implements br.com.ehc.controllers.docs.PersonControllerDocs {

    @Autowired
    private PersonService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PersonDTO> getAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Override
    public PersonDTO findById(@PathVariable("id") Long id) {

        var person = service.findById(id);
        person.setBirthDay(new Date());
        person.setPhoneNumber("+55(34)98765-1234");
//        person.setPhoneNumber("");
//        person.setLastName(null);
        person.setSensitiveData("Foo Bar");
        return person;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @Override
    public PersonDTO create(@RequestBody PersonDTO personDTO) {
        return service.create(personDTO);
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @Override
    public PersonDTO update(@RequestBody PersonDTO personDTO) {
        return service.update(personDTO);
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
