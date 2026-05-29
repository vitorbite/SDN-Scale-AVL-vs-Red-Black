# 🌐 Packet Router Tree

Sistema desenvolvido para simular o armazenamento e gerenciamento de regras de roteamento de pacotes em redes de computadores utilizando estruturas de dados balanceadas.

O projeto compara o comportamento de duas árvores de busca balanceadas:

- AVL Tree
- Red-Black Tree (Árvore Rubro-Negra)

Além da implementação das estruturas, foram realizados testes automatizados e benchmarks para avaliar desempenho em cenários de alta carga.

---

## 📖 Objetivo

Em equipamentos de rede, como roteadores e firewalls, milhares de regras precisam ser consultadas rapidamente para decidir o destino de um pacote.

Este projeto demonstra como árvores balanceadas podem ser utilizadas para armazenar essas regras de forma eficiente, mantendo operações rápidas mesmo com grandes volumes de dados.

---

## 🏗️ Estrutura do Projeto

### PacketRule

Representa uma regra de roteamento.

Cada regra possui:

- ID
- IP de origem
- IP de destino
- Prioridade

Exemplo:

```java
PacketRule rule =
    new PacketRule(
        10,
        "192.168.1.10",
        "10.0.0.10",
        3
    );
```

---

### Binary Search Tree

Classe abstrata responsável por definir operações básicas de uma árvore de busca:

* Inserção
* Remoção
* Busca

Servindo como base para as implementações AVL e Rubro-Negra.

---

### AVL Tree

A árvore AVL mantém seu balanceamento através de rotações sempre que o fator de balanceamento ultrapassa os limites permitidos.

Operações implementadas:

* Inserção
* Remoção
* Busca
* Rotação simples à esquerda
* Rotação simples à direita
* Rotação dupla esquerda-direita
* Rotação dupla direita-esquerda

---

### Red-Black Tree

A árvore Rubro-Negra utiliza coloração dos nós e rotações para manter o balanceamento.

Regras principais:

1. Todo nó é vermelho ou preto.
2. A raiz é sempre preta.
3. Nós vermelhos não podem possuir filhos vermelhos.
4. Todos os caminhos da raiz até as folhas possuem a mesma quantidade de nós pretos.

Operações implementadas:

* Inserção
* Remoção
* Busca
* Recoloração
* Rotação à esquerda
* Rotação à direita

---

## 🧪 Testes Automatizados

Foram desenvolvidos testes utilizando JUnit 5.

### AVL

Verificações realizadas:

* Balanceamento após inserções
* Balanceamento após remoções
* Inserções duplicadas
* Busca de elementos
* Funcionamento das rotações

### Red-Black

Verificações realizadas:

* Raiz sempre preta
* Ausência de violações vermelho-vermelho
* Altura negra válida
* Busca de elementos
* Inserções duplicadas
* Remoções mantendo as propriedades da árvore

Executar testes:

```bash
mvn test
```

---

## ⚡ Testes de Estresse

O projeto inclui um benchmark responsável por comparar o desempenho das árvores AVL e Rubro-Negra.

Operações avaliadas:

* Inserção
* Busca
* Remoção

Os testes podem ser executados com centenas de milhares ou milhões de registros para análise comparativa.

---

## 📊 Resultados Obtidos

### 1 Milhão de Registros

| Operação | AVL            | Red-Black      |
| -------- | -------------- | -------------- |
| Inserção | 169.365.330 ns | 140.019.180 ns |
| Busca    | 35.687.630 ns  | 102.602.380 ns |
| Remoção  | 49.301.150 ns  | 30.319.720 ns  |

### Observações

* AVL apresentou melhor desempenho em buscas.
* Red-Black apresentou melhor desempenho em inserções.
* Red-Black apresentou melhor desempenho em remoções.
* Em grandes volumes de dados, a Red-Black apresentou melhor desempenho geral.

---

## 🚀 Como Executar

### Clonar o projeto

```bash
git clone <https://github.com/vitorbite/SDN-Scale-AVL-vs-Red-Black>
```

### Entrar na pasta

```bash
cd SDN-Scale-AVL-vs-Red-Black
```

### Compilar

```bash
mvn clean compile
```

### Executar testes

```bash
mvn test
```

### Executar benchmark

```bash
java StressTest
```

---

## 👥 Equipe

Projeto desenvolvido para a disciplina de Estrutura de Dados II.

Integrantes:

* Samuel Egson Milhomem Rodrigues
* Vítor Bitencourt de Andrade
* Arthur Brito Carvalho

---

## 📚 Tecnologias Utilizadas

* Java
* Maven
* JUnit 5
* Git
* GitHub

---

## 📄 Licença

Projeto desenvolvido exclusivamente para fins acadêmicos.