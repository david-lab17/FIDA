package com.deltacode.kcb;

import com.deltacode.kcb.entity.Role;
import com.deltacode.kcb.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootKcbRestApiApplication implements CommandLineRunner   {
	public SpringBootKcbRestApiApplication(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKcbRestApiApplication.class, args);

	}


	private final RoleRepository roleRepository;
	@Override
	public void run(String... args) throws Exception {
		Role adminRole=new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);

		Role userRole=new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);

	}
}

