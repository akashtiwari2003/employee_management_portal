document.getElementById('loginForm').addEventListener('submit', async function (event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    try {
        const response = await fetch('http://localhost:8085/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ 
                email : username,
                password : password })
        });

        let data;
        try {
            data = await response.json();
            console.log(data);
        } catch (error) {
            // Handle JSON parsing error
            document.getElementById('error-message').textContent = 'Invalid response from server';
            return;
        }

        if (response.ok) {
            // Handle successful login
            alert('Login successful!');
            // Redirect to another page or do something else
        } else {
            // Handle login failure
            document.getElementById('error-message').textContent = data.message || 'Login failed';
        }
    } catch (error) {
        // Handle fetch error
        document.getElementById('error-message').textContent = 'An error occurred: ' + error.message;
    }
});

document.getElementById('registrationForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const password = document.getElementById('password').value;
    const accountType = document.getElementById('accountType').value;

    // Perform validation if needed

    // Example of sending data to backend using fetch
    try {
        const response = await fetch('http://localhost:8085/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: email,
                firstName: firstName,
                lastName: lastName,
                password: password,
                type: accountType
            })
        });

        if (response.ok) {
            // Registration successful, redirect to another page
            alert("HELLO"); // Redirect to login page
        } else {
            // Registration failed, handle error
            const data = await response.json();
            alert(data.message || 'Registration failed');
        }
    } catch (error) {
        // Handle fetch error
        console.error('Error:', error);
        alert('An error occurred during registration');
    }
});