package com.server.app.config;

import java.util.Map;
import java.util.Set;

public class SecurityRules {

    public static final Map<String, Set<String>> PUBLIC = Map.of(
            "POST",
            Set.of(
                    "/api/auth/login",
                    "/api/auth/signup"
            )
    );

    public static final Set<String> IGNORED = Set.of(
            "/error"
    );

    public static boolean isPublic(String method, String path) {

        return PUBLIC.containsKey(method)
                && PUBLIC.get(method).contains(path);
    }

    public static boolean isIgnored(String path) {

        return IGNORED.contains(path);
    }
}