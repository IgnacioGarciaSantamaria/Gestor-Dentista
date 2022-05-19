const validarCita = async () => {
    let dni = document.getElementById("dniExistente").value;

    let request = await fetch("/api/v1/clientes/" + dni + "/");
    if(request.status === 200){
        data = await request.json();
        cliente = data;
        localStorage.setItem('dni',cliente.dni);
        localStorage.setItem('nombre',cliente.nombre);
        localStorage.setItem('apellidos',cliente.apellidos);
        window.location.href="./nuevaCita.html";
    }else{
        alert("Error, DNI no resgistrado en la Base de Datos");
    }
}

console.log("hola");

