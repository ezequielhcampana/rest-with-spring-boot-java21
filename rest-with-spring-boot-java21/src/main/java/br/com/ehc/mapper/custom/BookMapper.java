package br.com.ehc.mapper.custom;

import br.com.ehc.data.dto.v1.BookDTO;
import br.com.ehc.model.Book;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookMapper {

    public BookDTO convertEntityToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setAuthor(book.getAuthor());
        dto.setLaunchDate(book.getLaunchDate());
        dto.setPrice(book.getPrice());
        dto.setTitle(book.getTitle());
        return dto;
    }

    public Book convertDTOToEntity(BookDTO bookDTO) {
        Book entity = new Book();
        entity.setId(bookDTO.getId());
        entity.setAuthor(bookDTO.getAuthor());
        entity.setLaunchDate(bookDTO.getLaunchDate());
        entity.setPrice(bookDTO.getPrice());
        entity.setTitle(bookDTO.getTitle());
        return entity;
    }
}
