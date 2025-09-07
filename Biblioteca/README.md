README - Sistema de Gestión de Biblioteca

        DESCRIPCIÓN:

Mi proyecto tiene como finalidad implementar un sistema de gestión de libros para una biblioteca utilizando lenguaje JAVA.
El sistema está compuesto por cuatro clases donde tres de ellos trabajan en conjunto para proporcionar una interfaz funcional y
una gestión efectiva de libros. Las tres clases son: Book, Library y LibraryUI, el cuarto es LibraryTest el cual fue creado para
realizar pruebas.

        OBJETIVOS:

El programa permitira cumplir con la gestión básica de libros al administrador, asistente o encargado de la biblioteca para
realizar las siguientes acciones:

* Añadir libros en la biblioteca.

* Eliminar libros mediante su ISBN.

* Prestar y regresar libros.

* Mostrar todos los libros o solo los disponibles.

* Buscar libros por título.

* Gestionar la interacción del usuario desde un menú en consola.

        DISEÑO DEL PROGRAMA

//CLASE BOOK//

Representa a cada libro. Funciona como modelo de datos del sistema, es decir, se usa el encapsulamiento (atributos privados como
getters y setters) para la información esencial de un libro como el titulo, autor, ISBN y la disponibilidad.

Contiene la siguiente estructura:

+----------------------------+
|           Book             |
+----------------------------+
| - titulo : String          |
| - autor : String           |
| - isbn : String            |
| - disponible : boolean     |
+----------------------------+
| + getTitulo() : String     |
| + getAutor() : String      |
| + getIsbn() : String       |
| + isDisponible() : boolean |
| + setTitulo(String) : void |
| + setAutor(String) : void  |
| + setIsbn(String) : void   |
| + setDisponible(boolean)   |
| + toString() : String      |
+----------------------------+

//CLASE LIBRARY //

Es la capa intermedia, se encarga de gestionar la lógica de la biblioteca y utiliza los atributos/métodos de la clase Book. Además
almacena los objetos de tipo Book en una colección ArrayList. Su función principal es proveer métodos para añadir, eliminar, pretar,
regresar y buscar libros.

Contiene la siguiente estructura:

+-------------------------------------------+
|                  Library                  | 
+-------------------------------------------+
| - libros : ArrayList<Book>                |
| - capacidadMaxima : int                   |
+-------------------------------------------+
| + anadirLibro(libro: Book) : boolean      |
| + eliminarLibro(isbn: String) : String    |
| + prestarLibro(isbn: String) : String     |
| + regresarLibro(isbn: String) : boolean   |
| + getTodosLibros() : ArrayList<Book>      |
| + getLibrosDisponibles() : ArrayList<Book>|
| + buscarTitulo(titulo: String) : Book     |
+-------------------------------------------+

NOTA: Se usó ArrayList como estructura de almacenamiento principal en lugar de LinkedList porque el programa realiza más operaciones de 
búsqueda y recorrido que de inserción o eliminación. ArrayList permite accesos directos más rápidos y un uso de memoria más eficiente, 
lo que lo hace más adecuado para la gestión de libros en esta biblioteca, es decir, el sistema realiza con mayor frecuencia operaciones 
de búsqueda, consulta y recorrido (mostrar todos los libros, listar solo los disponibles o buscar por título/ISBN). En estos casos, 
ArrayList resulta más eficiente, ya que permite accesos directos por índice, mientras que LinkedList obligaría a recorrer nodo por nodo.

//CLASE LIBRARYUI

Contiene el Main, permite mostrar una interfaz intectariva para el usuario final a partir de un menpu usando Scanner para leer la entrada
del usuario. Se comunica con la clase Library para ejecutar las operaciones (Interfaz lógica). Se usa Scanner ya que es más fácil de usar
para menús interactivos, porque lee directamente con métodos como nextLine(). Su simplibilidad funciona para programas educativos o practicas.
BufferReader es más complejo, usado para usar gran volumenes de datos.

Contiene la siguiente estructura:

+-----------------------------------+
|            LibraryUI              |
+-----------------------------------+
|                                   |
|                                   |
+-----------------------------------+
| Métodos:                          |
| + main()                          |
| + mostrarMenuPrincipal()          |
| + mostrarMenuGestionLibros()      |
| + anadirLibroMenu()               |
| + eliminarLibroMenu()             |
| + prestarLibroMenu()              |
| + regresarLibroMenu()             |
| + mostrarTodosLibros()            |
| + mostrarLibrosDisponibles()      |
| + buscarLibroMenu()               |
+-----------------------------------+

        FUNCIONALIDADES PRINCIPALES

* Añadir ibro                   -> Se crea un objeto Book y se añade al ArrayList de la clase Library.
* Eliminar libro                -> Se busca por ISBN y se elimina si existe.
* Prestar libro                 -> Cambia el estado de disponible true a false.
* Regresar libro                -> Se valida si estaba prestado (false) y cambia el estado a (true).
* Mostrar Todos                 -> Muestra todos los libros registrados, es decir, todos los datos del ArrayList
* Mostrar Solo Disponibles      -> MUestra solo los libros que esten es estado no disponible (false).
* Buscar por Titulo             -> Usa un sistema de comparación equals.