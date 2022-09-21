package com.deltacode.kcb;

import com.deltacode.kcb.entity.Role;
import com.deltacode.kcb.model.SpringSecurityAuditorAware;
import com.deltacode.kcb.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Slf4j
public class SpringBootKcbRestApiApplication   {




	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return new SpringSecurityAuditorAware();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKcbRestApiApplication.class, args);

	}
//	@Autowired
//	private  RoleRepository roleRepository;
//	@Override
//	public void run(String... args) throws Exception {
//		Role adminRole=new Role();
//		adminRole.setName("ROLE_ADMIN");
//		roleRepository.save(adminRole);
//
//		Role userRole=new Role();
//		userRole.setName("ROLE_USER");
//		roleRepository.save(userRole);
//
//	}



}



