package br.com.infnet.missioncontrol.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor@NoArgsConstructor@ToString
public class Sonda {
    private Long id;

    @NotBlank(message = "Nome é obrigatorio")
    private String nome;

    @NotBlank(message = "Destino é obrigatorio")
    private String destino;

    @Min(value = 1957, message = "O ano minimo é 1957")
    @Max(value = 2100, message = "O ano maximo é 2100")
    private Integer anoLancamento;
    private String status;

    @NotNull(message = "Agência é obrigatória")
    private String agencia;

    @NotBlank(message = "A sonda deve ter pelo menos um instrumento")
    private List<Instrumento> instrumentos;
}
