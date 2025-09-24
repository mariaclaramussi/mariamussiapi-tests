package br.edu.infnet.mariamussiprontuarioapi.model.service;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.BoletimEmergencia;
import br.edu.infnet.mariamussiprontuarioapi.model.domain.ClassificacaoRisco;

import java.time.LocalDateTime;

public class BoletimEmergenciaService {

    public void validarBoletim(BoletimEmergencia boletim) {
        if (boletim == null) {
            throw new IllegalArgumentException("Boletim não pode ser nulo");
        }
    }

    public void validarPaciente(BoletimEmergencia boletim) {
        if (boletim.getPaciente() == null || boletim.getPaciente().isBlank()) {
            throw new IllegalArgumentException("Paciente deve ser informado");
        }
    }

    public void validarDataEntrada(BoletimEmergencia boletim) {
        if (boletim.getDataEntrada().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data de entrada não pode ser futura");
        }
    }

    /**
     * Regras de tempo de espera: para casos URGENTES,
     * dispara alerta se tempo de espera excede limite
     */
    public boolean tempoDeEsperaExcedido(BoletimEmergencia boletim, int limiteEmMinutos) {
        if (boletim.getClassificacaoRisco() == ClassificacaoRisco.URGENTE) {
            long minutos = java.time.Duration.between(
                    boletim.getDataEntrada(), LocalDateTime.now()
            ).toMinutes();

            return minutos > limiteEmMinutos;
        }
        return false;
    }
}
