package com.example.kiloexperiment.authkiloexperiment.models;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    CONSULTANT_READ("consultant:read"),
    CONSULTANT_CREATE("consultant:create"),
    CONSULTANT_UPDATE("consultant:update"),
    CONSULTANT_DELETE("consultant:delete"),
    ACCOUNT_MANAGER_CREATE("accountmanager:create"),
    ACCOUNT_MANAGER_READ("accountmanager:read"),
    ACCOUNT_MANAGER_UPDATE("accountmanager:update"),
    ACCOUNT_MANAGER_DELETE("accountmanager:delete")

    ;

    @Getter
    private final String permission;

}

