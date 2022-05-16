let diccTratamientos = new Map();
let diccTratamientosID = new Map();
let idEditar;

const vistaInicio = () => {
    let fotoEspera = document.getElementById("foto-espera");
    fotoEspera.className = "";

    let verTratamiento = document.getElementById("ver-tratamiento");
    verTratamiento.className = "invisible";

    let tratamientoNuevo = document.getElementById("tratamiento-nuevo");
    tratamientoNuevo.className = "invisible";
}

const vistaVerTratamiento = () => {
    let fotoEspera = document.getElementById("foto-espera");
    fotoEspera.className = "invisible";

    let verTratamiento = document.getElementById("ver-tratamiento");
    verTratamiento.className = "";

    let tratamientoNuevo = document.getElementById("tratamiento-nuevo");
    tratamientoNuevo.className = "invisible";    
}

const vistaEditarTratamiento = () => {
    let fotoEspera = document.getElementById("foto-espera");
    fotoEspera.className = "invisible";

    let verTratamiento = document.getElementById("ver-tratamiento");
    verTratamiento.className = "";

    let tratamientoNuevo = document.getElementById("tratamiento-nuevo");
    tratamientoNuevo.className = "";

    let btnEditar = document.getElementById("btn-editar-trat");
    btnEditar.className = "btn btn-primary";

    let btnNuevo = document.getElementById("nuevo-trat");
    btnNuevo.className = "invisible";
}

const vistaNuevoTratamiento = () =>{
    let fotoEspera = document.getElementById("foto-espera");
    fotoEspera.className = "invisible";

    let verTratamiento = document.getElementById("ver-tratamiento");
    verTratamiento.className = "invisible";

    let tratamientoNuevo = document.getElementById("tratamiento-nuevo");
    tratamientoNuevo.className = "";

    let btnEditar = document.getElementById("btn-editar-trat");
    btnEditar.className = "invisible";

    let btnNuevo = document.getElementById("nuevo-trat");
    btnNuevo.className = "btn btn-primary";
}



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

const listadoTratamientos = async () => {
    let tratamientos = await obtenerTratamientos();
    for(let i=0;tratamientos.length;i++){
        let tratamiento = tratamientos[i];
        diccTratamientos.set(tratamiento.id,tratamiento.nombre);
        diccTratamientosID.set(tratamiento.nombre,tratamiento.id);
        //Inserto botón tratamiento
        const divBotonTratamiento = document.createElement("div");
        divBotonTratamiento.innerHTML = "<button type='button' onclick='verTratamiento("+ tratamiento.id +")' id='id-"+ tratamiento.id +"-trat' class='btns-tratamientos'>" + tratamiento.nombre + "</button><br><hr>";
        const divBotonesTratamientos = document.getElementById("botones-tratamientos");
        divBotonesTratamientos.insertAdjacentElement("beforeend",divBotonTratamiento);
        //Inserto botón eliminar
        const divBotonEliminar = document.createElement("div");
        divBotonEliminar.innerHTML = "<button type='button' onclick='eliminarTratamiento(" + tratamiento.id +")' id='elim-"+ tratamiento.id +"-trat' class='btns-eliminar'>Eliminar</button><br><hr>";
        const divEliminarTratamientos = document.getElementById("eliminar-tratamientos");
        divEliminarTratamientos.insertAdjacentElement("beforeend",divBotonEliminar);
        //Inserto botón editar
        const divBotonEditar = document.createElement("div");
        divBotonEditar.innerHTML = "<button type='button' onclick='editar(" + tratamiento.id + ")' id='edit-"+ tratamiento.id + "-trat' class='btns-editar'>Editar</button><br><hr>";
        const divEditarTratamientos = document.getElementById("editar-tratamientos");
        divEditarTratamientos.insertAdjacentElement("beforeend",divBotonEditar);
    }
}

listadoTratamientos();


const nuevoTratamiento = () =>{
    vistaNuevoTratamiento();
}

