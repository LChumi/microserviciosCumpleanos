#!/bin/bash

#Script para construir y subir las imagenes Docker de microservicios a Docker Hub

#Usuario de Docker Hub
DOCKER_USER=${DOCKER_USER:-"lchumi"}

#Lista de carpetas donde estan los microservicios
services=(
  "microservices/common-module"
  "microservices/ecommerce-service"
  "microservices/meta-service"
  "microservices/microservice-adminserver"
  "microservices/microservice-assist"
  "microservices/microservice-config"
  "microservices/microservice-eureka"
  "microservices/microservice-gateway"
  "microservices/microservice-models"
  "microservices/microservice-notificacion"
  "microservices/microservice-pos"
  "microservices/microservice-reccomprobantes"
  "microservices/mongo-service"
  "microservices/scheduler-service"
  "microservices/utility-classes-module"
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
