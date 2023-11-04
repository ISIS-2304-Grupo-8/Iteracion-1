let currentPage = 0; // Página actual. Empieza en 0.  
let size = 5; // Cantidad de habitaciones a mostrar por página.


/**
 * Función para actualizar la cantidad de registros por página y recargar los datos.
 * @param {Event} event - Evento del cambio en el selector de registros por página.
 */
function updateRecordsPerPage(event) {
    size = parseInt(event.target.value);
    currentPage = 0; // Reiniciar la paginación.
    fetchAndDisplayClients(currentPage, size);
    fetchAndDisplayServices(currentPage, size);
    fetchAndDisplayMoney(currentPage, size);
}

/**
 * Función para habilitar o deshabilitar los botones de paginación.
 * @param {Object} data - Datos de paginación del backend.
 */
function handlePaginationButtons(data) {
    const prevBtnLi = document.querySelector('.page-link[href="#prev"]').parentNode;
    const nextBtnLi = document.querySelector('.page-link[href="#next"]').parentNode;

    // Controlar la visibilidad de los botones de paginación.
    if (data.first) prevBtnLi.classList.add('disabled');
    else prevBtnLi.classList.remove('disabled');

    if (data.last) nextBtnLi.classList.add('disabled');
    else nextBtnLi.classList.remove('disabled');
}


function fetchAndDisplayServices(page, size) {
    const startDate = document.getElementById('startDateModRFC2').value;
    const endDate = document.getElementById('endDateModRFC2').value;

    if(startDate!= "" && endDate != ""){
        fetch(`/servicios/20_populares?size=${size}&offset=${page}&f_in=${startDate}&f_fin=${endDate}`)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            displayServices(data);
            handlePaginationButtons(data);
        })
        .catch(error => {
            console.error('Error fetching users:', error);
        });
    }


}

  function fetchAndDisplayMoney(page, size) {
    fetch(`/habitaciones/dinero_recolectado?size=${size}&offset=${page}`)
        .then(response => response.json())
        .then(data => {
            displayMoney(data);
            handlePaginationButtons(data);
        })
        .catch(error => {
            console.error('Error fetching users:', error);
        });
}








//DINERO RECOLECTADO POR SERIVICIOS POR HABITACION
function displayMoney(habitaciones) {
    const container = document.querySelector('.card-body.modRF1');
    
    let tableContent = `
        <table class="table table-striped table-dark">
            <thead>
                <tr>
                    <th scope="col">ID Habitación</th>
                    <th scope="col">Dinero Recolectado</th>
                </tr>
            </thead>
            <tbody>
    `;
    habitaciones.forEach((hab) => {
        tableContent += `
            <tr>
                <td>${hab.id_HABITACION}</td>
                <td>${hab.dinero_RECOLECTADO}</td>
            </tr>
        `;
    });

    tableContent += `</tbody></table>`;
    container.innerHTML = tableContent;
}

//TOP SERVICIOS
function displayServices(services){
    const container = document.querySelector('.card-body.modRF2');

    // Generar contenido para la tabla.
    let tableContent = `
        <table class="table table-striped table-dark">
            <thead>
                <tr>
                    <th scope="col">ID Servicio</th>
                    <th scope="col">Veces Consumido</th>
                </tr>
            </thead>
            <tbody>
    `;
    services.forEach((serv) => {
        tableContent += `
            <tr>
                <td>${serv.tipo_SERVICIO}</td>
                <td>${serv.veces_CONSUMIDO}</td>
            </tr>
        `;
    });

    tableContent += `</tbody></table>`;
    container.innerHTML = tableContent;
}

//BUENOS CLIENTES
function displayGoodClients(clientes){
    const container = document.querySelector('.card-body.modRF7');

    // Generar contenido para la tabla.
    let tableContent = `
        <table class="table table-striped table-dark">
            <thead>
                <tr>
                    <th scope="col">ID Cliente</th>
                    <th scope="col">Días Estadía</th>
                    <th scope="col">Consumo Total</th>
                </tr>
            </thead>
            <tbody>
    `;


    clientes.forEach((cli) => {
        if (cli.dias_ESTADIA == null) cli.dias_ESTADIA = '-';
        if (cli.consumo_TOTAL == null) cli.consumo_TOTAL = '-';
        tableContent += `
            <tr>
                <td>${cli.cedula_CLIENTE}</td>
                <td>${cli.dias_ESTADIA}</td>
                <td>${cli.consumo_TOTAL}</td>
            </tr>
        `;
    });

    tableContent += `</tbody></table>`;
    container.innerHTML = tableContent;
}

function fetchAndDisplayClients(page, size){
    fetch(`/clientes/buenos_clientes?size=${size}&offset=${page}`)
    .then(response => response.json())
    .then(data => {
        displayGoodClients(data);
        handlePaginationButtons(data);
    })
    .catch(error => {
        console.error('Error fetching users:', error);
    });
}

function nextPage() {
    currentPage += size;
    fetchAndDisplayClients(currentPage, size);
    fetchAndDisplayServices(currentPage, size);
    fetchAndDisplayMoney(currentPage, size);
}

function prevPage() {
    currentPage = Math.max(0, currentPage - size); // No permitir páginas negativas.
    fetchAndDisplayClients(currentPage, size);
    fetchAndDisplayServices(currentPage, size);
    fetchAndDisplayMoney(currentPage, size);
}







// Agregar controladores de eventos para los botones de paginación
document.addEventListener("DOMContentLoaded", function() {

    document.querySelector('.btn.btn-secondary.goback').addEventListener('click', () => {
        window.location.href = "/";
    });

    // Cargar los datos iniciales.
    fetchAndDisplayClients(currentPage, size);

    document.getElementById('btnFiltrarTopServicios').addEventListener('click', () => {
        fetchAndDisplayServices(currentPage, size);
    });

    fetchAndDisplayMoney(currentPage, size);

    // Asignar eventos a los botones de paginación.
    document.querySelector('.page-link[href="#prev"]').addEventListener("click", prevPage);
    document.querySelector('.page-link[href="#next"]').addEventListener("click", nextPage);

    document.querySelector('.page-link[href="#prevRFC2"]').addEventListener("click", prevPage);
    document.querySelector('.page-link[href="#nextRFC2"]').addEventListener("click", nextPage);

    // Asignar evento al selector de cantidad de registros por página.
    document.getElementById('recordsPerPage').addEventListener('change', updateRecordsPerPage);
    document.getElementById('recordsPerPageRFC2').addEventListener('change', updateRecordsPerPage);

}); 
