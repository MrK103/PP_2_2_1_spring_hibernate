package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car mazda = new Car("Mazda", 323);
      Car toyota = new Car("Toyota", 4);
      Car mitsubishi = new Car("Mitsubishi", 3000);
      Car lexus = new Car("Lexus", 400);

      userService.add(user1.setCar(mazda).setUser(user1));
      userService.add(user2.setCar(toyota).setUser(user2));
      userService.add(user3.setCar(mitsubishi).setUser(user3));
      userService.add(user4.setCar(lexus).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println(user.toString() + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("Mazda", 323));

      context.close();
   }
}
