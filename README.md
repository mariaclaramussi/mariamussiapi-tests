# Feature 1 - Projeto de Agendamento e Boletim de Emergência

Esta seçao documenta a implementação de testes unitários, seguindo a abordagem TDD (Test Driven Development).

O projeto em questão aborda o agendamento de consultas médicas, que podem ser particulares ou
por plano de saúde. Para cada paciente da rede, existe um boletim associado, destinado a armazenar
o histórico de agendamentos e outras informações do paciente.

---

### 📌 Classe `BoletimEmergencia`

1. **Definir e obter atributos básicos (getters/setters)**
2. **Data de entrada inicializada automaticamente**
3. **Classificação de risco deve ser inicializada como BAIXA**
4. **Deve obter lista de agendamentos**

---

### 📌 Classe `Agendamento`

1. **Getters/Setters de todos os atributos**
2. **Deve obter boletim associado**

---

### 📌 Classe `BoletimEmergenciaService`

1. **Validação do tempo de espera excedido (urgência)**
2. **Validação de datas de entrada e saída**
3. **Validação de data de saída posterior à entrada**
4. **Validação das regras de preenchimento obrigatório**

---

### 📌 Classe `AgendamentoService`

2. **Validação de valor para consultas com plano de saúde**
3. **Validação de valor para consultas particulares**
4. **Validação de paciente entre `BoletimEmergencia` e `Agendamento`**
5. **Validação de data do agendamento (não pode ser passado)**

---

# Feature 2 - Feign Clients ViaCep e BrasilApi

1. Via Cep (https://viacep.com.br): obtém dados de endereço através do cep informado
2. BrasilAPI - Feriados (https://brasilapi.com.br/docs#tag/Feriados-Nacionais): obtém feriados de acordo com o ano informado

### Endpoints 

```bash
curl -X GET "http://localhost:8080/api/consulta-feriados/2025"
```

```bash
curl -X GET "http://localhost:8080/api/consulta-endereco/24230410"
```
