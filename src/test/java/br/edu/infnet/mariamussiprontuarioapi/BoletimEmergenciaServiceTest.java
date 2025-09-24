package br.edu.infnet.mariamussiprontuarioapi;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.BoletimEmergencia;
import br.edu.infnet.mariamussiprontuarioapi.model.service.BoletimEmergenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoletimEmergenciaServiceTest {

    private BoletimEmergenciaService boletimEmergenciaService;
    private BoletimEmergencia boletim;

    @BeforeEach
    void setUp() {
        boletimEmergenciaService = new BoletimEmergenciaService();
        boletim = new BoletimEmergencia();
    }

    @Test
    @DisplayName("01 - Deve exibir erro se paciente for nulo")
    void deveLancarExcecaoQuandoPacienteForNulo() {
        assertThrows(UnsupportedOperationException.class, () -> boletimEmergenciaService.validarPaciente(boletim));
    }

    @Test
    @DisplayName("02 - Deve exibir erro se data de entrada for futura")
    void deveLancarExcecaoQuandoDataEntradaFutura() {
        assertThrows(IllegalArgumentException.class, () -> boletimEmergenciaService.validarDataEntrada(boletim));
    }

    @Test
    @DisplayName("03 - Deve exibir erro se tempo de espera for excedido para paciente URGENTE")
    void deveGerarAlertaQuandoUrgenteComTempoExcedido() {
        assertTrue(boletimEmergenciaService.tempoDeEsperaExcedido(boletim, 1));
    }

    @Test
    @DisplayName("04 - Deve ter sucesso quando tempo de espera for longo para paciente NAO urgente")
    void naoDeveGerarAlertaQuandoNaoUrgenteMesmoComEsperaLonga() {
        assertTrue(boletimEmergenciaService.tempoDeEsperaExcedido(boletim, 15));
    }
}
