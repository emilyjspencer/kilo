package com.example.kiloexperiment.authkiloexperiment.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.kiloexperiment.authkiloexperiment.models.Permission.ACCOUNT_MANAGER_UPDATE;
import static com.example.kiloexperiment.authkiloexperiment.models.Permission.ACCOUNT_MANAGER_CREATE;
import static com.example.kiloexperiment.authkiloexperiment.models.Permission.ACCOUNT_MANAGER_DELETE;
import static com.example.kiloexperiment.authkiloexperiment.models.Permission.ACCOUNT_MANAGER_READ;
import static com.example.kiloexperiment.authkiloexperiment.models.Permission.ADMIN_CREATE;
import static com.example.kiloexperiment.authkiloexperiment.models.Permission.ADMIN_READ;
import static com.example.kiloexperiment.authkiloexperiment.models.Permission.ADMIN_UPDATE;
import static com.example.kiloexperiment.authkiloexperiment.models.Permission.ADMIN_DELETE;
import static com.example.kiloexperiment.authkiloexperiment.models.Permission.CONSULTANT_CREATE;
import static com.example.kiloexperiment.authkiloexperiment.models.Permission.CONSULTANT_UPDATE;
import static com.example.kiloexperiment.authkiloexperiment.models.Permission.CONSULTANT_DELETE;
import static com.example.kiloexperiment.authkiloexperiment.models.Permission.CONSULTANT_READ;

@RequiredArgsConstructor
public enum Role {

    //admin has admin consultant accountmanager
    //consultant has consultant
    //accountmanager has accountmanager
    USER(Collections.emptySet()),
    ADMIN(Set.of(ADMIN_CREATE, ADMIN_READ, ADMIN_UPDATE, ADMIN_DELETE, CONSULTANT_CREATE, CONSULTANT_READ,
            CONSULTANT_UPDATE, CONSULTANT_DELETE, ACCOUNT_MANAGER_CREATE, ACCOUNT_MANAGER_READ, ACCOUNT_MANAGER_UPDATE,
            ACCOUNT_MANAGER_DELETE)),
    ACCOUNT_MANAGER(Set.of(ACCOUNT_MANAGER_READ, ACCOUNT_MANAGER_UPDATE, ACCOUNT_MANAGER_DELETE, ACCOUNT_MANAGER_CREATE)),
    CONSULTANT(Set.of(CONSULTANT_CREATE, CONSULTANT_READ, CONSULTANT_UPDATE, CONSULTANT_DELETE))
    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
