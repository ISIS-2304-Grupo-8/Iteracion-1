document.addEventListener("DOMContentLoaded", function () {
    document.querySelector('.btn.btn-secondary.goback').addEventListener('click', function() {
      window.location.href = "/";
    });
  });

  function fetchAndDisplayUsers(page = 0, size = 10) {
    fetch(`/habitaciones/dinero_recolectado`)
        .then(response => response.json())
        .then(data => {
            const moneyTableBody = document.getElementById('moneyTableBody');
            moneyTableBody.innerHTML = ''; // Limpiar el contenido anterior

            data.content.forEach((hab, dinero) => {
                const habRow = `
                    <tr>
                        <td>${hab.id_habitacion}</td>
                        <td>${dinero}</td>
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
                fetchAndDisplayUsers(parseInt(clickedPage));
            } else if (event.target.id === 'previousPage') {
                fetchAndDisplayUsers(currentPage - 1);
            } else if (event.target.id === 'nextPage') {
                fetchAndDisplayUsers(currentPage + 1);
            }

            event.preventDefault();
        }
    });

});