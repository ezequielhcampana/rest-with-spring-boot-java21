package br.com.ehc.services;

import br.com.ehc.controllers.BookController;
import br.com.ehc.data.dto.v1.BookDTO;
import br.com.ehc.exception.RequiredObjectIsNullException;
import br.com.ehc.exception.ResourceNotFoundException;
import br.com.ehc.mapper.custom.BookMapper;
import br.com.ehc.model.Book;
import br.com.ehc.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.ehc.mapper.ObjectMapper.parseListObjects;
import static br.com.ehc.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    @Autowired
    BookMapper converter;

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(BookService.class.getName());

    public List<BookDTO> findAll() {
        logger.info("Pesquisando todos as pessoas!");

        var books = parseListObjects(repository.findAll(), BookDTO.class);
        books.forEach(this::addHateoasLinks);
        return books;
    }

    public BookDTO findById(Long id) {
        logger.info("Procurando um livro!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Este ID não foi localizado!"));

        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO create(BookDTO bookDTO) {
        logger.info("Inserindo um livro!");

        if (bookDTO == null) throw new RequiredObjectIsNullException();

        var entity = parseObject(bookDTO, Book.class);
        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO bookDTO) {
        logger.info("Atualizando um livro!");

        if (bookDTO == null) throw new RequiredObjectIsNullException();

        Book entity = repository.findById(bookDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Este ID não foi localizado!"));

        entity.setAuthor(bookDTO.getAuthor());
        entity.setLaunchDate(bookDTO.getLaunchDate());
        entity.setPrice(bookDTO.getPrice());
        entity.setTitle(bookDTO.getTitle());

        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("Excluindo um livro!");

        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Este ID não foi localizado!"));

        repository.delete(entity);
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).getAll()).withRel("getAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
