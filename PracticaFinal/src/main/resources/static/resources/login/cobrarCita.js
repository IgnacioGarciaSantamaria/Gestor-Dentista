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

async function mostrarInfoCitaCobrar(dni)
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
            $("#nombre-cobrar").html(cita.nombre);
            $("#apellidos-cobrar").html(cita.apellidos);
            $("#dni-cobrar").html(cita.dni);
            i=1;
            let tratamientosEscribir = " ";
            let precio = 0;
            for(let tratamiento of tratamientosCita)
            {
                precio += tratamiento.precio;
                if(i < tratamientosCita.length){
                    tratamientosEscribir = tratamientosEscribir + tratamiento.nombre+ ", ";
                } else {
                    tratamientosEscribir = tratamientosEscribir + tratamiento.nombre;
                }
                i++;
            }
            $("#tratamientos-cobrar").html(tratamientosEscribir);
            $("#precio-cobrar").html(precio+"€");
        }
    }
}

mostrarInfoCitaCobrar(localStorage.getItem('dniSelected'));