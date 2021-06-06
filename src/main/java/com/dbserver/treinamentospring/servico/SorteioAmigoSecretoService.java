package com.dbserver.treinamentospring.servico;

import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
                        out.println("ultimo valor: " + amigos.get(numero));
                    }
                }
            }
            sorteioValido = verificaSeSorteioEhValido(amigosSorteados);
        } while (!sorteioValido);

        return amigosSorteados;
    }

    private boolean verificaSeNumeroJaFoiSorteado(ArrayList<Integer> numerosSorteados, int numero) {
        return numerosSorteados.stream().anyMatch(number -> number == numero);
    }

    private boolean verificaSeSorteioEhValido(HashMap<String, String> amigosSorteados) {
        return amigosSorteados.entrySet().stream().noneMatch(amigo -> amigo.getKey().equals(amigo.getValue()));
    }

}
