package br.edu.infnet.mariamussiprontuarioapi;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.Agendamento;
import br.edu.infnet.mariamussiprontuarioapi.model.domain.BoletimEmergencia;
import br.edu.infnet.mariamussiprontuarioapi.model.domain.ClassificacaoRisco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoletimEmergenciaTest {
    private BoletimEmergencia boletim;

    @BeforeEach
    void setUp() {
        boletim = new BoletimEmergencia();
    }

    @Test
    @DisplayName("01 - Deve inicializar o Boletim com valores corretos e data do presente")
    void construtorDeveInicializarComClassificacaoBaixaEDataEntradaAtual() {
        assertEquals(ClassificacaoRisco.BAIXA, boletim.getClassificacaoRisco());
        assertNotNull(boletim.getDataEntrada());

        LocalDateTime agora = LocalDateTime.now();
        assertFalse(boletim.getDataEntrada().isAfter(agora),  "A data de entrada nao pode ser futura");
    }

    @Test
    @DisplayName("02 - Deve permitir definir e obter número de prontuário")
    void devePermitirDefinirEObterNumeroProntuario() {
        boletim.setNumeroProntuario("12345");
        assertEquals("12345", boletim.getNumeroProntuario());
    }

    @Test
    @DisplayName("03 - Deve permitir definir e obter plano de saúde")
    void  devePermitirDefinirEObterPlanoSaude(){
        boletim.setPlanoSaude("Unimed");
        assertEquals("Unimed", boletim.getPlanoSaude());
    }

    @Test
    @DisplayName("04 - Deve permitir definir e obter paciente")
    void devePermitirDefinirEObterPaciente() {
        boletim.setPaciente("Maria Oliveira");
        assertEquals("Maria Oliveira", boletim.getPaciente());
    }

    @Test
    @DisplayName("05 - Deve permitir definir e obter anamnese")
    void devePermitirDefinirEObterAnamese() {
        boletim.setAnamese("Paciente apresenta febre alta.");
        assertEquals("Paciente apresenta febre alta.", boletim.getAnamese());
    }

    @Test
    @DisplayName("06 - Deve permitir definir e obter classificacao de risco")
    void devePermitirDefinirEObterClassificacaoRisco() {
        boletim.setClassificacaoRisco(ClassificacaoRisco.URGENTE);
        assertEquals(ClassificacaoRisco.URGENTE, boletim.getClassificacaoRisco());
    }

    @Test
    @DisplayName("07 - Deve permitir definir e obter data de saida")
    void devePermitirDefinirEObterDataSaida() {
        LocalDateTime saida = LocalDateTime.now().plusHours(2);
        boletim.setDataSaida(saida);
        assertEquals(saida, boletim.getDataSaida());
    }

    @Test
    @DisplayName("08 - Deve permitir definir e obter lista de agendamentos")
    void devePermitirDefinirEObterAgendamentos() {
        Agendamento ag1 = new Agendamento();
        Agendamento ag2 = new Agendamento();
        List<Agendamento> agendamentos = Arrays.asList(ag1, ag2);

        boletim.setAgendamentos(agendamentos);

        assertEquals(2, boletim.getAgendamentos().size());
        assertFalse(boletim.getAgendamentos().isEmpty(), "A lista de agendamentos não deve estar vazia.");
    }
}
