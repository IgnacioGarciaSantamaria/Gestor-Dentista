## PRACTICA FINAL PAT

La funcionalidad de la página web que hemos creado es la de proporcionar un sistema de gestión a un dentista.
Dicho sistema de gestión permite tener una organización absoluta de los tratamientos que ofrece, los clientes de la empresa con sus respectivos historiales y una agenda de citas.

En primer lugar se va a explicar la distribución del BackEnd:
Contamos con 4 tablas distintas, una de tratamientos, una de historiales médicos, una de clientes y una de usuarios.

Para cada tabla se ha desarrollado la funcionalidad End to End para los endpoints y documentos web: `html`, `javascript`, `REST controller`, `service`, `repository`, `entity` & `test`.

La tabla de tratamientos incluye un identificador del tratamiento, el nombre del tratamiento, su duración y una breve descripción de este.
La tabla de historiales de las citas incluye un identificador de la entrada de la tabla, la fecha de la cita, la hora de la cita, el dni del cliente de la cita y el id del tratamiento a realizar.
La tabla de clientes incluye el dni del cliente, su nombre, apellidos, correo y número de telefono.
La tabla de usuarios incluye el nombre de usuario de los administradores y sus respectivas contraseñas.
Debido a las necesidades del proyecto, y ser más eficientes a la hora de conectarnos con los endpoints, se realizaron dos inner join de las tablas Cliente-Historial y de Tratamientos-Historial, de este modo mediante una única solicitud podíamos mostrar toda la información necesaria para nuestra vista. 

Además de los test E2E realizados para cada una de las acciones HTTP que se realizan, se han incluido dos tets unitarios de funcionalidades específicas.
Como en todos los formularios implemenetados, los cuales se explicarán en detalle más adelante, se imponían condiciones de tamaño, de tipo... que hasta que no fuesen satisfechas no se realiza la solicitud HTTP, las funcionalidades unitarias implementadas son comprobaciones más complejas de los datos enviados.
Se realiza en dichos tests unitarios la comprobación de que tanto el DNI como el número de teléfono de los clientes sea válido conforme a las reglas y formato que impone cada clase. En los tests unistarios realizamos comprobaciones de casos específicos de dichas situaciones.


A continuación se procede a explicar la funcionalidad completa del **FrontEnd**:
Debido a la implementación de seguridad, la única manera de acceder a la aplicación es mediante la insercción de un usuario y una contraseña que estén registrados en la tabla de usuarios. Si se intenta acceder a cualquier vista de la aplicación web sin haberse registrado previamente, el usuario es redireccionado directamente a dicha página de login.
Cada vista implementada dentro de la aplicación web tiene el mismo "Header" y el mismo "Footer". Desde el header se puede accedeer a la vista de tratamientos, de calendario y de historiales. Además, incluye la posibilidad de de ver el usuario que está registrado en la aplicación, pudiendo este ser cambiado desde cualquier vista.

En cada conexión realizada con cualquier EndPoint del BackEnd en caso de que ocurra algún tipo de error de conexión o algún error relacionado con la información solicitada/proporcionada, se mostrará en formato de alerta el pertinente mensaje de error.

A la página de inicio se accede pulsado el logro de la empresa mostrado en el header, desde esta vista se pueden crear citas nuevas en función de la presencia de un cliente existente o de un cliente nuevo. Si el cliente que se quiere registrar es nuevo llegamos a la vista donde hay que introducir en el formulario los datos de este. Antes de ser enviados dichos datos, se muestra al usuario los datos insertados, y una vez se confirmen dichos datos estos son enviados al servidor.
Al realizar el POST, se lleva a cabo la validación del DNI y del teléfono mencionada anteriormente y en caso de error se muestra mediante un mensaje de error el problema.

