package com.server.app.components;


import com.server.app.entities.Permission;
import com.server.app.entities.Role;
import com.server.app.repositories.PermissionRepository;
import com.server.app.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(2) // Debe ejecutarse despues de SaveEndpoints (Order(1))
public class PermissionSeeder implements ApplicationListener<ApplicationReadyEvent> {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {

        Role admin = roleRepository.findByName("ADMIN").orElseThrow();
        Role user  = roleRepository.findByName("USER").orElseThrow();

        List<Permission> permissions = permissionRepository.findAll();

        // ADMIN -> todos los permisos
        admin.getPermissions().clear();
        admin.getPermissions().addAll(permissions);

        // USER -> solo los GET
        user.getPermissions().clear();
        permissions.stream()
                .filter(p -> p.getMethod().equals("GET"))
                .forEach(user.getPermissions()::add);

        roleRepository.save(admin);
        roleRepository.save(user);

        System.out.println("PERMISOS CARGADOS");
    }
}