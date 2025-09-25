# Documentação de Testes - Projeto Agendamento e Boletim de Emergência

Este repositório documenta a implementação de testes unitários,
seguindo a abordagem TDD (Test Driven Development).

O projeto em questão aborda o agendamento de consultas médicas, que podem ser particulares ou
por plano de saúde. Para cada paciente da rede, existe um boletim associado, destinado a armazenar
o histórico de agendamentos e outras informações do paciente.

---

## 📌 Classe `BoletimEmergencia`

### Testes implementados
1. **Definir e obter atributos básicos (getters/setters)**

2. **Data de entrada inicializada automaticamente**

3. **Classificação de risco deve ser inicializada como BAIXA**

4. **Deve obter lista de agendamentos**

---

## 📌 Classe `Agendamento`

### Testes implementados
1. **Getters/Setters de todos os atributos**

2. **Deve obter boletim associado**

---

## 📌 Classe `BoletimEmergenciaService`

### Testes implementados
1. **Validação do tempo de espera excedido (urgência)**

2. **Validação de datas de entrada e saída**

3. **Validação de data de saída posterior à entrada**

4. **Validação das regras de preenchimento obrigatório**

---

## 📌 Classe `AgendamentoService`

### Testes implementados
2. **Validação de valor para consultas com plano de saúde**

3. **Validação de valor para consultas particulares**

4. **Validação de paciente entre `BoletimEmergencia` e `Agendamento`**

5. **Validação de data do agendamento (não pode ser passado)**

---

## 📝 Considerações Finais

- O ciclo **TDD** garantiu que cada regra de negócio foi escrita **primeiro como teste**,
  evitando implementação sem necessidade.
- Os testes cobrem tanto a **camada de modelo** (POJOs) quanto as **regras de negócio nos Services**.
- Refatorações foram aplicadas sempre mantendo todos os testes verdes.
- Durante a implementação de testes Green dos Services também foram adicionadas novas coberturas de teste