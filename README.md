# SPYRO THE DRAGON GUÍA INTERACTIVA #
## INTRODUCCIÓN:
***App de información (tipo "enciclopedia") del juego para consola de Spyro the Dragon con guía interactiva a modo de tutorialpara guiar al usuario en su funcionamiento***

## CARACTERÍSTICAS PRINCIPALES DE LA GUÍA:
**~Pantalla de bienvenida:** Pantalla que aparece la primera vez que instalamos e iniciamos la app. Tiene un botón con gif animado de un diamante girando y un efecto de brillo que se agranda al pulsarlo y nos lleva al siguiente paso.

**~Pantalla de Personajes:** Layout sencillo con bocadillo en forma ovalada indicando qué información encontraremos en la pestaña que nos señala con otra forma circular, "Personajes" en este caso. Finalizado el tutorial, la tarjeta de Ripto tiene un easter egg si la mantienes pulsada y sueltas.

**~Pantalla de Mundos:** Layout sencillo con bocadillo en forma ovalada indicando qué información encontraremos en la pestaña que nos señala con otra forma circular, "Mundos" en este caso. Finalizado el tutorial, si pulsas 3 veces seguidas la misma tarjeta de uno de los mundos, te saldrá un video divertido con unos segundos de gameplay del primer Spyro.

**~Pantalla de Coleccionables:** Layout sencillo con bocadillo en forma ovalada indicando qué información encontraremos en la pestaña que nos señala con otra forma circular, "Coleccionables" en este caso.

**~Pantalla de información:** Layout sencillo con bocadillo en forma ovalada indicando qué información encontraremos en la pestaña que nos señala esta vez con una forma de delineado circular doble con efecto de resplandor. Señala al icono que se muestra un círculo con una "i" dentro, del cual obtendremos el nombre del desarrollador si clicamos en él (con un AlertDialog)

**~Pantalla de Resumen:** Layout sencillo con gradiente que ocupa casi toda la pantalla y tiene transparencia. Nos muestra un resumen de lo que nos han indicado los pasos seguidos en la guía (las pantallas anteriores), tiene un botón "Comenzar" con el que saldremos de la guía y podremos interactuar con la app directamente.

## TECNOLOGÍAS USADAS:
### Desarrollo
- Kotlin
- Android SDK
- Android Studio
- ViewBinding
- RecyclerView
- ConstraintLayout
- LinearLayout
- FrameLayout
- ... Entre otras
### Diseño
- Material Design
- Gifs animados
- Styles.xml
- Shapes
- Colores personalizados
- Backgrounds temáticos
- ... Entre otras
### Animaciones y gráficos
- Canvas
- Custom View
- Path
- RadialGradient
- ValueAnimator
- ... Entre otras
### Persistencia
- SharedPreferences

## INSTRUCCIONES DE USO

A continuación, se describen los pasos necesarios para clonar y ejecutar la aplicación desde este repositorio.

### Clonar el repositorio
Clic en la pestaña "<> Code" y bien puedes copiar el enlace y subirlo a Android Studio o descargar el .zip
Si optas por copiar el enlace, sigue estos pasos:

**1º:** Abre Android Studio.

**2º:** Selecciona "New" y después "Project from Version Control".

**3º:** Se abrirá otra ventana, en ella pegamos el enlace de este repositorio y seleccionamos dónde queremos que se guarde el proyecto. 

**4º:** Aceptamos, confirmamos que creemos en la fuente del proyecto con "Trust project" y ya estaría.

Android Studio sincronizará automáticamente las dependencias mediante Gradle.

## CONCLUSIONES DEL DESARROLLADOR:
Con respecto al desarrollo de la app de Rick & Morty, esta la he disfrutado bastante más e incluso ha sido satisfactoria para ser simplemente una guía interactiva. Me preocupada usar gifs animados por si no se terminaban moviendo, investigando por google y stackoverflow (y más) terminé descubriendo un método y consideraba que quedarían bien en la interfaz de la guía. A pesar de todo, considero que, aunque la veo aceptable, aún tengo que mejorar muchísimo; por ejemplo, hasta última hora no "atiné" con lo de animar una forma, terminé consiguiéndolo al tener que darle el efecto de resplandor a la tarjeta de Ripto.
También tuve que investigar de páginas que tuvieran efectos de sonido disponibles (y gratuitos) para poder usarlos, la cual he guardado en favs porque seguramente vuelva a ella algún día (más pronto que tarde). Eso sí, el video para el easter egg he tenido que hacer "trampas" para conseguirlo (descargarlo y eso...), pero finalmente quedó bien. 
Aparte de los desafíos que me han supuesto la implementación de los sonidos y las animaciones, además de "juguetear" con las formas y las colocaciones de cada elemento (lo típico de "esto me gusta más así, aquí, allí, etc"), ha sido una experiencia gratificante que me ha hecho aprender muchísimo e incluso a afianzar conceptos de unidades anteriores que se me atragantaban (también ayuda mucho el que haya conseguido otro pc con una RAM que tira muchísimo mejor de Android Studio, el emulador va solo prácticamente y así da gusto experimentar, codificar, diseñar y de todo).
Aprendizaje más grande... Sin ser broma, ConstraintLayout se ha convertido en mi Mesías, jeje.

## CAPTURAS DE PANTALLA
<p align="center">
<img width="1080" height="2400" alt="Screenshot_20260304_210121" src="https://github.com/user-attachments/assets/57935deb-3724-4055-8fc7-d58022bab0d0" />
<img width="1080" height="2400" alt="Screenshot_20260304_210056" src="https://github.com/user-attachments/assets/28e62305-d25b-4968-b86e-67b3d4ed6e41" />
<img width="1080" height="2400" alt="Screenshot_20260304_210033" src="https://github.com/user-attachments/assets/c207e434-1e44-424d-851c-224259bcde75" />
<img width="1080" height="2400" alt="Screenshot_20260304_205953" src="https://github.com/user-attachments/assets/fc6a01e5-629c-4dbd-a889-e809eb9bac4d" />
</p>
