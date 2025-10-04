package br.edu.infnet.mariamussiprontuarioapi.model.service;

import br.edu.infnet.mariamussiprontuarioapi.model.clients.BrasilApiFeignClients;
import br.edu.infnet.mariamussiprontuarioapi.model.domain.Feriado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeriadosService {
    private final BrasilApiFeignClients brasilApiFeignClients;

    public FeriadosService(BrasilApiFeignClients brasilApiFeignClients) {
        this.brasilApiFeignClients = brasilApiFeignClients;
    }

    public  List<Feriado>  obterFeriadosPorAno(String ano) {
        List<Feriado> brasilApiFeriadosResponse = brasilApiFeignClients.findByAno(ano);
        System.out.println(brasilApiFeriadosResponse);

        return brasilApiFeriadosResponse;
    }
}
