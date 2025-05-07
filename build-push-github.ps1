param (
    [string]$githubUser = "lchumi",
    [string]$repositoryName = "microservicioscumpleanos"
)

Set-Location -Path $PSScriptRoot

# Encuentra todas las carpetas con un Dockerfile
$foldersWithDockerfile = Get-ChildItem -Directory | Where-Object {
    Test-Path (Join-Path $_.FullName "Dockerfile")
}

foreach ($folder in $foldersWithDockerfile) {
    $serviceName = $folder.Name.Trim()

    if ([string]::IsNullOrWhiteSpace($serviceName)) {
        Write-Warning "El nombre del servicio está vacío. Saltando..."
        continue
    }

    # Asignar el nombre de la imagen con el formato correcto de `ghcr.io`
    $imageName = "ghcr.io/$githubUser/$repositoryName/$serviceName"

    Write-Host "Construyendo y subiendo '$serviceName' como '$imageName' ..."

    # Construir la imagen
    docker build -t $imageName $folder.FullName

    # Verifica si la construcción fue exitosa y sube la imagen
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Subiendo la imagen $imageName a GitHub Container Registry ..."
        docker push $imageName
    } else {
        Write-Warning "Falló la construcción de $serviceName"
    }
}

Write-Host "Todos los servicios fueron construidos y subidos correctamente"
