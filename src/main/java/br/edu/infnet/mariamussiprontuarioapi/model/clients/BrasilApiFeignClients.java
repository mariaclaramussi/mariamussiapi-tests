package br.edu.infnet.mariamussiprontuarioapi.model.clients;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.Feriado;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="brasilapi", url = "${api.brasil.feriados.url}")
public interface BrasilApiFeignClients {

    @GetMapping("{ano}")
    List<Feriado> findByAno(@PathVariable String ano);
}
