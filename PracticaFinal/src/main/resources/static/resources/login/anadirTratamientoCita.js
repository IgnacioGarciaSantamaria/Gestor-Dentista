let diccTratamientos = new Map();

const insertarInfo = () =>{
    let info = document.getElementById("info-cliente-nueva-cita");
    info.textContent = localStorage.getItem('nombreCompleto') + " (" + localStorage.getItem('dni') + ')';
}

insertarInfo();

const obtenerTratamientos = async () => {
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


const presentarTratamientos = async () => {
    let desplegable = document.getElementById("seleccionTratamiento");
    let citas = await getCitas();
    let citasFecha = [];
    for(let cita of citas)
    {
        if(cita.date == localStorage.getItem('fecha'))
        {
            citasFecha.push(cita);
        }
    }
    let tratamientos = await obtenerTratamientos();
    let diferencia = "24:00:00";
    let citaSeleccionada;
    for(let cita of citasFecha)
    {
        if((sumadorRestadorHoras(0, cita.time, localStorage.getItem('hora'))<diferencia || sumadorRestadorHoras(0, "20:00:00", localStorage.getItem('hora'))<diferencia) && cita.time!=localStorage.getItem('hora'))
        {
            diferencia = sumadorRestadorHoras(0, cita.time, localStorage.getItem('hora'));
        } else if(cita.time == localStorage.getItem('hora')) {
            citaSeleccionada = cita;
        }
    }
    console.log(diferencia, citaSeleccionada);
    let duracion = await getStringDuracion(citaSeleccionada);
    for(let tratamiento of tratamientos){
        if(sumadorRestadorHoras(1,duracion,tratamiento.duracion)<=diferencia)
        {
            diccTratamientos.set(tratamiento.nombre,tratamiento.id);
            let option = document.createElement("option");
            option.value = tratamiento.nombre;
            option.innerHTML = tratamiento.nombre;
            option.selected = "selected";
            desplegable.insertAdjacentElement("afterbegin",option);
        }
    }
}

presentarTratamientos();

const getStringDuracion = async (cita) => {
    let duracion = await getCitaTime(cita.dni);
    let duracionstring = ""+duracion.horas+":"+duracion.minutos+":00";
    return duracionstring;
}


const generarCita = async () => {
    let seleccionTratamiento = document.getElementById("seleccionTratamiento");
    let tratamiento = seleccionTratamiento.options[seleccionTratamiento.selectedIndex].value;
    
    let request = await fetch("api/v1/historiales", {
        method: "POST",
        body: JSON.stringify({
            id: 1,
            dni: localStorage.getItem('dniSelected'),
            idTratamiento: diccTratamientos.get(tratamiento),
            date:  localStorage.getItem('fecha'),
            time: localStorage.getItem('hora')
        }),
        headers: {
            "Content-Type": "application/json",
        },
        dataType: "json",
    });

    if(request.status === 200){
        alert("Cita generada Correctamente");
        window.location.href="./calendario.html";
    }else{
        alert("No hemos podido añadir este tratamiento");
    }
}

async function getCitaTime(dni) {
    let citas = await getCitas();
    let tratamientos = await obtenerTratamientos();
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
    let duracion = {
        horas: 0,
        minutos: 0,
    };
    for (let tratamiento of tratamientosCita) {
        let temp = tratamiento.duracion.split(":");
        let horas = parseInt(temp[0]);
        let minutos = parseInt(temp[1]);
        duracion.horas += horas;
        duracion.minutos += minutos;

        while(duracion.minutos > 60) {
            duracion.horas += 1;
            duracion.minutos -= 60;
        }
    }
    return duracion;
}

const obtenerHorasFecha = async (fecha) =>{
    horasHistoriales = [];
    let request = await fetch("api/v1/historiales/?fecha="+fecha);
    if(request.status === 200){
        data = await request.json();
        for(let i in data){
            horasHistoriales.push(data[i].time);
            idsHistoriales.push(data[i].idTratamiento);
        }
    }else{
        alert("Error al obtener las horas de la base de datos");
    }
    return horasHistoriales;
}

const sumadorRestadorHoras = (flag,tiempo,duracion) =>{
    tiempoFin = duracion.split(":");
    minutoFin = parseInt(tiempoFin[1]);
    horaFin = parseInt(tiempoFin[0]);

    tiempoInicial = tiempo.split(":");

    minutoInicio = parseInt(tiempoInicial[1]);
    horaInicio  = parseInt(tiempoInicial[0]);


    if(flag == 1){
        horaFinal = horaInicio + horaFin;
        minutoFinal =  minutoInicio + minutoFin;
    } else{
        horaFinal = horaInicio - horaFin;
        minutoFinal =  minutoInicio - minutoFin;
    }


    if((minutoFinal == 60) && (flag==1)){
        horaFinal = horaFinal +1;
        minutoFinal = 0;
    }

    if(minutoFinal < 0){
        horaFinal = horaFinal -1; 
        minutoFinal = 30;
    }

    if(horaFinal < 10){
        horaFinal = "0" + horaFinal;
    }

    if(minutoFinal <10){
        minutoFinal = "0" + minutoFinal
    }
    
    let salida = horaFinal + ":" + minutoFinal + ":00";
    return salida;
}