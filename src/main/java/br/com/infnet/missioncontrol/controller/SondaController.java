package br.com.infnet.missioncontrol.controller;

import br.com.infnet.missioncontrol.model.ErrorResponse;
import br.com.infnet.missioncontrol.model.Missao;
import br.com.infnet.missioncontrol.model.Sonda;
import br.com.infnet.missioncontrol.service.SondaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sondas")
@RequiredArgsConstructor//gera o construtor com os parametros obrigatorios
//inclusive gera o construtor de sondaservice que normalmente seria escrito a mao
public class SondaController {

    private final SondaService sondaService;

    @GetMapping
    public ResponseEntity listarTodas(){
        List<Sonda> sondas =  sondaService.listarTodas();
        return ResponseEntity.ok(sondas);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        Sonda byId = sondaService.getById(id);
        if(byId != null){
            return ResponseEntity.ok(byId);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity salvar(@Valid @RequestBody Sonda sonda) {
        sondaService.adicionar(sonda);
        return ResponseEntity.status(HttpStatus.CREATED).body(sonda);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        if(id <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("ID invalido"));
        }
        boolean removeu = sondaService.remover(id);
        if(removeu) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Id nao localizado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long  id,
                                    @RequestBody Sonda entrada){
        if(id <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Id invalido"));
        }
        Sonda atualizada = sondaService.atualizar(id, entrada);

        if(atualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Sonda não localizada"));
        }
        return ResponseEntity.ok().body(atualizada);
    }
}
