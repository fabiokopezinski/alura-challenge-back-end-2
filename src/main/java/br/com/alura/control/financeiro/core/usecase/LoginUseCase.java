package br.com.alura.control.financeiro.core.usecase;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.alura.control.financeiro.config.security.JwtTokenProvider;
import br.com.alura.control.financeiro.core.request.LoginRequest;
import br.com.alura.control.financeiro.core.response.LoginResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Validated
public class LoginUseCase {
    
    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    public LoginResponse auth(@Valid LoginRequest loginRequest) {
     
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

        String token=jwtTokenProvider.createToken(loginRequest.getEmail(),authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        return new LoginResponse(token,"Bearer");
    }
}
