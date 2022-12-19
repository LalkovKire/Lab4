package mk.finki.ukim.mk.lab.selenium;


import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.AuthService;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    BalloonService balloonService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    AuthService authService;

    private HtmlUnitDriver driver;

    private static String user = "user";
    private static String admin = "admin";
    private static Optional<Balloon> c1;
    private static Optional<Balloon> c2;
    private static Manufacturer m1;
    private static Manufacturer m2;
    private static User regularUser;
    private static User adminUser;
    private static boolean dataInitialized = false;

    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    private void initData() {
        if (!dataInitialized) {
            Manufacturer manufacturer = new Manufacturer("dada","sadasd","asdad");
            m1 = this.manufacturerService.save(manufacturer);
            c1 = balloonService.save("c1", "c1", m1.getId());
            c2 = balloonService.save("c2", "c2",m1.getId());

            regularUser = authService.register(user, user, user, user, user, LocalDate.now());
            adminUser = authService.register(admin, admin, admin, admin, admin,LocalDate.now());
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario()  throws  Exception {
           this.driver = new HtmlUnitDriver(true);
           BalloonsPage balloonsPage = BalloonsPage.to(this.driver);
           balloonsPage.assertElemts(2,0,0,0);
           LoginPage loginPage = LoginPage.openLogin(this.driver);
           balloonsPage = LoginPage.doLogin(this.driver,loginPage,adminUser.getUsername(),adminUser.getPassword());
           balloonsPage.assertElemts(2,2,2,1);
           balloonsPage = AddOrEditPage.addBalloon(this.driver,"TestingOne","TestingOne",m1.getName());
           balloonsPage.assertElemts(3,3,3,1);
           balloonsPage = AddOrEditPage.addBalloon(this.driver,"TestingTwo","TestingTwo",m1.getName());
           balloonsPage.assertElemts(4,4,4,1);
           balloonsPage.getDeleteButtons().get(0).click();
           balloonsPage.assertElemts(3,3,3,1);
           balloonsPage = AddOrEditPage.editBalloon(this.driver, balloonsPage.getEditButtons().get(0), "TestingOne", "TestingOSSne", m1.getName());
           balloonsPage.assertElemts(4, 4, 4, 1);

    }


}
