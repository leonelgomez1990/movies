# movies
 Movie app for a challenge

Objetivo logrado: Desarrollar una aplicación Android utilizando la API de películas. 
La app contiene una pantalla principal (MoviesMasterFragment) en donde se muestra la lista de películas populares, con sus respectivos títulos y portadas. 
Es posible seleccionar a cada una de esas películas, desplegando una nueva vista (MoviesDetailFragment) con los detalles de la misma: textos en el idioma del celular, como título y lista de géneros, poster, idioma original de la película (quedó el código del idioma de dos caracteres), popularidad (quedó un número flotante recibido).
Se muestran errores sencillos en pantalla.
Al navegar hasta el final de la lista de películas populares, la aplicación consultará por nuevas y se ampliará el listado (paginación)
La vista contiene un campo de búsqueda que permite filtrar aquellas películas en el listado visible que contengan dicho texto. Si no hay películas visibles, se muestra un mensaje indicando el problema.

Para el diseño de la aplicación utilicé las vistas clásicas (xml). Un solo activity y dentro dos fragments, utilizando el patrón modelo vista: Model-View-ViewModel en ui. 
Utilicé inyección de dependencias con Hilt.
Separé el código en capas intentando aplicar lo más parecido a clean architecture:
ui (fragment - viewmodel) -> domain ( usecase - repository)  -> data ( repository implementation - framework (local[Room] * {no disponible en esta entrega}, network[Retrofit])
Para la navegación utilicé Navigation Component y un livedata+observer dentro de la ui para darle la tarea al fragment 
Para la actualización de las vistas (progress bar, swipe refresh) utilicé una máquina de estados, la sealed class "ViewState" siendo actualizado por el patrón observer
Para el listado de películas populares utilicé un recycler view y un adapter, en el que le apliqué la lógica del filtrado.
En los layouts utilicé constraints layout y helpers para el ordenamiento de vistas. Para la representación de imágenes utilicé la librería Glide.
Desde el viewModel utilicé corrutinas para realizar la petición de datos.
La lógica de negocio la situé en domain, en donde en el package model cree las clases, limitado sólo a lo que necesitaba. Por eso necesité hacer tanto en ui como en data distintos mappers parcelables con los datos que necesitaba en cada lugar, por ejemplo en data realicé todas las clases necesarias para obtener los datos por Retrofit.
Para la obtención de datos utilicé la librería Retrofit, junto a mi clave creada y algunos mensajes GETs hacia la API.
