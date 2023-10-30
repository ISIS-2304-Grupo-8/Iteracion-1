// Variables globales para la paginación.
let currentPage = 0;
let size = 5; // Cantidad de habitaciones a mostrar por página.

// Función para actualizar la cantidad de registros por página y recargar los datos.
function updateRecordsPerPage(event) {
    size = parseInt(event.target.value);
    currentPage = 0; // Reiniciar la paginación
    loadData(currentPage, size);
}

/**
 * Función para mostrar los datos de habitaciones en una tabla dentro de la card.
 * @param {Array} habitaciones - Lista de habitaciones a mostrar.
 */
function displayData(habitaciones) {
    const container = document.querySelector('.card-body');
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

    habitaciones.forEach(habitacion => {
        tableContent += `
            <tr>
                <td>${habitacion.id_HABITACION}</td>
                <td>${habitacion.total_DIAS_OCUPADOS}</td>
                <td>${habitacion.porcentaje_OCUPACION}%</td>
            </tr>
        `;
    });

    tableContent += `
            </tbody>
        </table>
    `;

    container.innerHTML = tableContent;
}

/**
 * Función para habilitar o deshabilitar los botones de paginación según sea necesario.
 * @param {Object} data - Datos de paginación del backend.
 */
function handlePaginationButtons(data) {
    const prevBtnLi = document.querySelector('.page-link[href="#prev"]').parentNode;
    const nextBtnLi = document.querySelector('.page-link[href="#next"]').parentNode;

    if(prevBtnLi && nextBtnLi) {
        // Si es la primera página, deshabilita el botón "Anterior"
        if (data.first) {
            prevBtnLi.classList.add('disabled');
        } else {
            prevBtnLi.classList.remove('disabled');
        }
        // Si es la última página, deshabilita el botón "Siguiente"
        if (data.last) {
            nextBtnLi.classList.add('disabled');
        } else {
            nextBtnLi.classList.remove('disabled');
        }
        }
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

// Función para cargar la siguiente página de datos.
function nextPage() {
    currentPage = currentPage + size;
    loadData(currentPage, size);
}

// Función para cargar la página anterior de datos.
function prevPage() {
    if(currentPage - size < 0){
        currentPage = 0;
    }
    else{
        currentPage = currentPage - size;
    }
    loadData(currentPage, size);
}

document.addEventListener("DOMContentLoaded", function () {
    document.querySelector('.btn.btn-secondary.goback').addEventListener('click', function() {
      window.location.href = "/";
    });

    // Cargamos los datos iniciales.
    loadData(currentPage, size);

    // Asociar eventos a los botones de paginación
    const prevLink = document.querySelector('.page-link[href="#prev"]');
    const nextLink = document.querySelector('.page-link[href="#next"]');

    if(prevLink && nextLink) {
        prevLink.addEventListener("click", prevPage);
        nextLink.addEventListener("click", nextPage);
    }

     // Asociar evento al desplegable para la cantidad de registros por página
     document.getElementById('recordsPerPage').addEventListener('change', updateRecordsPerPage);
  });