package br.edu.infnet.mariamussiprontuarioapi;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.EnderecoQueryResult;
import br.edu.infnet.mariamussiprontuarioapi.model.service.EnderecoService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class EnderecoLoader implements ApplicationRunner {
    private final EnderecoService enderecoService;

    public EnderecoLoader(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        EnderecoQueryResult enderecoResultado = enderecoService.obterEnderecoPorCep("24230410");

        System.out.println("CEP Consultado " + enderecoResultado.getCep());
        System.out.println("Endereco " + enderecoResultado.getLogradouro());
        System.out.println("Bairro " + enderecoResultado.getBairro());
        System.out.println("Cidade " + enderecoResultado.getCidade());
    }
}
