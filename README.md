# MacroMovies
MacroMovies es una aplicación móvil creada para una prueba técnica. En este README, se mencionarán las características de la aplicación desde un punto de vista técnico.

## Introducción
MacroMovies es una aplicación móvil desarrollada en Kotlin para Android que utiliza el patrón de diseño MVVM (Model-View-ViewModel). El patrón MVVM se utiliza para separar los componentes de una aplicación en tres partes principales:

- Model (Modelo): Representa la capa de datos y lógica de negocio de la aplicación. Aquí se definen las estructuras de datos y las operaciones que se realizan en esos datos. El Modelo no tiene conocimiento de la interfaz de usuario.
- View (Vista): Representa la interfaz de usuario de la aplicación. Es la capa que el usuario ve y con la que interactúa. La Vista es responsable de mostrar los datos y recibir la entrada del usuario.
- ViewModel (Modelo de Vista): Actúa como intermediario entre el Modelo y la Vista. El ViewModel contiene la lógica de presentación y se encarga de transformar los datos del Modelo en un formato adecuado para que la Vista los muestre. También maneja las interacciones del usuario y comunica las acciones del usuario al Modelo si es necesario.

## Caracteristicas
Las principales características de la estructura del proyecto incluyen:

- Implementación de Koin como Inyección de dependencias.
- Uso de Retrofit como manejador de peticiones REST.
- Uso de Firebase Authentication para login de sesion.
- Integración de Navigation Jetpack.

## Mejoras a futuro
Algunas mejoras que se pueden considerar para el futuro incluyen:

- Implementar Compose.
- Conectar MacroMovies a un sistema de autenticación para crear cuentas de usuarios y mostrar registros por usuarios.
- Implementar notificaciones push.
