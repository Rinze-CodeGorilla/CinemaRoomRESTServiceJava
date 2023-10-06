package cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    final int ROWS = 9;
    final int COLUMNS = 9;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CinemaRoom getCinemaRoom() {
        return new CinemaRoom(ROWS, COLUMNS);
    }
}
