package validation;

import com.liaw.BibliotechAPI.dto.UsersDTO;
import com.liaw.BibliotechAPI.exception.UserNotFoundException;
import com.liaw.BibliotechAPI.model.Users;
import com.liaw.BibliotechAPI.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UsersRepository repository;

    public void validateUser(UsersDTO dto){
        if (existUser(dto)){
            throw new UserNotFoundException("Livro não encontrado");
        }
    }

    private boolean existUser(UsersDTO dto){
        Optional<Users> users = repository.findById(dto.id());
        if (dto.id() == null){
            return users.isPresent();
        }
        return !dto.id().equals(users.get().getId()) && users.isPresent();
    }

}
