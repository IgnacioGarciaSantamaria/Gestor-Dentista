let citas = [
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
];

function sacarFecha() {
    return document.getElementById("fecha-seleccionada").value;
};

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

function getCitaTime(cita) {
    const tratamientos = cita.tratamientos;
    let duracion = {
        horas: 0,
        minutos: 0,
    };
    for (let tratamiento of tratamientos) {
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

function ponerCitas(fecha) {
    for(let cita of citas)
    {
        if(cita.date == fecha){
            const temp = cita.time.split(":");
            let selector = "#time-"+temp[0].toString()+temp[1].toString();
            let duracion = getCitaTime(cita);
            let saltos = duracion.horas + Math.round(duracion.minutos / 30);

            let cliente = cita.nombre + " " +cita.apellidos;
            let tdSelector = `td-${temp[0]}${temp[1]}`;

            let id = cita.id;

            console.log(duracion, saltos);
            
            $(selector).append(`<td class="td-cita" id="${tdSelector}" rowspan="${saltos}">
                <div></div>
            </td>`);
            let height = $(`#${tdSelector}`).height();
            $(`#${tdSelector} > div`).append(`<button style="height: ${height}px;" class="btn-cita" id="${id}" onClick="mostrarInfoCita(${id})">${cliente}</button>`);
        }
    }
    localStorage.setItem('fecha', fecha);
};

function quitarCitas(fecha) {
    for(let cita of citas)
    {
        if(cita.date == fecha){
            const temp = cita.time.split(":");
            let tdSelector = `td-${temp[0]}${temp[1]}`;
            $(`#${tdSelector}`).remove();
        }
    }
};

function mostrarInfoCita(id)
{
    localStorage.setItem('idSelected', id);
    for(let cita of citas)
    {
        if(cita.id == id)
        {
            $("#nombre").html(cita.nombre);
            $("#apellidos").html(cita.apellidos);
            $("#dni").html(cita.dni);
            i=1;
            let tratamientosCita = " ";
            for(let tratamiento of cita.tratamientos)
            {
                if(i < cita.tratamientos.length){
                    tratamientosCita = tratamientosCita + tratamiento.nombre+ " , ";
                } else {
                    tratamientosCita = tratamientosCita + tratamiento.nombre;
                }
                i++;
            }
            $("#tratamientos").html(tratamientosCita);
        }
    }
}

function mostrarInfoCitaEliminar(id)
{
    for(let cita of citas)
    {
        if(cita.id == id)
        {
            $("#nombre-eliminar").html(cita.nombre);
            $("#apellidos-eliminar").html(cita.apellidos);
            $("#dni-eliminar").html(cita.dni);
            i=1;
            let tratamientosCita = " ";
            for(let tratamiento of cita.tratamientos)
            {
                if(i < cita.tratamientos.length){
                    tratamientosCita = tratamientosCita + tratamiento.nombre+ " , ";
                } else {
                    tratamientosCita = tratamientosCita + tratamiento.nombre;
                }
                i++;
            }
            $("#tratamientos-eliminar").html(tratamientosCita);
        }
    }
}

function borrarCita(id)
{
    for(let cita of citas)
    {

        if(cita.id == id)
        {
            citas.filter(cita);
        }
    }
}

document.getElementById("fecha-seleccionada").addEventListener('change', updateValue)

function updateValue(e) {
    quitarCitas(localStorage.getItem('fecha'));
    ponerCitas(sacarFecha());
}

$(document).ready(() => {
    createCitasTable();
    ponerCitas(sacarFecha());
    mostrarInfoCitaEliminar(localStorage.getItem('idSelected'));
});