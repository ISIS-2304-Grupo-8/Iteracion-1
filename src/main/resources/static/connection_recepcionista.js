// Variables globales para la paginación.
let currentPage = 0;
let size = 5; // Cantidad de habitaciones a mostrar por página.

/**
 * Función para actualizar la cantidad de registros por página y recargar los datos.
 * @param {Event} event - Evento del cambio en el selector de registros por página.
 */
function updateRecordsPerPage(event) {
    size = parseInt(event.target.value);
    currentPage = 0; // Reiniciar la paginación.
    loadData(currentPage, size);
}

/**
 * Función para mostrar los datos de habitaciones en una tabla.
 * @param {Array} habitaciones - Lista de habitaciones a mostrar.
 */
function displayData(habitaciones) {
    const container = document.querySelector('.card-body.modRF3');
    
    // Generar contenido para la tabla.
    let tableContent = `
        <table class="table table-striped table-dark">
            <thead>
                <tr>
                    <th scope="col">ID Habitación</th>
                    <th scope="col">Días Ocupados</th>
                    <th scope="col">Porcentaje de Ocupación</th>
                </tr>
            </thead>
            <tbody>
    `;

    // Iterar sobre las habitaciones para añadir filas a la tabla.
    habitaciones.forEach(habitacion => {
        tableContent += `
            <tr>
                <td>${habitacion.id_HABITACION}</td>
                <td>${habitacion.total_DIAS_OCUPADOS}</td>
                <td>${habitacion.porcentaje_OCUPACION}%</td>
            </tr>
        `;
    });

    tableContent += `</tbody></table>`;
    container.innerHTML = tableContent;
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

/**
 * Función para cargar los datos de habitaciones desde el backend.
 * @param {number} page - Página actual.
 * @param {number} size - Cantidad de registros por página.
 */
function loadData(page, size) { 
    fetch(`/habitaciones/indice_ocupacion?size=${size}&offset=${page}`)
        .then(response => response.json())
        .then(data => {
            displayData(data);
            handlePaginationButtons(data);
        })
        .catch(error => console.error('Error al cargar datos:', error));
}

/**
 * Función para cargar la siguiente página de datos.
 */
function nextPage() {
    currentPage += size;
    loadData(currentPage, size);
}

/**
 * Función para cargar la página anterior de datos.
 */
function prevPage() {
    currentPage = Math.max(0, currentPage - size); // No permitir páginas negativas.
    loadData(currentPage, size);
}

// Evento cuando el documento esté completamente cargado.
document.addEventListener("DOMContentLoaded", function () {
    // Asignar evento al botón de retorno.
    document.querySelector('.btn.btn-secondary.goback').addEventListener('click', () => {
        window.location.href = "/";
    });

    // Cargar los datos iniciales.
    loadData(currentPage, size);

    // Asignar eventos a los botones de paginación.
    document.querySelector('.page-link[href="#prev"]').addEventListener("click", prevPage);
    document.querySelector('.page-link[href="#next"]').addEventListener("click", nextPage);

    // Asignar evento al selector de cantidad de registros por página.
    document.getElementById('recordsPerPage').addEventListener('change', updateRecordsPerPage);
});
