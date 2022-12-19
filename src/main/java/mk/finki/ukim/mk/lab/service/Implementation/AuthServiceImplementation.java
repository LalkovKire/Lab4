package mk.finki.ukim.mk.lab.service.Implementation;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.repository.InMemoryUserRepository;
import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImplementation implements AuthService {

    private final InMemoryUserRepository userRepository;

    public AuthServiceImplementation(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname,LocalDate dateOfBi) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        User user = new User(username,name, surname, password, dateOfBi);
        return userRepository.saveOrUpdate(user);
    }

}
