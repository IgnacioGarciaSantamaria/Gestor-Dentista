/*let citas = [
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
];*/

const getCitas = async () => {
    let citas;
    let request = await fetch("api/v1/join/clientes/historiales");
    if(request.status === 200)
    {
        data = await request.json();
        citas = data;
    }
    else {
        alert("Error al conectarse al servidor de citas");
    }
    return citas;
}

const getTratamientos = async () => {
    let tratamientos;
    let request = await fetch("api/v1/tratamientos");
    if(request.status === 200){
        data = await request.json();
        tratamientos = data;
    }else{
        alert("Error al conectarse al servidor de tratamientos");
    }
    return tratamientos;
}

async function mostrarInfoCitaEliminar(dni)
{
    let citas = await getCitas();
    let tratamientos = await getTratamientos();
    let tratamientosCita = [];
    for(let cita of citas)
    {
        if(cita.dni == dni && cita.date == sacarFecha())
        {
            for(let tratamiento of tratamientos)
            {
                if(tratamiento.id == cita.idTratamiento)
                {
                    tratamientosCita.push(tratamiento);
                }
            }
        }
    }
    for(let cita of citas)
    {
        if(cita.dni == dni)
        {
            $("#nombre-eliminar").html(cita.nombre);
            $("#apellidos-eliminar").html(cita.apellidos);
            $("#dni-eliminar").html(cita.dni);
            i=1;
            let tratamientosEscribir = " ";
            for(let tratamiento of tratamientosCita)
            {
                if(i < tratamientosCita.length){
                    tratamientosEscribir = tratamientosEscribir + tratamiento.nombre+ " , ";
                } else {
                    tratamientosEscribir = tratamientosEscribir + tratamiento.nombre;
                }
                i++;
            }
            $("#tratamientos-eliminar").html(tratamientosEscribir);
        }
    }
}

async function borrarCita(dni)
{
    let citas = await getCitas();
    for(let cita of citas)
    {
        if(cita.dni == dni)
        {
            citas.filter(cita);
        }
    }
}

mostrarInfoCitaEliminar(localStorage.getItem('dniSelected'));