package com.nca.agendadortarefas.infrastructure.security;

import com.nca.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    private UsuarioClient client;

    @Autowired
    public UserDetailsServiceImpl(UsuarioClient client) {
        this.client = client;
    }

    public UserDetails loadUserDataBy(String email, String authorizationHeader) {
        var usuarioDTO = client.buscarPorEmail(email, authorizationHeader);
        return User.withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();
    }
}