const generarTratamiento = async () => {
    if(document.getElementById("nombreTratamiento").value == ""){
        alert("Debe insertar el nombre del tratamiento");
    }
    else if(document.getElementById("precioTratamiento").value == ""){
        alert("Debe insertar un número superior a cero");
    }
    else if(document.getElementById("descripcionTratamiento").value == ""){
        alert("Debe insertar una descripción");
    }
    else{
        let request = await fetch("/api/v1/tratamientos", {
            method: "POST",
            body: JSON.stringify({
                id: 1,
                nombre: document.getElementById("nombreTratamiento").value,
                precio: parseInt(document.getElementById("precioTratamiento").value),
                duracion: document.getElementById("seleccionHora").value,
                descripcion: document.getElementById("descripcionTratamiento").value
            }),
            headers: {
                "Content-Type": "application/json",
            },
            dataType: "json",
        });
    
        if(request.status === 200){
            alert("Tratamiento Creado Correctamente");
            window.location.href="./Tratamientos.html";
        }else{
            alert("No hemos podido subir la información proporcionada al servidor de clientes");
        }
    }
    return false;
}

const verTratamiento = async (id) =>{
    vistaVerTratamiento();
    let request = await fetch("api/v1/tratamientos/" + id + "/");
    if(request.status === 200){
        data = await request.json();
        tratamiento = data;
        document.getElementById("tratamiento-nombre").textContent = tratamiento.nombre;
        document.getElementById("tratamiento-precio").textContent = tratamiento.precio;
        document.getElementById("tratamiento-descripcion").textContent = tratamiento.descripcion;
        document.getElementById("tratamiento-duracion").textContent = tratamiento.duracion;
    }else{
        alert("Error al conectarse al servidor de tratamientos");
    }
}

const verTratamientoBuscador = () =>{
    let id = null;
    let nombreTratamiento = document.getElementById("trat-Buscador").value;
    id = diccTratamientosID.get(nombreTratamiento);
    if(id == null){
        alert("Nombre del Tratamiento No encontrado");
    }else{
        verTratamiento(id);
    }
}

const editar = (id) =>{
    verTratamiento(id);
    vistaEditarTratamiento();
    idEditar = id;
}

const editarTratamiento = async () => {
    if(document.getElementById("nombreTratamiento").value == ""){
        alert("Debe insertar el nombre del tratamiento");
    }
    else if(document.getElementById("precioTratamiento").value == ""){
        alert("Debe insertar un número superior a cero");
    }
    else if(document.getElementById("descripcionTratamiento").value == ""){
        alert("Debe insertar una descripción");
    }
    else {
        let request = await fetch("/api/v1/tratamientos/" + idEditar + "/", {
            method: "PUT",
            body: JSON.stringify({
                id: idEditar,
                nombre: document.getElementById("nombreTratamiento").value,
                precio: parseInt(document.getElementById("precioTratamiento").value),
                duracion: document.getElementById("seleccionHora").value,
                descripcion: document.getElementById("descripcionTratamiento").value
            }),
            headers: {
                "Content-Type": "application/json",
            },
            dataType: "json",
        });
    
        if(request.status === 200){
            alert("Tratamiento Modificado Correctamente");
            window.location.href="./Tratamientos.html";
        }else{
            alert("No hemos podido modificar la información proporcionada al servidor de clientes");
        }
    }
}


const eliminarTratamiento = async (id) =>{
    let request = await fetch("api/v1/tratamientos/" + id + "/", {
        method: "DELETE",
        credentials: "same-origin",
    });
    if(request.status === 204){
        let nombre = diccTratamientos.get(parseInt(id));
        let mensaje = "Se ha eliminado el tratamiento: " + nombre;
        alert(mensaje);
        window.location.href="./Tratamientos.html";
    }
    else{
        alert("No hemos podido conectar con el servidor para eliminar el tratamiento");
    }
}



vistaInicio();


