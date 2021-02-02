package com.dbserver.treinamentospring.web;

import com.dbserver.treinamentospring.crosscutting.UsuarioLoginDTO;
import com.dbserver.treinamentospring.dominio.Usuario;
import com.dbserver.treinamentospring.servico.ILoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/login")
public class LoginController {

     ILoginService loginService;

     LoginController(ILoginService loginService) {
         this.loginService = loginService;
     }

    @PostMapping()
    public ResponseEntity<Usuario> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        return ResponseEntity.ok(this.loginService.login(usuarioLoginDTO));
    }
}
