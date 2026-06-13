package com.server.app.components;


import com.server.app.entities.Permission;
import com.server.app.entities.Role;
import com.server.app.repositories.PermissionRepository;
import com.server.app.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PermissionSeeder implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        Role admin =
                roleRepository.findByName("ADMIN")
                        .orElseThrow();

        Role user =
                roleRepository.findByName("USER")
                        .orElseThrow();

        List<Permission> permissions =
                permissionRepository.findAll();

        admin.getPermissions().clear();

        admin.getPermissions()
                .addAll(permissions);

        permissions.stream()
                .filter(p ->
                        p.getMethod()
                                .equals("GET"))
                .forEach(user.getPermissions()::add);

        roleRepository.save(admin);

        roleRepository.save(user);

        System.out.println("PERMISOS CARGADOS");
    }
}