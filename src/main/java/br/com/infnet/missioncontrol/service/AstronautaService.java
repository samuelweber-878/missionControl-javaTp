package br.com.infnet.missioncontrol.service;

import br.com.infnet.missioncontrol.model.Astronauta;
import br.com.infnet.missioncontrol.model.StatusAstronauta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AstronautaService {
    private final List<Astronauta> astronautas = new ArrayList<>();
    private Long proximoId = 4l;

    public AstronautaService() {
        astronautas.add(new Astronauta(
            1L, "Ana Silva", "Brasil", 34, 8, StatusAstronauta.DISPONIVEL
        ));
        astronautas.add(new Astronauta(
                2L, "John Carter", "Estados Unidos", 41, 15, StatusAstronauta.EM_MISSAO
        ));
        astronautas.add(new Astronauta(
                3L, "Mei Lin", "China", 29, 5, StatusAstronauta.AFASTADO
        ));
    }

    public List<Astronauta> listarTodos() {
        return  astronautas;
    }

    public List<Astronauta> filtrarPorStatus(StatusAstronauta status) {

        List<Astronauta> resultado = new ArrayList<>();
        for(Astronauta astronauta : astronautas){
            if(astronauta.getStatus().equals(status)){
                resultado.add(astronauta);
            }
        }
        return resultado;
    }
    public Astronauta buscarPeloId(Long id) {
        for(Astronauta astronauta : astronautas) {
            if (astronauta.getId().equals(id)) {
                return astronauta;
            }
        }
        return null;
    }

    public boolean remover(Long id){
        boolean removeu = false;
        for(int i=0; i<astronautas.size(); i++){
            if(astronautas.get(i).getId().equals(id)){
                astronautas.remove(i);
                removeu = true;
            }
        }
        return removeu;
    }

    public boolean verificarNomeExistente(String nome){
        for (Astronauta astronauta : astronautas){
            if(astronauta.getNome().trim().equalsIgnoreCase(nome.trim())){
                return true;
            }
        }
        return false;
    }

    public boolean verificarOutroExistente(String nome, Long id){//pra nao conflitar com o atual na att
        for (Astronauta astronauta : astronautas){
            if(!astronauta.getId().equals(id)
            && astronauta.getNome().trim().equalsIgnoreCase(nome.trim())){
                return true;
            }
        }
        return false;
    }

    public Astronauta adicionar(Astronauta astronauta){
        if (verificarNomeExistente(astronauta.getNome())){
            return null;
        }
        astronauta.setId(proximoId++);
        astronautas.add(astronauta);
        return astronauta;
    }

    public Astronauta atualizar(Long id, Astronauta entrada){
        Astronauta existente = buscarPeloId(id);
        if (existente == null){
            return null;
        }
        if (verificarOutroExistente(entrada.getNome(), id)){
            return null;
        }
        existente.setNome(entrada.getNome());
        existente.setPais(entrada.getPais());
        existente.setIdade(entrada.getIdade());
        existente.setStatus(entrada.getStatus());
        existente.setAnosExperiencia(entrada.getAnosExperiencia());
        return existente;
    }
}
