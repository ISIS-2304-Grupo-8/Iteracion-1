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


document.addEventListener("DOMContentLoaded", function () {
    document.querySelector('.btn.btn-secondary.goback').addEventListener('click', function() {
      window.location.href = "/";
    });
  });

function fetchAndDisplayServices(page, size) {
    const f_in = document.getElementById('f_in').value;
    const f_fin = document.getElementById('f_fin').value;

    if(f_in == '' || f_fin == ''){
        alert("Debe ingresar ambas fechas");
        return;
    }
    const url = `/servicios/20_populares?page=${page}&size=${size}&f_in=${f_in}&f_fin=${f_fin}`;

    fetch(url)
    .then(response => response.json())
    .then(data => {
        console.log(data);
        const servicesTableBody = document.getElementById('servicesTableBody');
        servicesTableBody.innerHTML = ''; // Limpiar el contenido anterior

        data.forEach((serv) => {
            const servRow = `
                <tr>
                    <td>${serv.id_SERVICIO}</td>
                    <td>${serv.veces_CONSUMIDO}</td>
                </tr>
            `;
            servicesTableBody.insertAdjacentHTML('beforeend', servRow);
        });

        // Lógica de paginación
        const paginationControls = document.getElementById('paginationControls');
        const totalPages = data.totalPages;

        // Limpiar controles de paginación existentes
        paginationControls.innerHTML = `
            <li class="page-item"><a class="page-link" href="#" id="previousPage">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#" id="nextPage">Next</a></li>
        `;

        // Insertar números de página
        for (let i = 0; i < totalPages; i++) {
            const pageItem = document.createElement('li');
            pageItem.className = 'page-item';
            pageItem.innerHTML = `<a class="page-link" href="#" data-page="${i}">${i + 1}</a>`;
            paginationControls.insertBefore(pageItem, document.getElementById('nextPage').parentNode);
        }

        // Deshabilitar los botones anterior/siguiente según sea necesario
        document.getElementById('previousPage').parentNode.classList.toggle('disabled', page === 0);
        document.getElementById('nextPage').parentNode.classList.toggle('disabled', page === totalPages - 1);

        currentPage = page; // Actualizar página actual
    })
    .catch(error => {
        console.error('Error fetching users:', error);
    });


}




  function fetchAndDisplayMoney(page = 0, size = 10) {
    fetch(`/habitaciones/dinero_recolectado?page=${page}&size=${size}`)
        .then(response => response.json())
        .then(data => {
            const moneyTableBody = document.getElementById('moneyTableBody');
            moneyTableBody.innerHTML = ''; // Limpiar el contenido anterior

            data.forEach((hab) => {
                const habRow = `
                    <tr>
                        <td>${hab.id_HABITACION}</td>
                        <td>${hab.dinero_RECOLECTADO}</td>
                    </tr>
                `;
                moneyTableBody.insertAdjacentHTML('beforeend', habRow);
            });

            // Lógica de paginación
            const paginationControls = document.getElementById('paginationControls');
            const totalPages = data.totalPages;

            // Limpiar controles de paginación existentes
            paginationControls.innerHTML = `
                <li class="page-item"><a class="page-link" href="#" id="previousPage">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#" id="nextPage">Next</a></li>
            `;

            // Insertar números de página
            for (let i = 0; i < totalPages; i++) {
                const pageItem = document.createElement('li');
                pageItem.className = 'page-item';
                pageItem.innerHTML = `<a class="page-link" href="#" data-page="${i}">${i + 1}</a>`;
                paginationControls.insertBefore(pageItem, document.getElementById('nextPage').parentNode);
            }

            // Deshabilitar los botones anterior/siguiente según sea necesario
            document.getElementById('previousPage').parentNode.classList.toggle('disabled', page === 0);
            document.getElementById('nextPage').parentNode.classList.toggle('disabled', page === totalPages - 1);

            currentPage = page; // Actualizar página actual
        })
        .catch(error => {
            console.error('Error fetching users:', error);
        });
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
}

function prevPage() {
    currentPage = Math.max(0, currentPage - size); // No permitir páginas negativas.
    fetchAndDisplayClients(currentPage, size);
}







// Agregar controladores de eventos para los botones de paginación
document.addEventListener("DOMContentLoaded", function() {

    document.querySelector('.btn.btn-secondary.goback').addEventListener('click', () => {
        window.location.href = "/";
    });

    // Cargar los datos iniciales.
    fetchAndDisplayClients(currentPage, size);

    // Asignar eventos a los botones de paginación.
    document.querySelector('.page-link[href="#prev"]').addEventListener("click", prevPage);
    document.querySelector('.page-link[href="#next"]').addEventListener("click", nextPage);

    // Asignar evento al selector de cantidad de registros por página.
    document.getElementById('recordsPerPage').addEventListener('change', updateRecordsPerPage);
    
    // document.getElementById('paginationControls').addEventListener('click', function(event) {
    //     if (event.target.tagName === 'A' && !event.target.parentNode.classList.contains('disabled')) {
    //         const clickedPage = event.target.getAttribute('data-page');

    //         if (clickedPage !== null) {
    //             fetchAndDisplayMoney(parseInt(clickedPage));
    //         } else if (event.target.id === 'previousPage') {
    //             fetchAndDisplayMoney(currentPage - 1);
    //         } else if (event.target.id === 'nextPage') {
    //             fetchAndDisplayMoney(currentPage + 1);
    //         }

    //         event.preventDefault();
    //     }
    // });

    // document.getElementById('paginationControlsServices').addEventListener('click', function(event) {
    //     if (event.target.tagName === 'A' && !event.target.parentNode.classList.contains('disabled')) {
    //         const clickedPage = event.target.getAttribute('data-page');

    //         if (clickedPage !== null) {
    //             fetchAndDisplayServices(parseInt(clickedPage));
    //         } else if (event.target.id === 'previousPage') {
    //             fetchAndDisplayServices(currentPage - 1);
    //         } else if (event.target.id === 'nextPage') {
    //             fetchAndDisplayServices(currentPage + 1);
    //         }

    //         event.preventDefault();
    //     }
    // });

    // document.getElementById('paginationControlsGoodClients').addEventListener('click', function(event) {
    //     if (event.target.tagName === 'A' && !event.target.parentNode.classList.contains('disabled')) {
    //         const clickedPage = event.target.getAttribute('data-page');

    //         if (clickedPage !== null) {
    //             fetchAndDisplayClients(parseInt(clickedPage));
    //         } else if (event.target.id === 'previousPage') {
    //             fetchAndDisplayClients(currentPage - 1);
    //         } else if (event.target.id === 'nextPage') {
    //             fetchAndDisplayClients(currentPage + 1);
    //         }

    //         event.preventDefault();
    //     }
    // });
}); 
