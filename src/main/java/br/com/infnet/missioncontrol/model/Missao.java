package br.com.infnet.missioncontrol.model;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Missao {

    private Long id;

    @NotBlank(message = "nome é obrigatorio")
    private String nome;
    @NotBlank(message = "destino é obrigatorio")
    private String destino;
    @NotNull(message = "O ano é obrigatorio")
    @Min(value = 1957, message = "o ano minimo é 1957")
    @Max(value = 2100, message = "o ano maximo é 2100")
    private Integer anoLancamento;
    private StatusMissao status;
    @NotNull(message = "tripulantes sao obrigatorios")
    private List<String> tripulantes;

    @NotBlank(message = "email é obrigatorio")
    @Pattern(
            regexp = "^[a-zA-Z0-9_+.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Deve ser um e-mail válido")
    private String email;

}
