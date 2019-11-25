package com.jalvarez.listTodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.jalvarez.listTodo.model.FileTODO;

@SpringBootApplication
@EnableConfigurationProperties({FileTODO.class})
@EnableJpaAuditing
public class ListTodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListTodoApplication.class, args);
	}

}
