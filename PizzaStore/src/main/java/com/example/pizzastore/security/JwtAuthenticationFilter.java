package com.example.pizzastore.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.pizzastore.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * The Class JwtAuthenticationFilter.
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * The authentication manager.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * The secret key.
     */
    private final String secretKey;

    private final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

//    private final RememberMeServices rememberMeServices = new NullRememberMeServices();
//
//    private final AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

    /**
     * Instantiates a new jwt authentication filter.
     *
     * @param authenticationManager the authentication manager
     */
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, String secretKey) {
        this.authenticationManager = authenticationManager;
        this.secretKey = secretKey;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.authentication.
     * UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http
     * .HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        // Grab credentials and map them to login viewmodel
        User credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create login token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getName(), credentials.getPassword(), new ArrayList<>());

        // Authenticate user
		return authenticationManager.authenticate(authenticationToken);
    }

//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//                                              AuthenticationException failed) throws IOException, ServletException {
//        SecurityContextHolder.clearContext();
//        this.rememberMeServices.loginFail(request, response);
//        this.failureHandler.onAuthenticationFailure(request, response, failed);
//        response.setContentType("application/json");
//        response.getWriter().write(ow.writeValueAsString(new Authorization(null, "Login unsuccessful")));
//    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.authentication.
     * AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.servlet
     * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * javax.servlet.FilterChain, org.springframework.security.core.Authentication)
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException {
        // Grab principal
        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

        // Create JWT Token
        String token = JWT.create().withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 864_000_000)) // 10 days
                .sign(Algorithm.HMAC512(secretKey.getBytes()));

        // Add token in response
        response.addHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");
        response.getWriter().write(ow.writeValueAsString(new Authorization(token, "Login successful")));
    }

    @Getter
    private static class Authorization {
        private final String authorization;
        private final String message;

        public Authorization(String token, String message) {
            this.authorization = token == null ? null : "Bearer ".concat(token);
            this.message = message;
        }
    }
}
