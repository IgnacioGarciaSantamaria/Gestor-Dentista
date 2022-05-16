/*let citas = [
    {
        id : '1',
        nombre : 'Jaime',
        apellidos : 'de Clemente',
        dni : '02568420X',
        date : '2001-07-16',
        time : '9:30',
        tratamientos : [{
            id : '1',
            nombre : 'Endodoncia',
            precio : '100',
            duracion : '3:00'
        }, {
            id : '2',
            nombre : 'Blanqueamiento',
            precio : '150',
            duracion : '1:30'
        }]
    }, {
        id : '2',
        nombre : 'Ignacio',
        apellidos : 'García',
        dni : '02568520X',
        date : '2001-07-15',
        time : '13:30',
        tratamientos : [{
            id : '1',
            nombre : 'Revisión caries',
            precio : '100',
            duracion : '1:00'
        }, {
            id : '2',
            nombre : 'Limpieza',
            precio : '150',
            duracion : '1:30'
        }]
    }
];*/

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

function sacarFecha() {
    return document.getElementById("fecha-seleccionada").value;
}

function createCitasTable() {
    for (let i = 8; i < 20; i++) {
        for (let j = 0; j < 2; j++) {
            let min = j == 0 ? "00" : "30";
            $("#citas-table").append(`
                <tr id="time-${i}${min}">
                    <th>${i}:${min}</th>
                </tr>
            `);
        }
    }
}

async function getCitaTime(dni) {
    let citas = await getCitas();
    let tratamientos = await getTratamientos();
    let tratamientosCita = [];
    for(let cita of citas)
    {
        if(cita.dni == dni && cita.date == sacarFecha())
        {
            for(let tratamiento of tratamientos)
            {
                if(tratamiento.id == cita.tratamientoId)
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

async function ponerCitas(fecha) {
    let citas = await getCitas();
    for(let cita of citas)
    {
        if(cita.date == fecha){
            const temp = cita.time.split(":");
            let selector = "#time-"+temp[0].toString()+temp[1].toString();
            let duracion = getCitaTime(cita);
            let saltos = duracion.horas + Math.round(duracion.minutos / 30);

            let cliente = cita.nombre + " " +cita.apellidos;
            let tdSelector = `td-${temp[0]}${temp[1]}`;

            let dni = cita.dni;

            console.log(duracion, saltos);
            
            $(selector).append(`<td class="td-cita" id="${tdSelector}" rowspan="${saltos}">
                <div></div>
            </td>`);
            let height = $(`#${tdSelector}`).height();
            $(`#${tdSelector} > div`).append(`<button style="height: ${height}px;" class="btn-cita" id="${dni}" onClick="mostrarInfoCita(${dni})">${cliente}</button>`);
        }
    }
    localStorage.setItem('fecha', fecha);
};

async function quitarCitas(fecha) {
    let citas = await getCitas();
    for(let cita of citas)
    {
        if(cita.date == fecha){
            const temp = cita.time.split(":");
            let tdSelector = `td-${temp[0]}${temp[1]}`;
            $(`#${tdSelector}`).remove();
        }
    }
};

async function mostrarInfoCita(dni)
{
    localStorage.setItem('dniSelected', dni);
    let citas = await getCitas();
    let tratamientos = await getTratamientos();
    let tratamientosCita = [];
    for(let cita of citas)
    {
        if(cita.dni == dni && cita.date == sacarFecha())
        {
            for(let tratamiento of tratamientos)
            {
                if(tratamiento.id == cita.tratamientoId)
                {
                    tratamientosCita.push(tratamiento);
                }
            }
        }
    }
    console.log(tratamientosCita);
    console.log(tratamientosCita.length);
    for(let cita of citas)
    {
        if(cita.dni == dni)
        {
            $("#nombre").html(cita.nombre);
            $("#apellidos").html(cita.apellidos);
            $("#dni").html(cita.dni);
            i=1;
            let tratamientosEscribir = " ";
            for(let tratamiento of tratamientosCita)
            {
                if(i < tratamientosCita.length){
                    tratamientosEscribir = tratamientosEscribir + tratamiento.nombre+ " , ";
                } else {
                    tratamientosEscribir = tratamientosEscribir + tratamiento.nombre;
                }
                i++;
            }
            $("#tratamientos").html(tratamientosEscribir);
        }
    }
}

let diccTratamientos = new Map();

const presentarTratamientos = async () => {
    let tratamientos = await getTratamientos();
    let tratamiento;
    let desplegable = document.getElementById("seleccionTratamiento");
    for(let tratamiento of tratamientos){
        diccTratamientos.set(tratamiento.nombre,tratamiento.id);
        let option = document.createElement("option");
        option.value = tratamiento.nombre;
        option.innerHTML = tratamiento.nombre;
        option.selected = "selected";
        desplegable.insertAdjacentElement("afterbegin",option);
    }
}

presentarTratamientos();

/*function anadirTratamiento() {
    let tratamiento = $("#seleccionTratamiento").val();
    let tratamientosActuales = $("#tratamientos").val();
    if(tratamientosActuales != null)
    {
        for(let cita of citas)
        {
            if(localStorage.getItem('idSelected') == cita.id)
            {
                cita.tratamientos.push(tratamiento);
            }
        }
        mostrarInfoCita(localStorage.getItem('idSelected'));
        ponerCitas(sacarFecha());
    }
}*/

document.getElementById("fecha-seleccionada").addEventListener('change', updateValue)

function updateValue(e) {
    quitarCitas(localStorage.getItem('fecha'));
    ponerCitas(sacarFecha());
}

$(document).ready(() => {
    createCitasTable();
    ponerCitas(sacarFecha());
});