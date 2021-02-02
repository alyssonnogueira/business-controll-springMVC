package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.crosscutting.UsuarioLoginDTO;
import com.dbserver.treinamentospring.dominio.Usuario;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class LoginService implements ILoginService {

    @Override
    public Usuario login(UsuarioLoginDTO usuarioLoginDTO) {
        if (verifyPassword(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getPassword())) {
            return new Usuario("Usuario Teste", usuarioLoginDTO.getEmail());
        }

        throw new EntityNotFoundException("Email ou Senha invalido");
    }

    private Boolean verifyPassword(String email, String password) {
        val fakeUser = new UsuarioLoginDTO("teste@teste.com", "teste");

        return email.equals(fakeUser.getEmail()) && password.equals(fakeUser.getPassword());
    }
}
