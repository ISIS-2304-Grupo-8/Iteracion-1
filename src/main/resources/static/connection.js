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
//global variables
    //num role mapper
    var numRoleMapping = {};
    //rolemapper for db querys
    const roleMapping = {
        "Recepcionista": "recepcionista",
        "Gerente": "gerente",
        "Empleado": "empleado",
        "Administrador": "administrador",
        "Cliente": "cliente"
        };
//extra functions
    // Insertar roles en el select después de obtenerlos
    function populateRolesFromMapping() {
    const workerRoleSelect = document.getElementById('workerRoleSelect');
    
    for (const roleId in numRoleMapping) {
        const role = numRoleMapping[roleId];
        if (!role.toLowerCase().startsWith("cliente")) {
            const option = document.createElement("option");
            option.value = capitalizeFirstLetter(role);
            option.textContent = capitalizeFirstLetter(role);
            workerRoleSelect.appendChild(option);
            }
        }   
    }
    // get key from value of a dict
    function getKeysByValue(object, value) {
        return Object.keys(object).filter(key => object[key] === value);
    }
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
            number_of_role_in_db = getKeysByValue(numRoleMapping, role.toLowerCase())[0];
                role = {
                    "id_usuario": number_of_role_in_db,
                    "rol": role.toLowerCase()
                };
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
            role = {
                "id_usuario": 1,
                "rol": "cliente"
            };
        // Prepare the data to be sent to the server (body)
        const client_data = {
            num_doc: documentNumber,  
            nombre: name,
            email: email,
            tipo_doc: documentType,
            rol_cliente: role
            };
        // Make a POST request to create a new worker
            fetch('/clientes/new', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(client_data),
            })
            .then(response => response.json())  // Parse the JSON from the response
            .then(data => {
                console.log('Success:', data);
                alert('Cliente created successfully');
            })
            .catch((error) => {         // catching errors if they are and printing them
                console.error('Error:', error);
                alert('Failed to create cliente');
            });
        }

        var clientModal = bootstrap.Modal.getInstance(document.getElementById('createClientModal'));
        clientModal.hide();
    }

    //Capitalize Letter for Roles
    function capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
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
                        if (data.empleado && data.empleado.rol.rol === role) {
                            alert(`${data.empleado.rol.rol} encontrado: ${data.empleado.nombre}`);  
                            switch(role) {
                                case "gerente":
                                    window.location.href = "gerente.html";
                                    break;
                                case "administrador":
                                    window.location.href = "administrador.html";
                                    break;
                                case "recepcionista":
                                    window.location.href = "recepcionista.html";
                                    break;
                                case "empleado":
                                    window.location.href = "empleado.html";
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
    
    //fetching role types in the hotel
    // ...

    //fetching role types in the hotel
    fetch("http://localhost:8080/tiposusuario")
    .then(response => {
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        return response.json();
    })
    .then(data => {
        const dropdownList = document.querySelector(".dropdown-menu");

        data.forEach(role => {
            const listItem = document.createElement("li");
            const anchorItem = document.createElement("a");
            anchorItem.className = "dropdown-item";
            anchorItem.href = "#";
            anchorItem.textContent = capitalizeFirstLetter(role.rol);
            listItem.appendChild(anchorItem);
            dropdownList.appendChild(listItem);

            //add elements to role numberRoleMapping
            numRoleMapping[role.id_usuario] = role.rol;
        });

        // Llamamos a la función que inserta los roles en el select
        populateRolesFromMapping();

        // Aquí es donde se moverá el código de manejo del evento de click
        let dropdownItems = document.querySelectorAll('.dropdown-item');
        dropdownItems.forEach(function(item) {
            item.addEventListener('click', function() {
                // Set the dropdown button text to the clicked item's text
                dropdownButton.textContent = this.textContent;
            });
        });
    })
    .catch(error => {
        console.error("Error fetching roles:", error);
    });
    
});
