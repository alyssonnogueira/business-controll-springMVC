package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.crosscutting.UsuarioLoginDTO;
import com.dbserver.treinamentospring.dominio.Usuario;

public interface ILoginService {

    Usuario login(UsuarioLoginDTO usuarioLoginDTO);

}
