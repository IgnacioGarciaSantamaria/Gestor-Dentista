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
        alert("No hemos podido generar la cita adecuadamente");
    }
}