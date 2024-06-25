function validatePassword() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;
    if (password !== confirmPassword) {
        alert("Passwords do not match.");
        return false;
    }
    return true;
}

function togglePasswordVisibility(inputId, eyeIcon) {
    const input = document.querySelector(inputId);
    if(input.type === 'password') {
        input.type = 'text';
        eyeIcon.classList.replace('fa-eye', 'fa-eye-slash');
    } else {
        input.type = 'password';
        eyeIcon.classList.replace('fa-eye-slash', 'fa-eye');
    }
}
const eyeIcons = document.querySelectorAll('.fa-eye');
eyeIcons.forEach((eyeIcon) => {eyeIcon.addEventListener('click', () => {
        const inputId = eyeIcon.getAttribute('id') === 'passwordEyeIcon' ? '#password' : '#confirmPassword';
        togglePasswordVisibility(inputId, eyeIcon);
    });
});

document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    form.addEventListener('submit', (event) => {
        const username = document.querySelector('input[name="username"]').value;
        localStorage.setItem('username', username);
    });
});


