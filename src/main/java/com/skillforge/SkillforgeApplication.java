package com.skillforge;

import com.skillforge.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class SkillforgeApplication {



	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SkillforgeApplication.class, args);
		TaskType.setContext(context);

	}

}
