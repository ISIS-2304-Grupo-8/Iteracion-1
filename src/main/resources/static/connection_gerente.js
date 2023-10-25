let currentPage = 0; // Página actual. Empieza en 0.    

document.addEventListener("DOMContentLoaded", function () {
    document.querySelector('.btn.btn-secondary.goback').addEventListener('click', function() {
      window.location.href = "/";
    });
  });

function fetchAndDisplayServices(page = 0, size = 10) {
    const f_in = document.getElementById('f_in').value;
    const f_fin = document.getElementById('f_fin').value;
    const f_inDate = new Date(f_in);
    const f_finDate = new Date(f_fin);
    const f_inDateFormatted = f_inDate.toISOString().split('T')[0];
    const f_finDateFormatted = f_finDate.toISOString().split('T')[0];

    const url = `/servicios/20_populares?page=${page}&size=${size}&f_in=${f_inDateFormatted}&f_fin=${f_finDateFormatted}`;

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

  // Agregar controladores de eventos para los botones de paginación
document.addEventListener("DOMContentLoaded", function() {
    
    document.getElementById('paginationControls').addEventListener('click', function(event) {
        if (event.target.tagName === 'A' && !event.target.parentNode.classList.contains('disabled')) {
            const clickedPage = event.target.getAttribute('data-page');

            if (clickedPage !== null) {
                fetchAndDisplayMoney(parseInt(clickedPage));
            } else if (event.target.id === 'previousPage') {
                fetchAndDisplayMoney(currentPage - 1);
            } else if (event.target.id === 'nextPage') {
                fetchAndDisplayMoney(currentPage + 1);
            }

            event.preventDefault();
        }
    });

    document.getElementById('paginationControlsServices').addEventListener('click', function(event) {
        if (event.target.tagName === 'A' && !event.target.parentNode.classList.contains('disabled')) {
            const clickedPage = event.target.getAttribute('data-page');

            if (clickedPage !== null) {
                fetchAndDisplayServices(parseInt(clickedPage));
            } else if (event.target.id === 'previousPage') {
                fetchAndDisplayServices(currentPage - 1);
            } else if (event.target.id === 'nextPage') {
                fetchAndDisplayServices(currentPage + 1);
            }

            event.preventDefault();
        }
    });

    
});
