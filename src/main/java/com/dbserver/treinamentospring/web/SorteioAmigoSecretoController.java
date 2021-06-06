package com.dbserver.treinamentospring.web;

import com.dbserver.treinamentospring.servico.ISorteioAmigoSecretoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/v1/secret-friend/keeper")
public class SorteioAmigoSecretoController {

    ISorteioAmigoSecretoService amigoSecretoService;

    SorteioAmigoSecretoController(ISorteioAmigoSecretoService amigoSecretoService) {
        this.amigoSecretoService = amigoSecretoService;
    }

    @PostMapping()
    public ResponseEntity<HashMap<String, String>> sorteio(@RequestBody List<String> listaDeAmigos) {
        return ResponseEntity.ok(this.amigoSecretoService.sorteio(listaDeAmigos));
    }
}
