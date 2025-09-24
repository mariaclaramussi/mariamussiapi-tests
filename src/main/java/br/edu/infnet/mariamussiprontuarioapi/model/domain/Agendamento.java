package br.edu.infnet.mariamussiprontuarioapi.model.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class Agendamento {

    private BoletimEmergencia boletim;

    private String planoDeSaude;

    private String tipoConsulta; /* particular ou plano*/

    private BigDecimal valor;

    private LocalDateTime data;

    private String paciente;

    private String medico;

    public BoletimEmergencia getBoletim() {
        return boletim;
    }

    public void setBoletim(BoletimEmergencia boletim) {
        this.boletim = boletim;
    }

    public String getPlanoDeSaude() {
        return planoDeSaude;
    }

    public void setPlanoDeSaude(String planoDeSaude) {
        this.planoDeSaude = planoDeSaude;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }
}
