# Mi Perfil de Usuario (Activities + Fragments + ViewModel)

App demo que muestra **5 Fragments** dentro de una sola `MainActivity`, con **Navigation Component** y un **SharedViewModel** basado en **StateFlow** que sincroniza los datos en tiempo real. Cada fragment explica y **demuestra** un elemento de UI (EditText, Botones, Selección, RecyclerView, TextView/ImageView/ProgressBar).

> **Última actualización:** 08-sep-2025 (America/Mexico_City)



## 🎯 Objetivo

Enseñar el uso práctico de **Activities, Fragments y Navigation**, con un **ViewModel compartido** para armar un “perfil de usuario” módulo por módulo.


## 📱 Estructura

- `MainActivity` + `BottomNavigationView` + `NavHostFragment` (Navigation)
- `FragmentTextFields` — Información Personal (**EditText + Guardar**)
- `FragmentBotones` — Acciones Rápidas (**Button, ImageButton, FAB**)
- `FragmentSeleccion` — Preferencias (**CheckBox, RadioButton, Switch**)
- `FragmentListas` — Idiomas (**RecyclerView + CheckBox**)
- `FragmentInformacion` — Vista previa (**TextView, ImageView, ProgressBar**)

Todos los textos están en español. La UI está hecha en **XML** (no Compose).


## 🧱 Arquitectura

- **MVVM ligero** con `SharedViewModel` y **StateFlow**.
- `activityViewModels()` para compartir el estado entre fragments.
- Actualización **reactiva**: cada fragment observa/actualiza el ViewModel.


## 🧩 Dependencias clave

```gradle
implementation "com.google.android.material:material:1.12.0"
implementation "androidx.navigation:navigation-fragment-ktx:2.8.0"
implementation "androidx.navigation:navigation-ui-ktx:2.8.0"
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4"
implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.8.4"
implementation "androidx.recyclerview:recyclerview:1.3.2"
````

## 🗂️ Árbol del proyecto (resumen)

```
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
```


## ▶️ Ejecución

1. Abrir el proyecto en **Android Studio Narwhal**.
2. Verificar dependencias (Gradle sync).
3. Ejecutar en emulador o dispositivo físico (SDK 24+ recomendado).
4. Navegar con la barra inferior entre las 5 secciones.


## 🧪 Cómo usar / Qué probar

* **Texto**: escribe *Nombre* y *Apellido* → pulsa **Guardar**. (Los campos persisten al volver: vienen del `ViewModel`).
* **Botones**:

  * **Cambiar tema**: cambia un color de tema (se refleja en “Info”).
  * **Enviar perfil**: muestra un Toast con nombre y apellido del `ViewModel`.
  * **FAB (Nuevo perfil)**: limpia **todo** el estado global (afecta a todos los fragments).
* **Selección**: marca *Aficiones*, elige *Nivel*, alterna *Perfil público* (todo persiste en el `ViewModel`).
* **Listas**: marca uno o varios *Idiomas* (RecyclerView con `CheckBox`).
* **Info**: muestra avatar, nombre completo, preferencias (en **líneas separadas**), idiomas y **progreso %** calculado.


## 🔄 Conexión entre fragments

* Todos los fragments leen/escriben al **mismo `SharedViewModel`**.
* El **FAB “Nuevo perfil”** en “Botones” resetea el estado y se refleja en “Texto”, “Selección”, “Listas” e “Info”.



## 🐞 Problemas comunes (FAQ)

* **Se cierra al abrir**
  Verifica que los fragments estén en `app/src/main/java/...` (no en `androidTest`/`test`).
  En `nav_graph.xml` usa `android:name=".ui.FragmentX"` (nombre relativo).

* **BottomNavigation transparente / textos casi invisibles**
  Añade **íconos vectoriales** (Material Icons) a cada item, o define tintes para texto/ícono.

* **FAB tapado por la barra inferior**
  Se usa margen inferior grande en el fragment. Alternativa: mover el FAB a la **Activity** y anclarlo con `CoordinatorLayout`.


## 📸 Checklist de screenshots (entregables)

**Recomendación:** Dispositivo Pixel 6/7 (portrait), tema claro.
Guárdalas en `/screenshots/` del repo con estos nombres:

1. **`01_texto.png`**
   <img width="428" height="952" alt="image" src="https://github.com/user-attachments/assets/80223602-a1ec-4ee2-8d1f-aadf0d3dcff6" />
   Fragment **Texto** con:

   * “Información Personal”
   * Dos EditText (Nombre/Apellido) llenos
   * Botón **Guardar**
   * (Opcional) Toast “Información guardada”

3. **`02_botones.png`**
  <img width="428" height="952" alt="image" src="https://github.com/user-attachments/assets/831eb3da-3d4d-40de-87b0-48d891ea1fbc" />
   Fragment **Botones** con:

   * Botón **Cambiar tema (color)**
   * Fila con **flecha → icono email (con latido)** + texto “Enviar perfil (Toast)”
   * **FAB (Nuevo perfil)** visible, no tapado por la barra
   <img width="428" height="952" alt="image" src="https://github.com/user-attachments/assets/7fb0436e-dc62-4b69-968e-4f901c9fafbe" />


4. **`03_seleccion.png`**
   <img width="428" height="952" alt="image" src="https://github.com/user-attachments/assets/a979faad-41f0-4201-80e9-838742f83d11" />
   Fragment **Selección** con:
 
   * Aficiones: al menos 2 marcadas
   * Nivel: **Intermedio**
   * Switch “Perfil público”: **ON**

5. **`04_listas.png`**
   <img width="428" height="952" alt="image" src="https://github.com/user-attachments/assets/3c496228-d6c7-4dd5-a69e-cc6a317f8661" />
   Fragment **Listas** con:

   * Idiomas visibles
   * Al menos 2 seleccionados (p. ej., Español e Inglés)
   * Si no caben, que se note el scroll

7. **`05_info.png`**
   <img width="428" height="952" alt="image" src="https://github.com/user-attachments/assets/823c698d-5f2b-4a91-9e9e-d77a87662d89" />
   Fragment **Info** con:

   * Header redondeado y **ícono de usuario (ic\_person)** tintado por el **themeColor**
   * “Nombre completo: …”
   * **Nivel**, **Público**, **Aficiones** en **líneas separadas**
   * **Idiomas** listados
   * **Progreso %** > 0 y barra avanzando

8. **`06_reset.png`** *(opcional)*
   <img width="428" height="952" alt="image" src="https://github.com/user-attachments/assets/27ec6978-632d-4598-8f13-c9631bf50935" />
   <img width="428" height="952" alt="image" src="https://github.com/user-attachments/assets/3b97a143-9056-4fd2-8b96-37128e2f0de6" />
   Tras **FAB (Nuevo perfil)**:

   * **Texto**: campos vacíos
   * **Selección**: todo desmarcado / valor por defecto
   * **Listas**: idiomas desmarcados
   * **Info**: progreso 0 %




