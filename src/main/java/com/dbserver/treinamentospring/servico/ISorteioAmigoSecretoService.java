package com.dbserver.treinamentospring.servico;

import java.util.HashMap;
import java.util.List;

public interface ISorteioAmigoSecretoService {

    HashMap<String, String> sorteio(List<String> amigos);

}
