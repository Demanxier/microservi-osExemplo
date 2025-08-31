# Projeto de E-commerce com Arquitetura de Microserviços

Este projeto demonstra a implementação de uma arquitetura de microserviços para um sistema de e-commerce simples. A aplicação é construída utilizando Java com o ecossistema Spring, incluindo Spring Boot e Spring Cloud.

## Arquitetura

O sistema é composto por quatro serviços principais que trabalham em conjunto para fornecer a funcionalidade de um e-commerce:

1.  **Service Discovery (Eureka Server):**

      * **Propósito:** Atua como o "catálogo telefônico" da aplicação. Todos os outros microserviços se registram no Eureka, permitindo que eles se encontrem e se comuniquem dinamicamente sem a necessidade de conhecerem os endereços de IP e portas uns dos outros.
      * **Funcionamento:** Ele mantém um registro de todas as instâncias de serviço ativas. Quando um serviço precisa se comunicar com outro, ele consulta o Eureka para obter a localização do serviço desejado.

2.  **API Gateway:**

      * **Propósito:** É o ponto de entrada único para todas as requisições externas. Ele atua como um intermediário entre os clientes e os microserviços.
      * **Funcionalidades:**
          * **Roteamento:** Encaminha as requisições para os microserviços apropriados com base no caminho da URL (ex: `/api/products` é roteado para o `product-catalog-service`).
          * **Balanceamento de Carga:** Utiliza o Service Discovery para distribuir as requisições entre as instâncias disponíveis de um serviço.
          * **Segurança e Filtros:** Pode ser configurado para lidar com autenticação, autorização, logging e outras tarefas transversais.

3.  **Catálogo de Produtos (`product-catalog-service`):**

      * **Propósito:** Gerencia todas as operações relacionadas a produtos.
      * **Funcionalidades:**
          * Expõe um endpoint (`/products`) para listar todos os produtos disponíveis.
          * Expõe um endpoint (`/products/{id}`) para buscar um produto específico pelo seu ID.
          * Inicializa com uma lista de produtos pré-cadastrados (Notebook, Mouse e Monitor) para fins de demonstração.

4.  **Serviço de Pedidos (`order-service`):**

      * **Propósito:** Responsável por gerenciar a criação e o processamento de pedidos.
      * **Funcionalidades:**
          * Expõe um endpoint (`/orders`) para a criação de novos pedidos.
          * Comunica-se com o `product-catalog-service` para verificar a existência e o estoque de um produto antes de criar um pedido.
          * Calcula o valor total do pedido com base no preço do produto e na quantidade solicitada.

## Tecnologias Utilizadas

  * **Java 21:** Versão da linguagem de programação utilizada no projeto.
  * **Spring Boot:** Framework principal para a criação rápida e simplificada de aplicações Java autossuficientes.
  * **Spring Cloud:** Utilizado para a construção de padrões de sistemas distribuídos:
      * **Spring Cloud Netflix Eureka:** Para a implementação do Service Discovery.
      * **Spring Cloud Gateway:** Para a implementação do API Gateway.
  * **Maven:** Ferramenta de gerenciamento de dependências e automação de build do projeto.
  * **Lombok:** Biblioteca utilizada para reduzir a verbosidade do código Java, principalmente em classes de modelo (DTOs e Entidades).
  * **RestTemplate:** Utilizado para a comunicação síncrona (HTTP) entre os microserviços, com a anotação `@LoadBalanced` para integração com o Eureka.

## Como Executar

1.  **Inicie o Service Discovery:**

      * Navegue até o diretório `ServiceDiscovery` e execute a aplicação.
      * Acesse `http://localhost:8761` para ver o painel do Eureka.

2.  **Inicie os outros microserviços:**

      * Execute, em qualquer ordem, o `catalogoProduto`, `Pedido` e `api-gateway`.
      * Verifique no painel do Eureka se todos os serviços (`PRODUCT-CATALOG-SERVICE`, `ORDER-SERVICE`, `API-GATEWAY`) estão registrados.

3.  **Interaja com a API:**

      * **Listar produtos:** Faça uma requisição GET para `http://localhost:8040/api/products`.
      * **Criar um pedido:** Faça uma requisição POST para `http://localhost:8040/api/orders` com um corpo JSON como:
        ```json
        {
          "productId": 1,
          "quantity": 2
        }
        ```
