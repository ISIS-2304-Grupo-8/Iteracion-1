let textLength = 0;
let text = "Welcome! Please select your role in the main app or add a new user!";
let animationApplied = false; // Flag to track if animation is already applied

function type() {
  let textChar = text.charAt(textLength++);
  let paragraph = document.getElementById("typed");
  let charElement = document.createTextNode(textChar);
  paragraph.appendChild(charElement);
  if (textLength < text.length + 1) {
    setTimeout('type()', 50);
  } else {
    text = 'Python and Web Developer - Systems Engineering Student';
  }
}

document.addEventListener("DOMContentLoaded", function () {
  //load title
    type()

  // Get the dropdown button
  let dropdownButton = document.querySelector('.custom-dropdown');

  // Get all dropdown items
  let dropdownItems = document.querySelectorAll('.dropdown-item');

  // Add event listener to each dropdown item
  dropdownItems.forEach(function(item) {
      item.addEventListener('click', function() {
          // Set the dropdown button text to the clicked item's text
          dropdownButton.textContent = this.textContent;
      });
  });

    // Obteniendo referencias
    const documentNumberInput = document.getElementById('documentNumberInput');
    const roleDropdown = document.querySelector('.custom-dropdown');

    // Evento cuando se hace click en el botón "Entrar"
    document.querySelector('.btn-primary.w-100.mb-2').addEventListener('click', function() {
        const documentNumber = documentNumberInput.value;
        const role = roleDropdown.innerText.trim();

        if (role === "Customer (Client)") {
            // Hacer fetch al endpoint de clientes
            fetch(`/clientes/${documentNumber}`)
                .then(response => response.json())
                .then(data => {
                    if (data.cliente) {
                        alert(`Cliente encontrado: ${data.cliente.nombre}`);
                    } else {
                        alert('Cliente no encontrado.');
                    }
                })
                .catch(error => {
                    console.error('Error fetching client:', error);
                });
        } else {
            // Hacer fetch al endpoint de empleados
            fetch(`/empleados/${documentNumber}`)
                .then(response => response.json())
                .then(data => {
                    if (data.empleado) {
                        alert(`Empleado encontrado: ${data.empleado.nombre}`);
                    } else {
                        alert('Empleado no encontrado.');
                    }
                })
                .catch(error => {
                    console.error('Error fetching employee:', error);
                });
        }
    });
});
