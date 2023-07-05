package br.com.JWT.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.JWT.data.DetalheUsuarioData;
import br.com.JWT.model.UsuarioModel;
import br.com.JWT.repository.UsuarioRepository;

@Component
public class DetalgeUsuarioServiceImpl implements UserDetailsService {
    private final UsuarioRepository repository;

    public DetalgeUsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuario= repository.findByLogin(username);
        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario [" + username +"] não encontrado");
        }
        return new DetalheUsuarioData(usuario);
    }

}
