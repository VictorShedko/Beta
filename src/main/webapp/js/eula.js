const form = document.querySelector('#registration-form')

form.addEventListener('submit', (e) => {
    if (form.license.checked === false) {
        e.preventDefault();
    }

})
