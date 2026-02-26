# 💪 StayHardApp

Aplicación Android para llevar el seguimiento de tus entrenamientos, organizada por grupos musculares.

## ✨ Características

- **Grupos musculares** — Crea y gestiona tus músculos (pecho, espalda, piernas...)
- **Ejercicios por músculo** — Cada músculo tiene su propia lista de ejercicios con nombre, peso (kg) y repeticiones editables
- **Seguimiento por fecha** — Cada ejercicio muestra la fecha de su última modificación con un indicador de color:
  - 🟢 **Verde** — Modificado hace menos de 10 días
  - 🟡 **Amarillo** — Entre 10 y 19 días sin modificar
  - 🔴 **Rojo** — 20 o más días sin modificar
- **Persistencia de datos** — Todos los datos se guardan localmente con Room

## 🛠️ Stack técnico

- [Jetpack Compose](https://developer.android.com/jetpack/compose) — UI declarativa
- [Room](https://developer.android.com/training/data-storage/room) — Base de datos local
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) — Navegación entre pantallas
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) — Gestión del estado
- [KSP](https://github.com/google/ksp) — Procesador de anotaciones para Room
- Kotlin Coroutines + Flow

## 🚀 Instalación

1. Clona el repositorio
```bash
git clone https://github.com/tu-usuario/GymTracker.git
```
2. Abre el proyecto en **Android Studio**
3. Sincroniza Gradle y ejecuta en un emulador o dispositivo físico

## 📋 Requisitos

- Android 8.0 (API 26) o superior
- Android Studio Hedgehog o superior

## 📄 Licencia

MIT License — siéntete libre de usar, modificar y distribuir este proyecto.
