package br.com.infnet.missioncontrol.service;

import br.com.infnet.missioncontrol.model.Instrumento;
import br.com.infnet.missioncontrol.model.Missao;
import br.com.infnet.missioncontrol.model.Sonda;
import br.com.infnet.missioncontrol.model.StatusMissao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SondaService {
    private final List<Sonda> sondas = new ArrayList<>();
    private Long proximoId = 2L;

    public SondaService(){
        sondas.add(new Sonda(
                1L,
                "Aurora-1",
                "Marte",
                2028,
                "PLANEJADA",
                "Agência orbital",
                List.of(new Instrumento("Câmera", 2l), new Instrumento("Espectometro", 5l))
        ));
    }

    public  List<Sonda> listarTodas() {
        return sondas;
    }

    public Sonda getById(Long id) {
        for (Sonda s: sondas) {
            if(s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public List<Sonda> filtrarPorStatus(StatusMissao status) {

        List<Sonda> resultado = new ArrayList<>();
        for(Sonda sonda : sondas){
            if(sonda.getStatus().equals(status)){
                resultado.add(sonda);
            }
        }
        return resultado;
    }

    public Sonda buscarPeloId(Long id) {
        for(Sonda sonda : sondas) {
            if (sonda.getId().equals(id)) {
                return sonda;
            }
        }
        return null;
    }

    public boolean remover(Long id){
        boolean removeu = false;
        for(int i=0; i<sondas.size(); i++){
            if(sondas.get(i).getId().equals(id)){
                sondas.remove(i);
                removeu = true;
            }
        }
        return removeu;
    }

    public void adicionar(Sonda sonda){
        sonda.setId(proximoId++);
        sondas.add(sonda);
    }

    public Sonda atualizar(Long id, Sonda entrada){
        Sonda existente = buscarPeloId(id);
        if (existente == null){
            return null;
        }
        existente.setNome(entrada.getNome());
        existente.setDestino(entrada.getDestino());
        existente.setAnoLancamento(entrada.getAnoLancamento());
        existente.setStatus(entrada.getStatus());
        existente.setInstrumentos(entrada.getInstrumentos());
        return existente;
    }
}
