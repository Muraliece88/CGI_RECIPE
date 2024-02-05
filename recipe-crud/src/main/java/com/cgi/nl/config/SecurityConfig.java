package com.cgi.nl.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;



    @Configuration
    public class SecurityConfig {
        private final String createURL = "/api/v1/recipies/createRecipe";
        private final String deleteURL = "/api/v1/recipies/removeRecipie";
        private final String getURL = "/api/v1/recipies/getRecipies";
        private final String updateURL = "/api/v1/recipies/saveRecipie";
        private final  String actuator="/actuator/**";

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
            MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

            http.csrf(csrfConfigurer ->
                    csrfConfigurer.ignoringRequestMatchers(mvcMatcherBuilder.pattern(createURL),
                            mvcMatcherBuilder.pattern(updateURL), mvcMatcherBuilder.pattern(deleteURL), mvcMatcherBuilder.pattern(getURL),
                            PathRequest.toH2Console()));
            http.csrf(csrf -> csrf.disable());

            http.headers(headersConfigurer ->
                    headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
            http.authorizeHttpRequests(auth ->
                    auth
                            .requestMatchers(new AntPathRequestMatcher(createURL, HttpMethod.POST.toString())).hasAnyRole("ADMIN")
                            .requestMatchers(new AntPathRequestMatcher(updateURL, HttpMethod.PUT.toString())).hasAnyRole("ADMIN")
                            .requestMatchers(new AntPathRequestMatcher(deleteURL, HttpMethod.DELETE.toString())).hasAnyRole("ADMIN")
                            .requestMatchers(new AntPathRequestMatcher(getURL, HttpMethod.GET.toString())).hasAnyRole("ADMIN")
                            .requestMatchers(PathRequest.toH2Console()).authenticated()
                            .requestMatchers(mvcMatcherBuilder.pattern(actuator)).hasAnyRole("SUPERUSER")
                            .anyRequest().permitAll()
            );

            http.formLogin(Customizer.withDefaults());
            http.httpBasic(Customizer.withDefaults());
            return http.build();
        }
    }



