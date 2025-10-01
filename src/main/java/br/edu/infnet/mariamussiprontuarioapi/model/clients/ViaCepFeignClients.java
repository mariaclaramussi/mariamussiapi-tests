package br.edu.infnet.mariamussiprontuarioapi.model.clients;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.EnderecoViaCep;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="viacep", url = "${api.viacep.url}")
public interface ViaCepFeignClients {

    @GetMapping("{cep}/json")
    EnderecoViaCep findByCep(@PathVariable String cep);
}
