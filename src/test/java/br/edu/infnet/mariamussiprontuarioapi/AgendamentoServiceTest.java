package br.edu.infnet.mariamussiprontuarioapi;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.Agendamento;
import br.edu.infnet.mariamussiprontuarioapi.model.service.AgendamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AgendamentoServiceTest {
    private AgendamentoService agendamentoService;

    @BeforeEach
    void setUp() {
        agendamentoService = new AgendamentoService();
    }

    @Test
    @DisplayName("01 - Deve exibir erro para consulta agendada em data passada")
    void deveLancarExcecaoQuandoDataNoPassado() {
        assertThrows(UnsupportedOperationException.class,
                () -> agendamentoService.validarData(null));
    }

    @Test
    @DisplayName("02 - Deve exibir erro quando o plano nao é informado no tipoConsulta = plano")
    void deveLancarExcecaoQuandoPlanoNaoInformadoParaConsultaComPlano() {
        assertThrows(UnsupportedOperationException.class,
                () -> agendamentoService.validarTipoConsulta("", "", BigDecimal.valueOf(0)));
    }

    @Test
    @DisplayName("03 - Deve exibir erro quando valor da consulta for negativo")
    void deveLancarExcecaoQuandoValorNegativo() {
        assertThrows(UnsupportedOperationException.class, () -> agendamentoService.validarTipoConsulta("", "",  BigDecimal.valueOf(0)));
    }

    @Test
    @DisplayName("04 - Deve exibir erro quando paciente do boletim é diferente do agendamento")
    void deveLancarExcecaoQuandoPacienteNaoCorresponde() {
        Agendamento agendamento = new Agendamento();
        assertThrows(IllegalArgumentException.class, () -> agendamentoService.validarBoletim(agendamento));
    }
}
