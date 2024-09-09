# Skaffold Spring Boot Project Setup

This README explains the Skaffold configuration and setup for a Spring Boot project.

## Project Structure

- `skaffold.yaml`: Skaffold configuration file
- `k8s-deployment.yaml`: Kubernetes deployment and service configuration
- `Dockerfile`: Docker image configuration
- `build.sh`: Custom build script

## Skaffold Configuration (skaffold.yaml)
This configuration:
1. Defines a custom build process using `build.sh`.
2. Watches for changes in `src/`, `pom.xml`, and `Dockerfile`.
3. Deploys using Kubernetes manifests matching `k8s-*.yaml`.

## Kubernetes Configuration (k8s-deployment.yaml)

Defines a Deployment and a LoadBalancer Service for the application.

## Dockerfile

### Troubleshoooting Skaffold

```kubectl config current-context ``` -  get current context

```kubectl config use-context docker-desktop``` Switch the context to docker-desktop 

```kubectl get nodes``` - shows the context ( should be docker-desktop)

Remove the AWS creds (for kubernetes otherwise it will try to connect to AWS)
```
unset AWS_ACCESS_KEY_ID                  
unset AWS_SECRET_ACCESS_KEY
unset AWS_SESSION_TOKEN
unset AWS_PROFILE
```
### Starting Skaffold

1. `Maven clean install`
2. `Skaffold dev`

When skaffold dev is running, any change you make will be automatically recompiled and built using a script
Therefore you shouldn't need to rebuild and take down your container, changes will automatically deploy using skaffold.
The rebuild happens because skaffold is triggering off the `build.sh` script to run mvn clean install whenever it detects changes 