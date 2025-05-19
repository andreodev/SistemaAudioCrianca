# 🎵 Sistema de Formas com Áudio para Crianças

Este é um aplicativo Java educativo simples que apresenta formas geométricas (quadrado, triângulo e círculo) com cores e sons associados. Ele utiliza uma interface gráfica com Swing e reproduz áudios ao interagir com as formas.

---

## 🧱 Estrutura do Projeto

```
SistemaAudioCrianca/
├── src/
│   ├── audio/                  # Arquivos de áudio (.wav)
│   └── shapes/                 # Código-fonte Java
│       ├── controller/
│       ├── model/
│       └── view/
├── MANIFEST.MF                 # Arquivo de manifesto para o JAR
├── build.sh                    # Script para compilar, empacotar e executar
└── README.md                   # Este arquivo
```

---

## ▶️ Como executar

### Pré-requisitos

- Java JDK 8+ instalado
- Terminal Bash (Linux/macOS) ou Git Bash (Windows)

### Passos

1. **Compile e execute com o script:**

```bash
./build.sh
```

> Esse script irá:
> - Compilar os arquivos `.java` em `out/`
> - Copiar a pasta de áudio `src/audio` para o diretório do projeto
> - Gerar o arquivo `app.jar`
> - Executar o JAR com `java -jar app.jar`

---

## 📦 Gerar JAR manualmente

Caso prefira fazer manualmente:

```bash
# Compilar
mkdir -p out
find src -name "*.java" > sources.txt
javac -d out @sources.txt

# Copiar os áudios
cp -r src/audio .

# Criar o JAR
jar cfm app.jar MANIFEST.MF -C out . audio/
```

### Estrutura esperada do `MANIFEST.MF`

```
Main-Class: shapes.Main
```

---

## 📁 Créditos e Licença

- Desenvolvido para fins educativos
- Áudios e imagens (se houver) devem ser de uso livre
