package br.edu.infnet.mariamussiprontuarioapi.model.service;

import br.edu.infnet.mariamussiprontuarioapi.model.clients.ViaCepFeignClients;
import br.edu.infnet.mariamussiprontuarioapi.model.domain.EnderecoViaCep;
import br.edu.infnet.mariamussiprontuarioapi.model.domain.EnderecoQueryResult;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final ViaCepFeignClients viaCepFeignClients;

    public EnderecoService(ViaCepFeignClients viaCepFeignClients) {
        this.viaCepFeignClients = viaCepFeignClients;
    }

    public EnderecoQueryResult obterEnderecoPorCep (String cep) {
        if (cep == null || cep.trim().isEmpty()) {
            throw new IllegalArgumentException("CEP não pode ser nulo ou vazio.");
        }

        String cepFormatado = cep.replaceAll("[^0-9]", "");

        if (!cepFormatado.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP inválido. Deve conter exatamente 8 dígitos numéricos.");
        }

        try {
            EnderecoViaCep resultadoEndereco = viaCepFeignClients.findByCep(cepFormatado);

            EnderecoQueryResult enderecoQueryResult = new EnderecoQueryResult();
            enderecoQueryResult.setCep(resultadoEndereco.getCep());
            enderecoQueryResult.setLogradouro(resultadoEndereco.getLogradouro());
            enderecoQueryResult.setBairro(resultadoEndereco.getBairro());
            enderecoQueryResult.setUf(resultadoEndereco.getUf());
            enderecoQueryResult.setCidade(resultadoEndereco.getLocalidade());
            enderecoQueryResult.setComplemento(resultadoEndereco.getComplemento());

            return enderecoQueryResult;
        } catch (FeignException e) {
            throw new RuntimeException("Erro ao consultar ViaCep. Status: " +
                    e.status() + " - " + e.getMessage(), e);
        }

    }
}
