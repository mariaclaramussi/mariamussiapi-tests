package br.edu.infnet.mariamussiprontuarioapi.model.service;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.Agendamento;
import br.edu.infnet.mariamussiprontuarioapi.model.domain.BoletimEmergencia;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public void validarValor(BigDecimal valor, String tipoConsulta) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor é obrigatório");
        }

        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo");
        }

        if (tipoConsulta.equalsIgnoreCase("particular") && valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Consultas particulares devem ter valor maior que zero");
        }

        if (tipoConsulta.equalsIgnoreCase("plano") && valor.compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalArgumentException("Consultas por plano devem ter valor zero");
        }
    }

    public void validarPacienteBoletim(Agendamento agendamento, BoletimEmergencia boletim) {
        if (!agendamento.getPaciente().equalsIgnoreCase(boletim.getPaciente())) {
            throw new IllegalArgumentException(
                    "Paciente do agendamento não corresponde ao paciente do boletim"
            );
        }
    }

    public void validarPaciente(String paciente) {
        if (paciente == null || paciente.isBlank()) {
            throw new IllegalArgumentException("Paciente é obrigatório");
        }
    }

    public void validarMedico(String medico) {
        if (medico == null || medico.isBlank()) {
            throw new IllegalArgumentException("Médico é obrigatório");
        }
    }

    public void validarAgendamento(Agendamento agendamento) {
        Objects.requireNonNull(agendamento, "Agendamento não pode ser nulo");

        validarData(agendamento.getData());
        validarPaciente(agendamento.getPaciente());
        validarMedico(agendamento.getMedico());
        validarTipoConsulta(agendamento.getTipoConsulta(), agendamento.getPlanoDeSaude(), agendamento.getValor());
        validarValor(agendamento.getValor(), agendamento.getTipoConsulta());
    }

}
