//dinamic title section
    //variables for the title
    let textLength = 0;
    let text = "Welcome! Please select your role in the main app or add a new user!";
    let animationApplied = false; // Flag to track if animation is already applied

    //function for typing title of the web
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

//extra functions
    //rolemapper for db querys
        const roleMapping = {
        "Receptionist": "recepcionista",
        "Service Manager": "gerente",
        "Employee": "empleado",
        "Data Administrator": "admin_datos",
        "Customer (Client)": "cliente"
        };
    //reset modals
        function resetModalInputs(modalId) {
            const modalElement = document.getElementById(modalId);
            const inputs = modalElement.querySelectorAll('input, select');
            inputs.forEach(input => input.value = '');
        }
    
// Creating new users
    // Creating worker
    function submitNewWorker(event) {
        //preventing the normal behavior of the form to personalize it to our behavior required
        event.preventDefault();

        //getting the instance of the form to retrieve the values
        const formData = new FormData(document.getElementById('workerForm'));

        //accessing the values of the form
        const name = formData.get('workerNameInput');
        const email = formData.get('workerEmailInput');
        const documentType = formData.get('workerDocumentTypeSelect');
        const documentNumber = formData.get('workerDocumentNumberInput');
        let role = formData.get('workerRoleSelect');

        //double checking that there are not nullable values
        if (name === null || email === null || documentType === null
                || role === null || documentNumber === null) {
            alert("There is a nullable field, please try again.");
        } else {
            //mapping right name of the role for the db
                role = roleMapping[role] || role;
            // Prepare the data to be sent to the server (body)
            const data = {
                num_doc: documentNumber,  
                tipo_doc: documentType,
                rol: role,
                nombre: name,
                email: email
            };

            // Make a POST request to create a new worker
            fetch('/empleados/new', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            })
            .then(response => response.json())  // Parse the JSON from the response
            .then(data => {
                console.log('Success:', data);
                alert('Empleado created successfully');
            })
            .catch((error) => {         // catching errors if they are and printing them
                console.error('Error:', error);
                alert('Failed to create empleado');
            });
        }

        //hide the modal
        var workerModal = bootstrap.Modal.getInstance(document.getElementById('createWorkerModal'));
        workerModal.hide();
    }


    // Creating new client
    function submitNewClient(event) {
        event.preventDefault();
        const formData = new FormData(document.getElementById('clientForm'));
        const name = formData.get('clientNameInput');
        const email = formData.get('clientEmailInput');
        const documentType = formData.get('clientDocumentTypeSelect');
        const documentNumber = formData.get('clientDocumentNumberInput');

        //double checking that there are not nullable values
        if (name === null || email === null || documentType === null
            || documentNumber === null) {
        alert("There is a nullable field, please try again.");
    } else {
        // Prepare the data to be sent to the server (body)
        const data = {
            num_doc: documentNumber,  
            nombre: name,
            email: email,
            tipo_doc: documentType,
            rol_cliente: "cliente"
            };
        //TODO FETCH TO POST THE NEW 'CLIENTE'
        }
        console.log(`Creating client: Name: ${name}, Email: ${email}, Document Type: ${documentType}`);

        var clientModal = bootstrap.Modal.getInstance(document.getElementById('createClientModal'));
        clientModal.hide();
    }

    
document.addEventListener("DOMContentLoaded", function () {
    //load title
        type()

    //catch all elements
        //modals
            document.getElementById('createWorkerModal').addEventListener('hidden.bs.modal', function () {
                resetModalInputs('createWorkerModal');
            });

            document.getElementById('createClientModal').addEventListener('hidden.bs.modal', function () {
                resetModalInputs('createClientModal');
            });
        //dropdown button to get in
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
    
    //start process of getting a user in 
        //getting references of elements to recognize a user
        const documentNumberInput = document.getElementById('documentNumberInput');
        const roleDropdown = document.querySelector('.custom-dropdown');

        //event that is activated when the button "Entrar" is clicked
            document.querySelector('.btn-primary.w-100.mb-2').addEventListener('click', function() {
            const documentNumber = documentNumberInput.value;
            let role = roleDropdown.innerText.trim();
  
            // mapping right name for the user to search in the db
                role = roleMapping[role] || role;
    
            //if the role is "cliente", we make a fetch function to catch what's inside clients route
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
                            alert(`${data.empleado.rol} encontrado: ${data.empleado.nombre}`);  
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
                    }); //end of if for button 'entrar' in line 60
    //ending process of searching valid user and role in the db
    
    // Add submit event listener to the workerForm
    document.getElementById('workerForm').addEventListener('submit', submitNewWorker);

    // Add submit event listener to the clientForm
    document.getElementById('clientForm').addEventListener('submit', submitNewClient);

});
