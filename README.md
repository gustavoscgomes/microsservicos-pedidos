# 📦 Sistema de Pedidos - Microserviços

Este projeto é um sistema de pedidos baseado em microserviços, utilizando **Spring Boot** e **RabbitMQ** para comunicação assíncrona entre os serviços.

## 🚀 Tecnologias Utilizadas

- **Java 21** + **Spring Boot**
- **RabbitMQ** (mensageria)
- **PostgreSQL** (banco de dados)
- **MailHog** (teste de envio de e-mails)
- **Docker** + **Docker Compose**

## 🏗️ Estrutura do Projeto

- **API de Pedidos** → Exposição de endpoints REST
- **Serviço de Notificação** → Envia e-mails sobre pedidos criados
- **Serviço de Processamento** → Processa pedidos e atualiza status

---
📌 *Este é um projeto de estudo focado em arquitetura de microserviços e mensageria.*
