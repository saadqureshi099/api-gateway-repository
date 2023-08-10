package org.instagram.filter;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    private RouteValidator(){}
    /**
     * routes that can be requested without Authorization header
     */
    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token"
    );

    /**
     * All Endpoints are secured other than openAPiEndpoints
     */
    public static final Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
