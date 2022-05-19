##PRACTICA FINAL PAT

La funcionalidad de la página web que hemos creado es la de proporcionar un sistema de gestión a un dentista.
Dicho sistema de gestión permite tener una organización absoluta de los tratamientos que ofrece, los clientes de la empresa con sus respectivos historiales y una agenda de citas.

En primer lugar se va a explicar la distribución del BackEnd:
Contamos con 4 tablas distintas, una de tratamientos, una de historiales médicos, una de clientes y una de usuarios.

Para cada tabla se desarrollo la funcionalidad End to End para los endpoints y documentos web: `html`, `javascript`, `REST controller`, `service`, `repository`, `entity` & `test`.

La tabla de tratamientos incluye un identificador del tratamiento, el nombre del tratamiento, su duración y una breve descripción de este.
La tabla de historiales de las citas incluye un identificador de la entrada de la tabla, la fecha de la cita, la hora de la cita, el dni del cliente de la cita y el id del tratamiento suministrado.
La tabla de clientes incluye el dni del cliente, su nombre, apèllidos, correo y número de telefono.
La tabla de usuarios incluye el nombre de usuario de los administradores y sus respectivas contraseñas.
Debido a las necesidades del proyecto, y ser más eficientes a la hora de conectarnos con los endpoints, se realizaron dos inner join de las tablas Cliente-Historial y de Tratamientos-Historial, de este modo mediante una única solicitud podíamos mostrar toda la información necesaria para nuestra vista. 

Además de los test E2E realizados para cada una de las acciones HTTP que se realizan, se han incluido dos tets unitarios de funcionalidades específicas.
Como en todos los formularios implemenetados, los cuales se explicarán en detalle más adelante, se imponían condiciones de tamaño, de tipo... que hasta que no fuesen satisfechas no se realiza la solicitud HTTP, las funcionalidades unitarias implementadas son comprobaciones más complejas de los datos enviados.
Se realiza en dichos tests unitarios la comprobación de que tanto el DNI como el número de teléfono de los clientes sea válido conforme a las reglas y formato que impone cada clase. En los tests unistarios realizamos comprobaciones de casos específicos de dichas situaciones.


A continuación se procede a explicar la funcionalidad completa del FrontEnd:
Debido a la implementación de seguridad, la única manera de acceder a la aplicación es mediante la insercción de un usuario y una contraseña que estén registrados en la tabla de usuarios. Si se intenta acceder a cualquier vista de la aplicación web sin haberse registrado previamente, el usuario es redireccionado directamente a dicha página de login.
Cada vista implementada dentro de la aplicación web tiene el mismo "Header" y el mismo "Footer". Desde el header se puede accedeer a la vista de tratamientos, de clientes y de historiales. Además, incluye la posibilidad de de ver el usuario que está registrado en la aplicación, pudiendo este ser cambiado desde cualquier vista.

En cada conexión realizada con cualquier EndPoint del BackEnd en caso de que ocurra algún tipo de error de conexión o algún error relacionado con la información solicitada/proporcionada, se mostrará en formato de alerta el pertinente mensaje de error.

A la página de inicio se accede pulsado el logro de la empresa mostrado en el header, desde esta vista se pueden crear citas nuevas en función de la presencia de un cliente existente o de un cliente nuevo. Si el cliente que se quiere registrar es nuevo llegamos a la vista donde hay que introducir en el formulario los datos de este. Antes de ser enviados dichos datos, se muestra al usuario los datos insertados, y una vez se confirmen dichos datos estos son enviados al servidor.
Al hacer el realizaer el POST, se realiza la validación del DNI y del teléfono mencionada anteriormente y en caso de error se muestra mediante un mensaje de error el problema.

Tras crear el nuevo usuariuo se llega a la misma vista que si quisieramos insertar una cita con un usuario existente, para ello, con insertar el dni del usuario en un formulario ya tenemos la información suficiente para crear la nueva cita.
A la hora de insertar los parámetros de la nueva cita, se realiza una doble validación para solo permitir crear la cita en una hora donde se pueda desarrollar todo el tratamiento sin solapamientos con otras citas. Por tanto, las opciones de horas que se muestran en cualquier fecha seleccionada son considerando tanto la duración del tratamiento que se desea implementar como las citas ya establecidas para ese día.
Una vez completado dicho formulario, se realiza el POST la historial de citas.

Dentro de la información a consultar en el header, al pulsar en tratamientos el usuario accede a la vista de gestión de los tratamientos. En la parte izquierda de la vista se muestran mediante botones concatenados verticalmente todos los tratamientos ya insertados, en la parte superior de dicha zona izquierda de la vista se incluye un buscador donde si se inserta el nombre del tratamiento, aparece información relativa al mismo en la parte derecha de la vista (GET).
Adicoinalmente, se pueden crear nuevos tratamientos, cambiándose la parte derecha de la vista para mostrar un formulario con los datos a rellenar sobre el nuevo tratamiento que queremos crear.
Al lado de cada botón concatenado el usuario tiene la posibilidad de editar los tratamientos o de eliminarlos. Si desea eliminarlo se elimina automáticamente, mostrándose una alerta informativa de que dicha acción ha sido realizada. Por otra parte, si se desea editar el tratamiento, al usuario se le muestra un idéntico formulario con la información a rellenar además de la información ya insertada de dicho formulario, así los cambios a realizar son más fáciles de introducir.
