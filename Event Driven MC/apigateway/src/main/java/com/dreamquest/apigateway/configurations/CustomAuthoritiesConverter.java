package com.dreamquest.apigateway.configurations;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param jwt the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {


        Map<String, Object> claims = (Map<String, Object>) jwt.getClaims().get("realm_access");

        if(claims == null || claims.isEmpty())
            return new ArrayList<>();

       List<String> roles = (List<String>) claims.get("roles");

        Collection<GrantedAuthority> collect = roles.stream().map(s -> "ROLE_" + s).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

//        Collection<GrantedAuthority> collect = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return collect;


    }
}
