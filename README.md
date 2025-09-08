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
```

## 🗂️ Estructura del proyecto
app/
 └─ src/main/
     ├─ java/com/example/myapplication/
     │   ├─ MainActivity.kt
     │   ├─ SharedViewModel.kt
     │   └─ ui/
     │       ├─ FragmentTextFields.kt
     │       ├─ FragmentBotones.kt
     │       ├─ FragmentSeleccion.kt
     │       ├─ FragmentListas.kt
     │       └─ FragmentInformacion.kt
     └─ res/
         ├─ layout/
         │   ├─ activity_main.xml
         │   ├─ fragment_textfields.xml
         │   ├─ fragment_botones.xml
         │   ├─ fragment_seleccion.xml
         │   ├─ fragment_listas.xml
         │   ├─ item_idioma.xml
         │   └─ fragment_informacion.xml
         ├─ navigation/nav_graph.xml
         ├─ menu/bottom_nav_menu.xml
         └─ drawable/ (íconos vectoriales: ic_text_fields, ic_button, ic_select, ic_list, ic_info, ic_person, etc.)

▶️ Ejecución

Abrir el proyecto en Android Studio Narwhal.

Verificar dependencias (Gradle sync).

Ejecutar en emulador o dispositivo físico (SDK 24+ recomendado).

Navegar con la barra inferior entre las 5 secciones.

🧪 Cómo usar / Qué probar

Texto: escribe Nombre y Apellido → pulsa Guardar. Vuelve a este tab y verifica que persiste (viene del ViewModel).

Botones:

Cambiar tema: cambia el color (se refleja en Info).

Enviar perfil: muestra un Toast con nombre y apellido.

FAB (Nuevo perfil): limpia todo el estado global.

Selección: marca Aficiones, elige Nivel, alterna Perfil público.

Listas: marca uno o varios Idiomas (RecyclerView).

Info: muestra avatar, nombre completo, preferencias, idiomas y progreso % calculado en base a lo capturado.

🔄 Conexión entre fragments

Los fragments escriben y leen del SharedViewModel.

FAB (Nuevo perfil) en “Botones” resetea todo y se refleja en “Texto”, “Selección”, “Listas” e “Info”.

🐞 Problemas comunes (FAQ)

La app se cierra al abrir: revisa que los fragments estén en app/src/main/java/... (no en androidTest/test) y que nav_graph.xml use android:name=".ui.FragmentX".

BottomNavigation transparente: añade íconos vectoriales (Material Icons) o define tintes para texto si no quieres íconos.

FAB tapado por la barra: se usa margen inferior grande en el fragment; alternativamente, muévelo a la Activity con CoordinatorLayout.

📸 Capturas de pantalla

Ver la sección “Checklist de Screenshots”.

📦 Publicación en GitHub (sugerido)
git init
git add .
git commit -m "Mi Perfil de Usuario: app demo con 5 fragments y ViewModel"
git branch -M main
git remote add origin https://github.com/<tu_usuario>/<tu_repo>.git
git push -u origin main

📄 Licencia

MIT 

## Checklist de screenshots (qué capturar y cómo)

**Dispositivo sugerido:** Pixel 6/7 (portrait), tema del sistema claro.  
**Dónde:** cada captura ubicada en `/screenshots/` del repo con nombres consistentes.

### 1) `01_texto.png`
- Fragment **Texto** mostrando:
  - Título “Información Personal”.
  - Dos EditText (Nombre/Apellido) llenos con un ejemplo.
  - Botón **Guardar**.
  - (Opcional) Toast de “Información guardada”.

### 2) `02_botones.png`
- Fragment **Botones** mostrando:
  - Botón **Cambiar tema (color)**.
  - Fila con **flecha → icono de email (latido)** + texto “Enviar perfil (Toast)”.
  - **FAB (Nuevo perfil)** visible por encima de la Bottom Nav (no tapado).
- (Opcional) Toma una segunda (`02b_botones_tema.png`) tras pulsar Cambiar tema, para que se note el color aplicado al header en **Info** después.

### 3) `03_seleccion.png`
- Fragment **Selección** con:
  - Aficiones: marcar al menos 2 (p. ej., *Deporte* y *Música*).
  - Nivel: *Intermedio* seleccionado.
  - Switch “Perfil público”: **ON**.

### 4) `04_listas.png`
- Fragment **Listas** (RecyclerView) con:
  - Lista de idiomas visible.
  - Al menos 2 idiomas chequeados (p. ej., *Español* y *Inglés*).
  - Si la lista no cabe, que se vea el scroll.

### 5) `05_info.png`
- Fragment **Info** con:
  - Header redondeado con **ícono de usuario (ic_person)** tintado por el **themeColor** elegido.
  - “Nombre completo: …”
  - **Nivel** / **Público** / **Aficiones** en **líneas separadas**.
  - **Idiomas** listados.
  - **Progreso %** > 0 con la barra avanzando.

### 6) `06_reset.png` (opcional)
- Tras pulsar **FAB (Nuevo perfil)**:
  - Verifica en **Texto**: campos vacíos.
  - En **Selección**: checkboxes/radio/switch reseteados.
  - En **Listas**: idiomas desmarcados.
  - En **Info**: progreso 0 %.

---

### Cómo tomar las capturas
- **Android Studio → Running Devices → Screen Capture** (botón de cámara).
- O por **ADB**:
  ```bash
  adb exec-out screencap -p > 01_texto.png
