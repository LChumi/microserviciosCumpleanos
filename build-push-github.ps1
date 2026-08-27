param (
    [string]$githubUser = "lchumi",
    [string]$repositoryName = "microservicioscumpleanos",
    [string]$tag = "latest"
)

Set-Location -Path $PSScriptRoot

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Build & Push Microservicios" -ForegroundColor Cyan
Write-Host "  Repo: $githubUser/$repositoryName" -ForegroundColor Cyan
Write-Host "  Tag: $tag" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan

# Buscar todas las carpetas con Dockerfile en la raíz del workspace
$foldersWithDockerfile = Get-ChildItem -Directory | Where-Object {
    Test-Path (Join-Path $_.FullName "Dockerfile")
}

if ($foldersWithDockerfile.Count -eq 0) {
    Write-Warning "No se encontraron servicios con Dockerfile en $PSScriptRoot"
    exit 0
}

Write-Host "Servicios encontrados: $($foldersWithDockerfile.Count)`n" -ForegroundColor Green

$succeeded = @()
$failed = @()

foreach ($folder in $foldersWithDockerfile) {
    $serviceName = $folder.Name.Trim()

    if ([string]::IsNullOrWhiteSpace($serviceName)) {
        Write-Warning "Nombre de servicio vacío. Saltando..."
        continue
    }

    $imageName = "ghcr.io/$githubUser/$repositoryName/$serviceName`:$tag"

    Write-Host "----------------------------------------" -ForegroundColor DarkGray
    Write-Host "Servicio: $serviceName" -ForegroundColor Cyan
    Write-Host "Imagen: $imageName" -ForegroundColor DarkGray
    Write-Host ""

    Write-Host "Building..." -ForegroundColor Yellow
    docker build -t $imageName $folder.FullName

    if ($LASTEXITCODE -ne 0) {
        Write-Error "Fallo el build de $serviceName"
        $failed += $serviceName
        continue
    }

    Write-Host "Build exitoso" -ForegroundColor Green

    Write-Host "Pushing to GHCR..." -ForegroundColor Yellow
    docker push $imageName

    if ($LASTEXITCODE -ne 0) {
        Write-Error "Fallo el push de $serviceName"
        $failed += $serviceName
        continue
    }

    Write-Host "Push exitoso" -ForegroundColor Green
    Write-Host ""

    $succeeded += $serviceName
}

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Resumen" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan

Write-Host "`nExitosos ($($succeeded.Count)):" -ForegroundColor Green
foreach ($name in $succeeded) {
    Write-Host "  ghcr.io/$githubUser/$repositoryName/$name`:$tag" -ForegroundColor White
}

if ($failed.Count -gt 0) {
    Write-Host "`nFallidos ($($failed.Count)):" -ForegroundColor Red
    foreach ($name in $failed) {
        Write-Host "  $name" -ForegroundColor White
    }
    exit 1
}
