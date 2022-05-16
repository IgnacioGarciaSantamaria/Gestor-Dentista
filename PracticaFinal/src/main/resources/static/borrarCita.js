let citas = [
    {
        id : '1',
        nombre : 'Jaime',
        apellidos : 'de Clemente',
        dni : '02568420X',
        date : '2001-07-16',
        time : '9:30',
        tratamientos : [{
            id : '1',
            nombre : 'Endodoncia',
            precio : '100',
            duracion : '3:00'
        }, {
            id : '2',
            nombre : 'Blanqueamiento',
            precio : '150',
            duracion : '1:30'
        }]
    }, {
        id : '2',
        nombre : 'Ignacio',
        apellidos : 'García',
        dni : '02568520X',
        date : '2001-07-15',
        time : '13:30',
        tratamientos : [{
            id : '1',
            nombre : 'Revisión caries',
            precio : '100',
            duracion : '1:00'
        }, {
            id : '2',
            nombre : 'Limpieza',
            precio : '150',
            duracion : '1:30'
        }]
    }
];

function mostrarInfoCitaEliminar(id)
{
    for(let cita of citas)
    {
        if(cita.id == id)
        {
            $("#nombre-eliminar").html(cita.nombre);
            $("#apellidos-eliminar").html(cita.apellidos);
            $("#dni-eliminar").html(cita.dni);
            i=1;
            let tratamientosCita = " ";
            for(let tratamiento of cita.tratamientos)
            {
                if(i < cita.tratamientos.length){
                    tratamientosCita = tratamientosCita + tratamiento.nombre+ " , ";
                } else {
                    tratamientosCita = tratamientosCita + tratamiento.nombre;
                }
                i++;
            }
            $("#tratamientos-eliminar").html(tratamientosCita);
        }
    }
}

function borrarCita(id)
{
    for(let cita of citas)
    {

        if(cita.id == id)
        {
            citas.filter(cita);
        }
    }
}

mostrarInfoCitaEliminar(localStorage.getItem('idSelected'));