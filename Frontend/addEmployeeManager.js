document.getElementById('registrationForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const password = document.getElementById('password').value;
    const accountType = document.getElementById('accountType').value;

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