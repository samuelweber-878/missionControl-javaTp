package br.com.infnet.missioncontrol.controller;


import br.com.infnet.missioncontrol.model.*;
import br.com.infnet.missioncontrol.service.AstronautaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/astronautas")
@RequiredArgsConstructor
public class AstronautaController {
    private final AstronautaService astronautaService;

    @GetMapping("/{id}")
    public ResponseEntity BuscarPeloId(@PathVariable Long id) {
        Astronauta astronauta = astronautaService.buscarPeloId(id);
        if (astronauta != null) {
            return ResponseEntity.ok().body(astronauta);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Astronauta> listar(@RequestParam(required = false) StatusAstronauta status) {
        if(status == null){
            return astronautaService.listarTodos();
        }
        return astronautaService.filtrarPorStatus(status);
    }

    @PostMapping
    public ResponseEntity salvar(@Valid @RequestBody Astronauta astronauta) {
        astronautaService.adicionar(astronauta);
        return ResponseEntity.status(HttpStatus.CREATED).body(astronauta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        if(id <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("ID inválido"));
        }
        boolean removeu = astronautaService.remover(id);
        if(removeu) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("ID não localizado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long  id,
                                    @Valid @RequestBody Astronauta entrada){
        if(id <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Id invalido"));
        }
        if(astronautaService.buscarPeloId(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("ID não localizado"));
        }
        if(astronautaService.verificarOutroExistente(entrada.getNome(), id)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Já existe outro astronauta com esse nome"));
        }
        Astronauta atualizado = astronautaService.atualizar(id, entrada);
        return ResponseEntity.ok().body(atualizado);
    }
}