Tras crear el nuevo usuariuo se llega a la misma vista que si quisiéramos insertar una cita con un usuario existente (en cuyo caso con insertar el dni del usuario en un formulario se dispone de la información suficiente para crear la nueva cita).
A la hora de insertar los parámetros de la nueva cita, se realiza una doble validación para solo permitir crear la cita en una hora donde se pueda desarrollar todo el tratamiento sin solapamientos con otras citas. Por tanto, las opciones de horas que se muestran en cualquier fecha seleccionada son considerando tanto la duración del tratamiento que se desea implementar como las citas ya establecidas para ese día.
Una vez completado dicho formulario, se realiza el POST al historial de citas.

Dentro de la información a consultar en el header, al pulsar en tratamientos el usuario accede a la vista de gestión de los tratamientos. En la parte izquierda de la vista se muestran mediante botones concatenados verticalmente todos los tratamientos ya insertados, en la parte superior de dicha zona izquierda de la vista se incluye un buscador donde si se inserta el nombre del tratamiento, aparece información relativa al mismo en la parte derecha de la vista (GET).
Adicionalmente, se pueden crear nuevos tratamientos, cambiándose la parte derecha de la vista para mostrar un formulario con los datos a rellenar sobre el nuevo tratamiento que queremos crear.
Al lado de cada botón concatenado el usuario tiene la posibilidad de editar los tratamientos o de eliminarlos. Si desea eliminarlo se elimina automáticamente, mostrándose una alerta informativa de que dicha acción ha sido realizada. Por otra parte, si se desea editar el tratamiento, al usuario se le muestra un idéntico formulario con la información a rellenar además de la información ya insertada de dicho formulario, así los cambios a realizar son más fáciles de introducir.

Pulsando el menú se puede acceder también a la información sobre los historiales de los usuarios, éstos incluyen todos los tratamientos que se les han hecho o que tienen programados y la fecha en que se hicieron. La forma de acceder al historial de un usuario es mediante la pulsación del botón que lleva su nombre. Si la búsqueda se hace tediosa, se puede buscar al cliente por el DNI, aparenciendo tanto con la pulsación del botón como mediante la búsqueda la información del usuario elegido. Dentro de esta información, se da la posibilidad de añadir algún tratamiento al historial, para aquellos casos en los que el dentista crea conveniente que tratamientos anteriores aparezcan en su historial, o para tratamientos realizados con otros profesionales.

Por otro lado, desde el menú se accede también a la funcionalidad del calendario. En éste podremos encontrar las citas que tendremos cada día. Éstas están representadas por botones con el nombre del usuario que, además, ocupan tanto en el calendario del día como duren los tratamientos a realizar en las mismas. 
Al pulsar en cualquiera de las citas nos aparecerá en la parte derecha de la pantalla la información de la msima, así como la posibilidad de añadir tratamientos a la cita, eliminarla o cobrarla.
Si añadimos tratamientos, el botón de la cita cambiará de tamaño conforme a lo explicado anteriormente.
Si deseamos eliminarla, seremos dirigidos a una página que contiene la información de la cita y nos requiere confirmación de la eliminación.
Si deseamos cobrarla, nos aparecerá el precio total de la cita, calculado como la suma de los precios individuales de cada uno de los tratamientos que en ella se realizan. 

**Datos de Prueba:**
A fin de probar la funcionalidad de la web, se han añadido una serie de datos iniciales de prueba. Para que le sea más fácil encontrar citas, le informamos que existen citas creadas los días 2022-06-20, 2022-07-20, 2022-08-20, así como los días 20, 26, 27 y 28 de mayo.

Además
A fin de probar la funcionalidad de la web, se han añadido una serie de datos iniciales de prueba. Para que le sea más fácil encontrar citas, le informamos que existen citas creadas los días 2022-06-20, 2022-07-20, 2022-08-20, así como los días 20, 26, 27 y 28 de mayo.

Además, su información de inicio de sesión es:
**usuario:** jabrena
**contraseña:** dentista

En cualquier caso, también puede acceder con los usuarios *jaime*, *ignacio* y *blas* y con la misma contraseña.
