package com.dbserver.treinamentospring.servico;

import lombok.val;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.lang.System.out;

@Service
public class SorteioAmigoSecretoService implements ISorteioAmigoSecretoService {

    @Override
    public HashMap<String, String> sorteio(List<String> amigos) {
        Random random = new Random();
        HashMap<String, String> amigosSorteados = new HashMap<>();
        var sorteioValido = true;

        do {
            ArrayList<Integer> numerosSorteados = new ArrayList<>();
            for (String amigo : amigos) {
                var amigoSorteadoFlag = false;
                while (!amigoSorteadoFlag) {
                    val numero = Math.abs(random.nextInt()) % amigos.size();
                    val numeroJaFoiSorteado = verificaSeNumeroJaFoiSorteado(numerosSorteados, numero);
                    if (!numeroJaFoiSorteado) {
                        amigoSorteadoFlag = true;
                        numerosSorteados.add(numero);
                        amigosSorteados.put(amigo, amigos.get(numero));
                    } else {
                        out.println("Repete sorteio para: " + amigo);
                        out.println("ultimo sorteio: " + amigos.get(numero));
                    }
                }
            }
            sorteioValido = verificaSeSorteioEhValido(amigosSorteados);
        } while (!sorteioValido);

        amigosSorteados.forEach((key, value) -> {
            val hash = Base64.encodeBase64(value.getBytes(StandardCharsets.UTF_8));
            val hashString = new String(hash, StandardCharsets.UTF_8);
            val url = "http://bc.pyramitec.com/sorteio/" + hashString;
            amigosSorteados.replace(key, url);
        });
        return amigosSorteados;
    }

    private boolean verificaSeNumeroJaFoiSorteado(ArrayList<Integer> numerosSorteados, int numero) {
        return numerosSorteados.stream().anyMatch(number -> number == numero);
    }

    private boolean verificaSeSorteioEhValido(HashMap<String, String> amigosSorteados) {
        return amigosSorteados.entrySet().stream().noneMatch(amigo -> amigo.getKey().equals(amigo.getValue()));
    }

}
