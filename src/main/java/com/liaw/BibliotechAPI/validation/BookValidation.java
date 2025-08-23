package validation;

import com.liaw.BibliotechAPI.dto.BookDTO;
import com.liaw.BibliotechAPI.exception.BookNotFoundException;
import com.liaw.BibliotechAPI.model.Book;
import com.liaw.BibliotechAPI.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidation {

    private final BookRepository repository;

    public void validateBook(BookDTO dto){
        if (findBook(dto)){
            throw new BookNotFoundException("Usuário não encontrado");
        }
    }

    private Boolean findBook(BookDTO dto){
        Optional<Book> book = repository.findById(dto.id());
        if (dto.id() == null){
            return book.isPresent();
        }
        return !dto.id().equals(book.get().getId()) && book.isPresent();
    }

    public void findBookId(Long id){
        Optional<Book> book = repository.findById(id);
        if (book.isEmpty()){
            throw new BookNotFoundException("Usuário não encontrado");
        }
    }


}
