package org.instagram.filter;

import lombok.extern.slf4j.Slf4j;
import org.instagram.exceptions.InvalidAccessException;
import org.instagram.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtUtil jwtUtil;

    public AuthenticationFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    /**
     * Gateway Filter that intercepts every request, and tries to extract and validate Authorization token
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request=null;
            /**
             * Check if the route is secured
             */
            if (RouteValidator.isSecured.test(exchange.getRequest())) {
                /**
                 * Check if token is present
                 */
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new InvalidAccessException("missing authorization header");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    /**
                     * Validate Token
                     */
                    jwtUtil.validateToken(authHeader);
                    request =exchange.getRequest()
                            .mutate()
                            .header("username", jwtUtil.extractUsername(authHeader))
                            .build();



                } catch (Exception e) {
                    throw new InvalidAccessException("un authorized access to application");
                }
            }
            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config {

    }
}
