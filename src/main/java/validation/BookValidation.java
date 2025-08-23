package validation;

import com.liaw.BibliotechAPI.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookValidation {

    private final BookRepository repository;




}
