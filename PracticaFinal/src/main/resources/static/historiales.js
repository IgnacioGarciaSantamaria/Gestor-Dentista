const getClientes = async () => {
    let clientes;
    let request = await fetch("api/v1/clientes");
    if(request.status === 200)
    {
        data = await request.json();
        clientes = data;
    }
    else {
        alert("Error al conectarse al servidor de clientes");
    }
    return clientes
}

const getHistoriales = async () => {
    let historiales;
    let request = await fetch("api/v1/historiales");
    if(request.status === 200){
        data = await request.json();
        historiales = data;
    } else {
        alert("Error al conectarse al servidor de historiales");
    }
    return historiales;
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

let clientesPuestos = [];

async function pintarHistoriales() {
    let clientes = await getClientes();
    let historiales = await getHistoriales();
    for(let historial of historiales)
    {
        for(let cliente of clientes)
        {
            if(cliente.dni == historial.dni)
            {
                let nombreCompleto = cliente.nombre + " " + cliente.apellidos;
                if(clientesPuestos.includes(cliente.id) == false)
                {
                    $("#btns-historiales").append(`<div class="row"><button class="btn-historial" id="${cliente.dni}" onClick="mostrarHistorial('${cliente.dni}')">${nombreCompleto}</button></div>`);
                }
                clientesPuestos.push(cliente.id);
            }
        }
    }
    $("#historial-seleccionado").hide();
}

async function mostrarHistorial(dni)
{
    $("#foto-espera-img").hide();
    $("#historial-seleccionado").show();
    let clientes = await getClientes();
    let historiales = await getHistoriales();
    let tratamientos = await getTratamientos();

    console.log(tratamientos);
    console.log(clientes);
    console.log(historiales);

    console.log(dni);
    for(let cliente of clientes)
    {
        if(cliente.dni == dni)
        {
            let nombreCompleto = cliente.nombre + " " + cliente.apellidos;
            localStorage.setItem("nombreCompleto", nombreCompleto);
            localStorage.setItem("dni", dni);
            console.log(nombreCompleto);
            $("#info-cliente-seleccionado").empty();
            $("#tabla-historial").empty();
            $("#info-cliente-seleccionado").append(`${nombreCompleto}, (${cliente.dni})`);
            for(let historial of historiales)
            {
                if(historial.dni == dni)
                {
                    for(let tratamiento of tratamientos)
                    {
                        if(tratamiento.id == historial.idTratamiento)
                        {
                            $("#tabla-historial").append(`
                                <tr id="tratamiento-${tratamiento.id}">
                                    <th class="datos">${tratamiento.nombre}</th>
                                    <td class="datos">${historial.date}</td>
                                </tr>
                            `);
                        }
                    }
                }
            }
        }
    }
}

const buscarHistorial = async (dni) => {
    let clientes = await getClientes();
    let a = 0;
    for(let cliente of clientes)
    {
        if(cliente.dni == dni)
        {
            a = 1;
        }
    }
    if(a === 1)
    {
        mostrarHistorial(dni);
    } else {
        alert("El DNI introducido no se encuentra en la base de datos de clientes");
    }
}

$(document).ready(() => {
    pintarHistoriales();
});