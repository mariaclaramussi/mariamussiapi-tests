package br.edu.infnet.mariamussiprontuarioapi.model.service;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.Agendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AgendamentoService {

    public void validarData(LocalDateTime data) {
        if (data == null) {
            throw new IllegalArgumentException("Data do agendamento é obrigatória");
        }
        if (data.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data do agendamento não pode estar no passado");
        }
    }

    public void validarTipoConsulta(String tipoConsulta, String planoDeSaude, BigDecimal valor) {
        if (tipoConsulta == null || tipoConsulta.isBlank()) {
            throw new IllegalArgumentException("Tipo de consulta é obrigatório");
        }

        if (!(tipoConsulta.equalsIgnoreCase("particular") || tipoConsulta.equalsIgnoreCase("plano"))) {
            throw new IllegalArgumentException("Tipo de consulta inválido: deve ser 'particular' ou 'plano'");
        }

        if (tipoConsulta.equalsIgnoreCase("plano") && (planoDeSaude == null || planoDeSaude.isBlank())) {
            throw new IllegalArgumentException("Plano de saúde deve ser informado para consultas com plano");
        }

        if (tipoConsulta.equalsIgnoreCase("particular") && planoDeSaude != null && !planoDeSaude.isBlank()) {
            throw new IllegalArgumentException("Plano de saúde não deve ser informado para consultas particulares");
        }
    }

    public void validarBoletim(Agendamento agendamento) {
        if (agendamento.getBoletim() != null) {
            // Boletim só pode estar associado se o paciente for o mesmo
            if (!agendamento.getBoletim().getPaciente().equalsIgnoreCase(agendamento.getPaciente())) {
                throw new IllegalArgumentException("Paciente do boletim não corresponde ao do agendamento");
            }
        }
    }

}
