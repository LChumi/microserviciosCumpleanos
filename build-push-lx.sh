#!/bin/bash

#Script para construir y subir las imagenes Docker de microservicios a Docker Hub

#Usuario de Docker Hub
DOCKER_USER=${DOCKER_USER:-"user"}

#Lista de carpetas donde estan los microservicios
services=(
  "common-module"
  "ecommerce-service"
  "meta-service"
  "microservice-adiminserver"
  "microservice-assist"
  "microservice-config"
  "microservice-eureka"
  "microservice-gateway"
  "microservice-models"
  "microservice-notification"
  "microservice-pos"
  "microservice-reccomprobantes"
  "mongo-service"
  "scheduler-service"
  "utility-classes-module"
)

echo "Usuario debe estar autenticado con docker login"
echo ""

for service in "${services[@]}"
do
  echo " Construyendo imagen para $service ..."
  docker build -t "$DOCKER_USER/$service:latest" "$service"

  echo " Subiendo imagen $DOCKER_USER/$service:latest a Docker Hub ..."
  docker push "$DOCKER_USER/$service:latest"

  echo " $service completado"
  echo "-----------------------------------"
done

echo " ¡Todos los servicios fueron construidos y subidos exitosamente!"
