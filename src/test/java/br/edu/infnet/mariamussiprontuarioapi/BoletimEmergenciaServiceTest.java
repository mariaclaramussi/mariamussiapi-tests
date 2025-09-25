package br.edu.infnet.mariamussiprontuarioapi;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.BoletimEmergencia;
import br.edu.infnet.mariamussiprontuarioapi.model.domain.ClassificacaoRisco;
import br.edu.infnet.mariamussiprontuarioapi.model.service.BoletimEmergenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BoletimEmergenciaServiceTest {

    private BoletimEmergenciaService boletimEmergenciaService;
    private BoletimEmergencia boletim;

    @BeforeEach
    void setUp() {
        boletimEmergenciaService = new BoletimEmergenciaService();

        boletim = new BoletimEmergencia();
        boletim.setClassificacaoRisco(ClassificacaoRisco.MEDIA);
        boletim.setPaciente("Maria Oliveira");
        boletim.setDataEntrada(LocalDateTime.now().minusMinutes(10));
        boletim.setPlanoSaude("Unimed");
        boletim.setAnamese("Paciente com dor de cabeça leve.");

    }

    @Test
    @DisplayName("01 - Deve ibter sucesso para boletim preenchido corretamente")
    void deveValidarBoletimComSucesso() {
        assertDoesNotThrow(() -> boletimEmergenciaService.validarBoletim(boletim));
    }

    @Test
    @DisplayName("02 - Deve exibir erro se paciente for nulo")
    void deveLancarExcecao_quandoPacienteForNulo() {
        boletim.setPaciente(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> boletimEmergenciaService.validarPaciente(boletim));
        assertEquals("Paciente deve ser informado", ex.getMessage());
    }

    @Test
    @DisplayName("03 - Deve exibir erro se data de entrada for futura")
    void deveLancarExcecao_QuandoDataEntradaFutura() {
        boletim.setDataEntrada(LocalDateTime.now().plusDays(1));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> boletimEmergenciaService.validarDataEntrada(boletim));
        assertEquals("Data de entrada não pode ser futura", ex.getMessage());
    }

    @Test
    @DisplayName("04 - Deve exibir alerta se tempo de espera for excedido para paciente URGENTE")
    void deveRetornarTrue_quandoUrgenteComTempoExcedido() {
        boletim.setClassificacaoRisco(ClassificacaoRisco.URGENTE);
        boletim.setDataEntrada(LocalDateTime.now().minusMinutes(30));

        boolean resultado = boletimEmergenciaService.tempoDeEsperaExcedido(boletim, 15);

        assertTrue(resultado);
    }

    @Test
    @DisplayName("05 - Nao deve exibir alerta quando tempo de espera for longo para paciente NAO urgente")
    void deveRetornarFalse_QuandoNaoUrgenteMesmoComEsperaLonga() {
        boletim.setClassificacaoRisco(ClassificacaoRisco.BAIXA);
        boletim.setDataEntrada(LocalDateTime.now().minusMinutes(60));

        boolean resultado = boletimEmergenciaService.tempoDeEsperaExcedido(boletim, 15);

        assertFalse(resultado);
    }

    @Test
    @DisplayName("06 - Deve exibir erro se classificacao for vazia")
    void deveLancarExcecao_quandoClassificacaoRiscoForNula() {
        boletim.setClassificacaoRisco(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> boletimEmergenciaService.validarClassificacaoRisco(boletim.getClassificacaoRisco()));
        assertEquals("Classificação de risco é obrigatória", ex.getMessage());

    }

    @Test
    @DisplayName("07 - Deve exibir erro se anamnese for vazia")
    void deveLancarExcecao_quandoAnamneseNula() {
        boletim.setAnamese(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> boletimEmergenciaService.validarAnamnese(boletim.getAnamese()));
        assertEquals("Anamnese não pode ser vazia", ex.getMessage());

    }

    @Test
    @DisplayName("08 - Deve exibir erro se boletim for nulo")
    void deveLancarExcecao_quandoBoletimNulo() {
        BoletimEmergencia boletimVazio = null;

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> boletimEmergenciaService.validarBoletim(boletimVazio));
        assertEquals("Boletim não pode ser nulo", ex.getMessage());
    }

}
