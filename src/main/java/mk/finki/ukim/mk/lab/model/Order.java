package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "balloon_order")
public class Order {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long orderId;

   private String balloonColor;

   private String balloonSize;

   @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
   private LocalDateTime datum;

   public Order(String balloonColor,String balloonSize,LocalDateTime datum) {
      this.balloonColor = balloonColor;
      this.balloonSize = balloonSize;
      this.datum = datum;
   }

   public Order() {}
}
