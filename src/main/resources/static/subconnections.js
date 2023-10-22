document.addEventListener("DOMContentLoaded", function () {
    document.querySelector('.btn.btn-secondary.goback').addEventListener('click', function() {
      window.location.href = "/";
    });
  });

document.addEventListener("DOMContentLoaded", function () {
    // Get the forms button
    let forms = document.querySelector('#createRoomTypeForm');

      // Evento cuando se hace click en el botón "Entrar"
        forms.addEventListener('click', function() {
            const data = {
                tipo: document.getElementById('roomTypeName').value,
                costo: parseInt(document.getElementById('pricePerNight').value),
                capacidad: parseInt(document.getElementById('maxOccupancy').value),
                descripcion: document.getElementById('descriptiontype').value
            };

            fetch('/tipos_de_habitaciones/new/save', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                // Cierra el modal y/o refresca la lista de tipos de habitación
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
    
  });
  