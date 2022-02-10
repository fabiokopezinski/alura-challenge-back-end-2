package br.com.alura.control.financeiro.entrypoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.control.financeiro.core.request.LoginRequest;
import br.com.alura.control.financeiro.core.response.LoginResponse;
import br.com.alura.control.financeiro.core.usecase.LoginUseCase;
import lombok.AllArgsConstructor;

@RequestMapping("/login")
@AllArgsConstructor
@RestController
public class LoginController {

    private LoginUseCase loginUseCase;
    
    @PostMapping
    public ResponseEntity<LoginResponse> auth(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginUseCase.auth(loginRequest));
    }    
}
