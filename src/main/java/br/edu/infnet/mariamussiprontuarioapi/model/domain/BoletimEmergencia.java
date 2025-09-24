package br.edu.infnet.mariamussiprontuarioapi.model.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class BoletimEmergencia {
    private String numeroProntuario;
    private ClassificacaoRisco classificacaoRisco;
    private String paciente;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private String planoSaude;
    private String anamese;
    List<Agendamento> agendamentos;

    public BoletimEmergencia() {
        this.classificacaoRisco = ClassificacaoRisco.BAIXA;
        this.dataEntrada = LocalDateTime.now();
    }

    public String getNumeroProntuario() {
        return numeroProntuario;
    }

    public void setNumeroProntuario(String numeroProntuario) {
        this.numeroProntuario = numeroProntuario;
    }

    public ClassificacaoRisco getClassificacaoRisco() {
        return classificacaoRisco;
    }

    public void setClassificacaoRisco(ClassificacaoRisco classificacaoRisco) {
        this.classificacaoRisco = classificacaoRisco;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(String planoSaude) {
        this.planoSaude = planoSaude;
    }

    public String getAnamese() {
        return anamese;
    }

    public void setAnamese(String anamese) {
        this.anamese = anamese;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
}
