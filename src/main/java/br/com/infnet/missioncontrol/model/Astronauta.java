package br.com.infnet.missioncontrol.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Astronauta {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "País é obrigatório")
    private String pais;

    @NotNull(message = "Idade é obrigatório")
    @Min(value = 18, message = "A idade mínima é de 18 anos")
    private Integer idade;

    @Min(value = 0, message = "Os anos de experiência não podem ser negativos")
    private Integer anosExperiencia;

    @NotNull(message = "Status é obrigatório")
    private StatusAstronauta status;
}
