package br.com.alura.control.financeiro.infrastructure.gateway;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.control.financeiro.infrastructure.repository.ProfileRepository;
import br.com.alura.control.financeiro.infrastructure.repository.UserRepository;
import br.com.alura.control.financeiro.validations.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UserGateway implements UserDetailsService {

    private UserRepository userRepository;
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("method=loadUserByUsername username={}", username);
        return userRepository.findByEmail(username)
                .orElseThrow(Message.NOT_FOT_USER_PERMISSION::asBusinessException);
    }

}
