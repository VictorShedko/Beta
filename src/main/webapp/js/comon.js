
const form = document.querySelector('.needs-validation')

form.addEventListener('submit', (e) => {
    if (form.checkValidity() === false) {
        e.preventDefault();
    }
    if (form.license.checked === false) {
        e.preventDefault();
    }
    form.classList.add('was-validated');
})
