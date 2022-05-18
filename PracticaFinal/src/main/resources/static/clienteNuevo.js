const cambiarVista = () => {
    let tiempoFoto = document.getElementById("tiempo-foto");
    tiempoFoto.className = "invisible";
    let infoCliente = document.getElementById("info-cliente");
    infoCliente.className = "";
}

const vueltaVista = () =>{
    let tiempoFoto = document.getElementById("tiempo-foto");
    tiempoFoto.className = "";
    let infoCliente = document.getElementById("info-cliente");
    infoCliente.className = "invisible";
} 


const mostrarInfo = () => {
    let nombreCliente = document.getElementById("nombreCliente").value;
    document.getElementById("info-nombre").textContent = nombreCliente;

    let apellidosCliente = document.getElementById("apellidosCliente").value;
    document.getElementById("info-apellidos").textContent = apellidosCliente;

    let dniCliente = document.getElementById("DNICliente").value;
    document.getElementById("info-DNI").textContent = dniCliente;

    let telefonoCliente = document.getElementById("telefonoCliente").value;
    document.getElementById("info-telefono").textContent = telefonoCliente;

    let emailCliente = document.getElementById("emailCliente").value;
    document.getElementById("info-email").textContent = emailCliente;
} 



const insertarUsuario = () => {
    cambiarVista();

    mostrarInfo();

    return (false);
}

const postInfo = async () =>{
    let request = await fetch("/api/v1/clientes", {
        method: "POST",
        body: JSON.stringify({
            id: 1,
            dni: document.getElementById("info-DNI").textContent,
            nombre: document.getElementById("info-nombre").textContent,
            apellidos: document.getElementById("info-apellidos").textContent,
            telefono: document.getElementById("info-telefono").textContent,
            correo: document.getElementById("info-email").textContent
        }),
        headers: {
            "Content-Type": "application/json",
        },
        dataType: "json",
    });

    if(request.status === 200){
        document.getElementById("btn-reset-cliente").click();
        localStorage.setItem('dni',document.getElementById("info-DNI").textContent);
        localStorage.setItem('nombre', document.getElementById("info-nombre").textContent);
        localStorage.setItem('apellidos', document.getElementById("info-apellidos").textContent);    
        window.location.href="./nuevaCita.html";
    }else{
        alert("No hemos podido subir la información proporcionada al servidor de clientes, asegúrese de que el DNI y el teléfono sean válidos");
    }
}

const enviarInfo = () =>{

    postInfo();
}


console.log("nhola");







