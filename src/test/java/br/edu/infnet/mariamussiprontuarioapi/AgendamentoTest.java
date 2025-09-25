package br.edu.infnet.mariamussiprontuarioapi;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.Agendamento;
import br.edu.infnet.mariamussiprontuarioapi.model.domain.BoletimEmergencia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgendamentoTest {
        @Test
        @DisplayName("01 - Deve permitir definir e obter boletim")
        void devePermitirDefinirEObterBoletim() {
            BoletimEmergencia boletim = new BoletimEmergencia();
            boletim.setPaciente("Maria Silva");

            Agendamento agendamento = new Agendamento();
            agendamento.setBoletim(boletim);

            assertEquals(boletim, agendamento.getBoletim(), "Agendamento tem correspondencia com boletim");
        }


        @Test
        @DisplayName("02 - Deve permitir definir e obter plano de saúde")
        void devePermitirDefinirEObterPlanoDeSaude() {
            Agendamento agendamento = new Agendamento();
            agendamento.setPlanoDeSaude("Unimed");

            assertEquals("Unimed", agendamento.getPlanoDeSaude());
        }

        @Test
        @DisplayName("03 - Deve permitir definir e obter tipo de consulta")
        void devePermitirDefinirEObterTipoConsulta() {
            Agendamento agendamento = new Agendamento();
            agendamento.setTipoConsulta("particular");

            assertEquals("particular", agendamento.getTipoConsulta());
        }

        @Test
        @DisplayName("04 - Deve permitir definir e obter valor da consulta")
        void devePermitirDefinirEObterValor() {
            Agendamento agendamento = new Agendamento();
            BigDecimal valor = new BigDecimal("250.00");
            agendamento.setValor(valor);

            assertEquals(valor, agendamento.getValor());
        }

        @Test
        @DisplayName("05 - Deve permitir definir e obter data do agendamento")
        void devePermitirDefinirEObterData() {
            Agendamento agendamento = new Agendamento();
            LocalDateTime data = LocalDateTime.now().plusDays(1);
            agendamento.setData(data);

            assertEquals(data, agendamento.getData());
        }

        @Test
        @DisplayName("06 - Deve permitir definir e obter paciente")
        void devePermitirDefinirEObterPaciente() {
            Agendamento agendamento = new Agendamento();
            agendamento.setPaciente("João Souza");

            assertEquals("João Souza", agendamento.getPaciente());
        }

        @Test
        @DisplayName("07 - Deve permitir definir e obter o medico")
        void devePermitirDefinirEObterMedico() {
            Agendamento agendamento = new Agendamento();
            agendamento.setMedico("Dr. Pedro");

            assertEquals("Dr. Pedro", agendamento.getMedico());
        }
}

