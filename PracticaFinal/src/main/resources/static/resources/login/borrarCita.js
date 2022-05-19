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

const getHistoriales = async () => {
    let historiales;
    let request = await fetch("api/v1/historiales");
    if(request.status === 200)
    {
        data = await request.json();
        historiales = data;
    } else {
        alert("Error al conectarse al servidor de historiales");
    }
    return historiales;
}

async function mostrarInfoCitaEliminar(dni)
{
    let citas = await getCitas();
    let tratamientos = await getTratamientos();
    let tratamientosCita = [];
    for(let cita of citas)
    {
        if(cita.dni == dni && cita.date == localStorage.getItem('fecha'))
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
                    tratamientosEscribir = tratamientosEscribir + tratamiento.nombre+ ", ";
                } else {
                    tratamientosEscribir = tratamientosEscribir + tratamiento.nombre;
                }
                i++;
            }
            $("#tratamientos-eliminar").html(tratamientosEscribir);
        }
    }
}

async function eliminarCita(dni)
{
    let historiales = await getHistoriales();
    for(let historial of historiales)
    {
        console.log(historial);
        if(historial.dni == dni && historial.date == localStorage.getItem('fecha'))
        {
            let request = await fetch("api/v1/historiales/"+ historial.id +"/", {
                method: "DELETE",
                credentials: "same-origin",
            });
            console.log(request);
            if(request.status === 204)
            {
                let mensaje = "Se ha eliminado la cita";
                alert(mensaje);
                window.location.href="./calendario.html";
            } else {
                alert("No se ha podido conectar al servidor para eliminar la cita");
            }
        }
    }
}

mostrarInfoCitaEliminar(localStorage.getItem('dniSelected'));