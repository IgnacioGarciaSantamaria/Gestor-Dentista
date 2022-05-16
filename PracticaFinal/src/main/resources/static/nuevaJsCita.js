let diccTratamientos = new Map();
let horasHistoriales = [];
let idsHistoriales = [];

const insertarInfo = () =>{
    let info = document.getElementById("info-cliente-nueva-cita");
    info.textContent = localStorage.getItem('nombre') + " " + localStorage.getItem('apellidos') + " (" + localStorage.getItem('dni') + ')';
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


const presentarTratamientos = async () => {
    let tratamiento;
    let desplegable = document.getElementById("seleccionTratamiento");
    let tratamientos = await obtenerTratamientos();
    for(let i=0; i < tratamientos.length; i++){
        tratamiento = tratamientos[i];
        diccTratamientos.set(tratamiento.nombre,tratamiento.id);
        let option = document.createElement("option");
        option.value = tratamiento.nombre;
        option.innerHTML = tratamiento.nombre;
        option.selected = "selected";
        desplegable.insertAdjacentElement("afterbegin",option);
    }
}

presentarTratamientos();

const cambiarHora = () => {
    let date = new Date();
    let fecha = date.toISOString().split('T')[0];
    fechaCita = document.getElementById("fechas-disponibles");
    fechaCita.value=fecha;
    fechaCita.min=fecha;
}

cambiarHora();


const obtenerFechasBaseDatos = async () =>{
    let fechas = [];
    let request = await fetch("api/v1/historiales");
    if(request.status === 200){
        data = await request.json();
        historiales = data;
        for(let i=0; i < historiales.length; i++){
            fechas.push(historiales[i].date)
        }
    }else{
        alert("Error a la hora de obtener las fechas en la Base de Datos");
    }
    return fechas;
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

const obtenerIdHistoriales = async (fecha) =>{
    idsHistoriales = [];
    let request = await fetch("api/v1/historiales/?fecha="+fecha);
    if(request.status === 200){
        data = await request.json();
        for(let i in data){
            idsHistoriales.push(data[i].idTratamiento);
        }
    }else{
        alert("Error al obtener las horas de la base de datos");
    }
    return idsHistoriales;
}

const obtenerDuracionTratamiento = async (id) => {
    let tratamiento;
    let request = await fetch("api/v1/tratamientos/" + id +"/");
    if(request.status === 200){
        data = await request.json();
        tratamiento = data;
    }else{
        alert("Error a la hora de obtener las fechas en la Base de Datos");
    }
    return tratamiento.duracion;
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

const compararFecha = async () => {
    let flag = 0;
    let fechaActual = document.getElementById("fechas-disponibles").value;
    let fechas = await obtenerFechasBaseDatos();
    for(let i in fechas){
        if(fechas[i].toString() == fechaActual.toString()){
            flag = 1;
        } 
    }
    if(flag){
        //Restricción impuesta por tratamientos ya asignados
        let flag_tiempo = 0;
        let horasNoSeleccionables;
        horasNoSeleccionables = await obtenerHorasFecha(fechaActual);
        let idsNoSeleccionables;
        idsNoSeleccionables = await obtenerIdHistoriales(fechaActual);
        let seleccion = document.getElementById("seleccionHora");
        for(let i=0; i < seleccion.options.length;i++){
            let pro = seleccion.options[i];
            for(let j=0; j < horasNoSeleccionables.length;j++){
                if(pro.value == horasNoSeleccionables[j]){
                    let duracion = await obtenerDuracionTratamiento(idsNoSeleccionables[j]);
                    tiempoInicial = pro.value;
                    horaFinal = sumadorRestadorHoras(1,tiempoInicial,duracion);
                    flag_tiempo = 1;
                }
                if(flag_tiempo == 1){
                    if(horaFinal == pro.value){
                        flag_tiempo = 0;
                    }
                    pro.className = "invisible"; 
                }
            }
        }
        //Restricción impuesta por el tratamiento a asignar
        let flag_tiempo_2 = 0;
        let horasNoSeleccionables_2 = [];
        horasNoSeleccionables = await obtenerHorasFecha(fechaActual);
        let duracion_2 = await obtenerDuracionTratamiento(diccTratamientos.get(document.getElementById("seleccionTratamiento").value));
        horasNoSeleccionables_2[0] = sumadorRestadorHoras(3,horasNoSeleccionables[0],duracion_2);
        console.log(horasNoSeleccionables);
        for(let k=0; k < horasNoSeleccionables.length;k++){
            horasNoSeleccionables_2[k] = sumadorRestadorHoras(3,horasNoSeleccionables[k],duracion_2);
        }
        console.log(horasNoSeleccionables_2);
        let seleccion_2 = document.getElementById("seleccionHora");
        for(let i=0; i < seleccion_2.options.length;i++){
            let pro_2 = seleccion_2.options[i];
            for(let j=0; j < horasNoSeleccionables_2.length;j++){
                if(pro_2.value == horasNoSeleccionables_2[j]){
                    tiempoInicial_2 = pro_2.value;
                    horaFinal_2 = sumadorRestadorHoras(1,tiempoInicial_2,duracion_2);
                    flag_tiempo_2 = 1;
                }
                if(flag_tiempo_2 == 1){
                    if(horaFinal_2 == pro_2.value){
                        flag_tiempo_2 = 0;
                    }
                    pro_2.className = "invisible"; 
                }
            }
        }
    }
}



compararFecha();


const generarCita = async () => {
    let seleccionTratamiento = document.getElementById("seleccionTratamiento");
    let tratamiento = seleccionTratamiento.options[seleccionTratamiento.selectedIndex].value;
    let seleccionHora = document.getElementById("seleccionHora");
    let request = await fetch("api/v1/historiales", {
        method: "POST",
        body: JSON.stringify({
            id: 1,
            dni: localStorage.getItem('dni'),
            idTratamiento: diccTratamientos.get(tratamiento),
            date:  document.getElementById('fechas-disponibles').value,
            time: seleccionHora.options[seleccionHora.selectedIndex].value
        }),
        headers: {
            "Content-Type": "application/json",
        },
        dataType: "json",
    });

    if(request.status === 200){
        alert("Cita generada Correctamente");
        window.location.href="./index.html";
    }else{
        alert("No hemos podido generar la cita adecuadamente");
    }
}
