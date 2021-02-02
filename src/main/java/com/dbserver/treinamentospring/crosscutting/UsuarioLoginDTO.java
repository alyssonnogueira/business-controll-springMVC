package com.dbserver.treinamentospring.crosscutting;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioLoginDTO {

    @NotNull
    String email;

    @NotNull
    String password;
}
