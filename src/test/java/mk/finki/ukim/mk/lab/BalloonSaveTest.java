package mk.finki.ukim.mk.lab;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.repository.InMemoryUserRepository;
import mk.finki.ukim.mk.lab.repository.jpaRepository.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpaRepository.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.Implementation.AuthServiceImplementation;
import mk.finki.ukim.mk.lab.service.Implementation.BalloonServiceImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BalloonSaveTest {

    @Mock
    private InMemoryUserRepository userRepository;

    private AuthServiceImplementation serviceImplementation;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        LocalDate dateOfBirth = LocalDate.now();
        User user = new User("username","name","surname","password",dateOfBirth);
        Mockito.when(this.userRepository.saveOrUpdate(Mockito.any(User.class))).thenReturn(user);
        serviceImplementation = Mockito.spy(new AuthServiceImplementation(this.userRepository));
    }
    @Test
    public void testSuccessSave() {
        LocalDate dateOfBirth = LocalDate.now();
        User user  = this.serviceImplementation.register("username","password","password","name","surname",dateOfBirth);
        Mockito.verify(this.serviceImplementation).register("username","password","password","name","surname",dateOfBirth);
        Assert.assertNotNull("User is null",user);
        Assert.assertEquals("username",user.getUsername());
        Assert.assertEquals("password",user.getPassword());
        Assert.assertEquals("name",user.getUserFullname().getName());
        Assert.assertEquals("surname",user.getUserFullname().getSurname());
    }
    @Test
    public void testNullUsername() {
        LocalDate dateOfBirth = LocalDate.now();
        Assert.assertThrows("InvalidArgumentsException expected", InvalidArgumentsException.class,
                () -> this.serviceImplementation.register(null,"password","password","name","surname",dateOfBirth));
        Mockito.verify(this.serviceImplementation).register(null,"password","password","name","surname",dateOfBirth);
    }
    @Test
    public void testNullPassword() {
        LocalDate dateOfBirth = LocalDate.now();
        Assert.assertThrows("InvalidArgumentsException expected", InvalidArgumentsException.class,
                () -> this.serviceImplementation.register("username",null,"password","name","surname",dateOfBirth));
        Mockito.verify(this.serviceImplementation).register("username",null,"password","name","surname",dateOfBirth);
    }
    @Test
    public void testEmptyUsername() {
        LocalDate dateOfBirth = LocalDate.now();
        Assert.assertThrows("InvalidArgumentsException expected", InvalidArgumentsException.class,
                () -> this.serviceImplementation.register("","password","password","name","surname",dateOfBirth));
        Mockito.verify(this.serviceImplementation).register("","password","password","name","surname",dateOfBirth);
    }
    @Test
    public void testEmptyPassword() {
        LocalDate dateOfBirth = LocalDate.now();
        Assert.assertThrows("InvalidArgumentsException expected", InvalidArgumentsException.class,
                () -> this.serviceImplementation.register("username","","password","name","surname",dateOfBirth));
        Mockito.verify(this.serviceImplementation).register("username","","password","name","surname",dateOfBirth);
    }
    @Test
    public void testPasswordMatch() {
        LocalDate dateOfBirth = LocalDate.now();
        Assert.assertThrows("PasswordsDoNotMatchException expected", PasswordsDoNotMatchException.class,
                () -> this.serviceImplementation.register("username","password","otherPassword","name","surname",dateOfBirth));
        Mockito.verify(this.serviceImplementation).register("username","password","otherPassword","name","surname",dateOfBirth);
    }
}
