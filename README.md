# Mi Perfil de Usuario (Activities + Fragments + ViewModel)

App demo que muestra 5 Fragments dentro de una sola `MainActivity`, con navegación inferior y un `SharedViewModel` (StateFlow) que sincroniza los datos en tiempo real. Cada fragment explica y **demuestra** un elemento de UI (EditText, Botones, Selección, RecyclerView, TextView/ImageView/ProgressBar).

## 🎯 Objetivo
Enseñar el uso práctico de **Activities, Fragments y Navigation Component**, con un **ViewModel compartido** para un “perfil de usuario” que se va armando módulo por módulo.

## 📱 Estructura
- `MainActivity` + `BottomNavigationView` + `NavHostFragment` (Navigation)
- `FragmentTextFields` — Información Personal (EditText + Guardar)
- `FragmentBotones` — Acciones Rápidas (Button, ImageButton, FAB)
- `FragmentSeleccion` — Preferencias (CheckBox, RadioButton, Switch)
- `FragmentListas` — Idiomas (RecyclerView + CheckBox)
- `FragmentInformacion` — Vista previa (TextView, ImageView, ProgressBar)

> Se usa **XML** (no Compose) para mantenerlo simple y clásico.

## 🧱 Arquitectura
- **MVVM ligero** con `SharedViewModel` y **StateFlow**.
- `activityViewModels()` para compartir el estado entre todos los fragments.
- Actualización **reactiva**: cada fragment observa/actualiza el ViewModel.

## 🧩 Dependencias clave
```gradle
implementation "com.google.android.material:material:1.12.0"
implementation "androidx.navigation:navigation-fragment-ktx:2.8.0"
implementation "androidx.navigation:navigation-ui-ktx:2.8.0"
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4"
implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.8.4"
implementation "androidx.recyclerview:recyclerview:1.3.2"
