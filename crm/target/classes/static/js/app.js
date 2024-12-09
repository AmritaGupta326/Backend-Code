document.addEventListener('DOMContentLoaded', () => {
    // Check if we are on the customer form page
    if (window.location.pathname === '/customer-form.html') {
        const params = new URLSearchParams(window.location.search);
        const id = params.get('id');
        if (id) {
            fetchCustomer(id);
        }
    } else {
        fetchCustomers(); // Load customers if on the home page
    }
});

// Fetch and display all customers
async function fetchCustomers() {
    try {
        const response = await fetch('/api/customers');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const customers = await response.json();
        displayCustomers(customers);
    } catch (error) {
        console.error('Error fetching customers:', error);
        document.getElementById('customerTableContainer').innerHTML = '<p>Error fetching customer data.</p>';
    }
}

// Display customers in a table format
function displayCustomers(customers) {
    const container = document.getElementById('customerTableContainer');
    if (customers.length === 0) {
        container.innerHTML = '<p>No customers found.</p>';
        return;
    }

    let tableHTML = '<table><thead><tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Actions</th></tr></thead><tbody>';
    customers.forEach(customer => {
        tableHTML += `<tr>
            <td>${customer.id}</td>
            <td>${customer.firstName}</td>
            <td>${customer.lastName}</td>
            <td>${customer.email}</td>
            <td>
                <button onclick="editCustomer(${customer.id})">Update</button>
                <button onclick="deleteCustomer(${customer.id})">Delete</button>
            </td>
        </tr>`;
    });
    tableHTML += '</tbody></table>';

    container.innerHTML = tableHTML;
}

// Fetch a single customer's details for editing
async function fetchCustomer(id) {
    try {
        const response = await fetch(`/api/customers/${id}`);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const customer = await response.json();
        document.getElementById('firstName').value = customer.firstName;
        document.getElementById('lastName').value = customer.lastName;
        document.getElementById('email').value = customer.email;
    } catch (error) {
        console.error('Error fetching customer:', error);
    }
}

// Save new or updated customer data
async function saveCustomer() {
    const id = new URLSearchParams(window.location.search).get('id');
    const customer = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        email: document.getElementById('email').value,
    };

    try {
        const response = await fetch(`/api/customers${id ? '/' + id : ''}`, {
            method: id ? 'PUT' : 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(customer)
        });

        if (response.ok) {
            window.location.href = '/index.html'; // Redirect to home page
        } else {
            console.error('Error saving customer:', response.statusText);
        }
    } catch (error) {
        console.error('Error saving customer:', error);
    }
}

// Redirect to the customer form page for editing
function editCustomer(id) {
    window.location.href = `/customer-form.html?id=${id}`;
}

// Delete a customer by ID and refresh the customer list
async function deleteCustomer(id) {
    try {
        const response = await fetch(`/api/customers/${id}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            fetchCustomers(); // Refresh the list after deletion
        } else {
            console.error('Error deleting customer:', response.statusText);
        }
    } catch (error) {
        console.error('Error deleting customer:', error);
    }
}

// Redirect back to the home page
function goBack() {
    window.location.href = '/index.html';
}