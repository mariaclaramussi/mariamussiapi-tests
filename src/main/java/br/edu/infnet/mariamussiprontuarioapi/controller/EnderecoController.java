package br.edu.infnet.mariamussiprontuarioapi.controller;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.EnderecoQueryResult;
import br.edu.infnet.mariamussiprontuarioapi.model.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/consulta-endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoQueryResult> obterEnderecoPorCep (@PathVariable String cep) {

        EnderecoQueryResult resultadoEndereco = enderecoService.obterEnderecoPorCep(cep);
        return ResponseEntity.ok(resultadoEndereco);
    }
}
