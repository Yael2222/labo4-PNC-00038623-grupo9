package com.server.app.components;

import com.server.app.entities.Role;
import com.server.app.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        if(roleRepository.findByName("ADMIN").isEmpty()){

            roleRepository.save(
                    Role.builder()
                            .name("ADMIN")
                            .build()
            );
        }

        if(roleRepository.findByName("USER").isEmpty()){

            roleRepository.save(
                    Role.builder()
                            .name("USER")
                            .build()
            );
        }
    }
}
