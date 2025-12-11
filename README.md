# 🐳 TP 25 — Conteneurisation des Microservices avec Docker + Consul

Ce projet démontre comment conteneuriser une architecture microservices Spring Boot et l’orchestrer via **Docker Compose**, tout en utilisant **Consul** comme service discovery.  
L’architecture inclut : ClientService, VoitureService, GatewayService, MySQL et phpMyAdmin.

---

## 🎯 Objectifs du TP

À la fin de ce TP, il est possible de :

- Comprendre l’utilité de Docker dans les architectures microservices  
- Écrire un **Dockerfile multi-stage** pour un microservice Spring Boot  
- Orchestrer plusieurs conteneurs via **docker-compose.yml**  
- Faire la découverte des services via **Consul**  
- Comprendre la différence entre *localhost* (machine hôte) et les *DNS Docker* (mysql, consul…)  
- Diagnostiquer les problèmes typiques : ports, réseau, dépendances  

---

