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

const roleMapping = {
  "Receptionist": "recepcionista",
  "Service Manager": "gerente",
  "Employee": "empleado",
  "Data Administrator": "admin_datos",
  "Customer (Client)": "cliente"
};


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
      let role = roleDropdown.innerText.trim();
  
      // Mapear el rol al valor correcto en la base de datos
      role = roleMapping[role] || role;
  
      // Si el rol es "cliente", hacemos un fetch a la ruta de clientes
      if (role === "cliente") {
          fetch(`/clientes/${documentNumber}`)
              .then(response => response.json())
              .then(data => {
                  if (data.cliente) {
                      alert(`Cliente encontrado: ${data.cliente.nombre}`);
                      window.location.href = "cliente.html";
                  } else {
                      alert('Cliente no encontrado.');
                  }
              })
              .catch(error => {
                  console.error('Error fetching client:', error);
              });
      } else { // Si no es un cliente, asumimos que es un tipo de empleado
          fetch(`/empleados/${documentNumber}`)
              .then(response => response.json())
              .then(data => {
                  if (data.empleado && data.empleado.rol === role) {
                      switch(role) {
                          case "gerente":
                              window.location.href = "administradorDelSistema.html";
                              break;
                          case "admin_datos":
                              window.location.href = "administrador.html";
                              break;
                          case "recepcionista":
                          case "empleado":
                              window.location.href = "recepcionista.html";
                              break;
                          default:
                              alert('Rol no reconocido.');
                              break;
                      }
                  } else {
                      alert('Empleado no encontrado o el rol no coincide.');
                  }
              })
              .catch(error => {
                  console.error('Error fetching employee:', error);
              });
      }
  });

  document.querySelector('.btn.btn-secondary.goback').addEventListener('click', function() {
    window.location.href = "/";
  });
  
});
