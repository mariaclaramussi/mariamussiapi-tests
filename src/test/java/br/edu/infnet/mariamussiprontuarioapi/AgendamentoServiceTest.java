package br.edu.infnet.mariamussiprontuarioapi;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.Agendamento;
import br.edu.infnet.mariamussiprontuarioapi.model.domain.BoletimEmergencia;
import br.edu.infnet.mariamussiprontuarioapi.model.service.AgendamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AgendamentoServiceTest {
    private AgendamentoService agendamentoService;
    private Agendamento agendamentoTeste;

    @BeforeEach
    void setUp() {
        agendamentoService = new AgendamentoService();

        agendamentoTeste = new Agendamento();
        agendamentoTeste.setPaciente("João da Silva");
        agendamentoTeste.setMedico("Dra. Maria");
        agendamentoTeste.setTipoConsulta("particular");
        agendamentoTeste.setValor(new BigDecimal("200"));
        agendamentoTeste.setData(LocalDateTime.now().plusDays(1));
    }

    @Test
    @DisplayName("01 - Deve exibir erro para consulta agendada em data passada")
    void deveLancarExcecaoQuandoDataNoPassado() {
        agendamentoTeste.setData(LocalDateTime.now().minusDays(1));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> agendamentoService.validarData(agendamentoTeste.getData()));
        assertEquals("Data do agendamento não pode estar no passado", ex.getMessage());
    }

    @Test
    @DisplayName("02 - Deve exibir erro quando o plano nao é informado em consulta de plano")
    void deveLancarExcecaoQuandoPlanoNaoInformadoParaConsultaComPlano() {
        agendamentoTeste.setTipoConsulta("plano");
        agendamentoTeste.setPlanoDeSaude(null);
        agendamentoTeste.setValor(BigDecimal.ZERO);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> agendamentoService.validarTipoConsulta(agendamentoTeste.getTipoConsulta(),
                        agendamentoTeste.getPlanoDeSaude(),
                        agendamentoTeste.getValor()));
        assertEquals("Plano de saúde deve ser informado para consultas com plano", ex.getMessage());
    }

    @Test
    @DisplayName("03 - Deve exibir erro quando o valor é informado em consulta de plano")
    void deveLancarExcecaoQuandoPlanoComValorNaoZero() {
        agendamentoTeste.setTipoConsulta("plano");
        agendamentoTeste.setPlanoDeSaude("Amil");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> agendamentoService.validarValor(agendamentoTeste.getValor(), agendamentoTeste.getTipoConsulta()));
        assertEquals("Consultas por plano devem ter valor zero", ex.getMessage());
    }

    @Test
    @DisplayName("04 - Deve exibir erro quando valor da consulta particular for negativo")
    void deveLancarExcecaoQuandoValorNegativo() {
        agendamentoTeste.setValor(new BigDecimal("-50"));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> agendamentoService.validarValor(agendamentoTeste.getValor(), agendamentoTeste.getTipoConsulta()));
        assertEquals("Valor não pode ser negativo", ex.getMessage());
    }

    @Test
    @DisplayName("05 - Deve exibir erro quando paciente do boletim é diferente do agendamento")
    void deveLancarExcecaoQuandoPacienteNaoCorresponde() {
        BoletimEmergencia boletim = new BoletimEmergencia();
        boletim.setPaciente("Maria da Silva");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> agendamentoService.validarPacienteBoletim(agendamentoTeste, boletim));
        assertEquals("Paciente do agendamento não corresponde ao paciente do boletim", ex.getMessage());
    }

    @Test
    @DisplayName("06 - Agendamento nao pode ser criado sem tipo de consulta válido")
    void deveLancarExcecaoSeTipoDeConsultaInvalido() {
        agendamentoTeste.setTipoConsulta("SUS");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> agendamentoService.validarTipoConsulta(agendamentoTeste.getTipoConsulta(), agendamentoTeste.getPlanoDeSaude(), agendamentoTeste.getValor()));
        assertEquals("Tipo de consulta inválido: deve ser 'particular' ou 'plano'", ex.getMessage());
    }

    @Test
    @DisplayName("07 - Agendamento particular deve ser validado com sucesso")
    void deveValidarAgendamentoParticularComSucesso() {
        assertDoesNotThrow(() -> agendamentoService.validarAgendamento(agendamentoTeste));
    }

    @Test
    @DisplayName("08 - Agendamento de plano deve ser validado com sucesso")
    void deveValidarAgendamentoPlanoComValorZero() {
        agendamentoTeste.setTipoConsulta("plano");
        agendamentoTeste.setPlanoDeSaude("Unimed");
        agendamentoTeste.setValor(BigDecimal.ZERO);

        assertDoesNotThrow(() -> agendamentoService.validarAgendamento(agendamentoTeste));
    }

    @Test
    @DisplayName("09 - Deve exibir erro para agendamento sem paciente")
    void deveValidarPacienteNaoNulo() {
        agendamentoTeste.setPaciente(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> agendamentoService.validarPaciente(agendamentoTeste.getPaciente()));
        assertEquals("Paciente é obrigatório", ex.getMessage());
    }

    @Test
    @DisplayName("10 - Deve exibir erro para agendamento sem médico")
    void deveValidarMedicoNaoNulo() {
        agendamentoTeste.setMedico(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> agendamentoService.validarMedico(agendamentoTeste.getMedico()));
        assertEquals("Médico é obrigatório", ex.getMessage());
    }

    @Test
    @DisplayName("11 - Deve exibir erro quando o valor é informado em consulta de plano")
    void deveLancarExcecaoQuandoParticularComValorNulo() {
        agendamentoTeste.setValor(BigDecimal.ZERO);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> agendamentoService.validarValor(agendamentoTeste.getValor(), agendamentoTeste.getTipoConsulta()));
        assertEquals("Consultas particulares devem ter valor maior que zero", ex.getMessage());
    }


    @Test
    @DisplayName("12 - Deve exibir erro para todos os parametros obrigatórios com valor nulo")
    void deveLancarExcecoesQuandoValorObrigatorioNulo() {
        agendamentoTeste.setData(null);
        agendamentoTeste.setTipoConsulta(null);
        agendamentoTeste.setValor(null);

        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> agendamentoService.validarData(agendamentoTeste.getData())),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> agendamentoService.validarTipoConsulta(agendamentoTeste.getTipoConsulta(), agendamentoTeste.getPlanoDeSaude(), agendamentoTeste.getValor())),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> agendamentoService.validarValor(agendamentoTeste.getValor(), agendamentoTeste.getTipoConsulta()))
        );
    }

    @Test
    @DisplayName("13 - Deve lancar excecao para plano de saude informado em consulta particular")
    void deveLancarExcecoesQuandoPlanoDeSaudeEmParticular() {
        agendamentoTeste.setPlanoDeSaude("Amil");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> agendamentoService.validarTipoConsulta(agendamentoTeste.getTipoConsulta(),agendamentoTeste.getPlanoDeSaude(), agendamentoTeste.getValor()));
        assertEquals("Plano de saúde não deve ser informado para consultas particulares", ex.getMessage());
    }
}

