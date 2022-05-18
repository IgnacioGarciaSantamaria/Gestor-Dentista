let diccTratamientos = new Map();

const insertarInfo = () =>{
    let info = document.getElementById("info-cliente-nueva-cita");
    info.textContent = localStorage.getItem('nombreCompleto') + " (" + localStorage.getItem('dniSelected') + ')';
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
    let horas = [];
    let request = await fetch("api/v1/historiales/?fecha="+fecha);
    if(request.status === 200){
        data = await request.json();
        for(let i in data){
            horas.push(data[i].time);
        }
    }else{
        alert("Error al obtener las horas de la base de datos");
    }
    return horas;
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
        let horasNoSeleccionables;
        horasNoSeleccionables = await obtenerHorasFecha(fechaActual);
        let seleccion = document.getElementById("seleccionHora");
        for(let i=0; i < seleccion.options.length;i++){
            let pro = seleccion.options[i];
            for(let j=0; j < horasNoSeleccionables.length;j++){
                if(pro.value == horasNoSeleccionables[j]){
                    pro.parentNode.removeChild(pro);
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
            dni: localStorage.getItem('dniSelected'),
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
        window.location.href="./historiales.html";
    }else{
        alert("No hemos podido generar la cita adecuadamente");
    }
}