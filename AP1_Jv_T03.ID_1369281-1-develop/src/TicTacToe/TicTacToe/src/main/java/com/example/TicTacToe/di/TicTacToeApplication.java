package com.example.TicTacToe.di;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({
        "com.example.TicTacToe.web",
        "com.example.TicTacToe.datasource",
        "com.example.TicTacToe.domain",
        "com.example.TicTacToe.di"
})
public class TicTacToeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicTacToeApplication.class, args);
    }
}
