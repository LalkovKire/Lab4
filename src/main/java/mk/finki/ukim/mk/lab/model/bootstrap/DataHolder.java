package mk.finki.ukim.mk.lab.model.bootstrap;

import lombok.Getter;
import lombok.Setter;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class DataHolder {
     public static List<Order> orderList = new ArrayList<>();
     public static List<Balloon> balloonList = new ArrayList<>();
     public static List<Manufacturer> manufacturerList = new ArrayList<>();
     public static List<User> users = new ArrayList<>();

     @PostConstruct
     public void init() {
         Manufacturer manufacturer = new Manufacturer("Nike","Germany","Bulevar6");
         Manufacturer manufacturer1 = new Manufacturer("BMW","Germany","Bulevar6");
         Manufacturer manufacturer2 = new Manufacturer("Mercedes","Japan","Bulevar6");
         Manufacturer manufacturer3 = new Manufacturer("Porche","France","Bulevar6");
         Manufacturer manufacturer4= new Manufacturer("Opel","Serbia","Bulevar6");
         Manufacturer manufacturer5 = new Manufacturer("Toyota","Italy","Bulevar6");

         manufacturerList.add(manufacturer1);
         manufacturerList.add(manufacturer2);
         manufacturerList.add(manufacturer3);
         manufacturerList.add(manufacturer4);
         manufacturerList.add(manufacturer5);

         balloonList.add(new Balloon("Red","Balloon", manufacturer));
         balloonList.add(new Balloon("Black","Balloon", manufacturer1));
         balloonList.add(new Balloon("Green","Balloon", manufacturer2));
         balloonList.add(new Balloon("White","Balloon", manufacturer3));
         balloonList.add(new Balloon("Yellow","Balloon", manufacturer4));
         balloonList.add(new Balloon("Grey","Balloon", manufacturer5));
         balloonList.add(new Balloon("Brown","Balloon", manufacturer1));
         balloonList.add(new Balloon("Purple","Balloon", manufacturer3));
         balloonList.add(new Balloon("Pink","Balloon", manufacturer));

         users.add(new User("kire","kiril","lalkov","123", LocalDate.now()));
     }
}
