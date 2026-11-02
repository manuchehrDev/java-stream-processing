package com.example.TicTacToe.di;

import com.example.TicTacToe.datasource.repository.TicTacToeRepositoryImpl;
import com.example.TicTacToe.datasource.repository.TicTacToeServiceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicTacToeConfiguration {

    @Bean
    public TicTacToeRepositoryImpl ticTacToeRepository(){
        return new TicTacToeRepositoryImpl();
    }

    @Bean
    public TicTacToeServiceRepository ticTacToeServiceRepository(TicTacToeRepositoryImpl ticTacToeRepository){
        return new TicTacToeServiceRepository(ticTacToeRepository);
    }

}
