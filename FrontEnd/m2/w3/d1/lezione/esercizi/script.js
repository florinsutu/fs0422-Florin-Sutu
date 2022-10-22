let bottone = document.querySelector('#salva')

bottone.addEventListener('click', function () {

    let testo = document.querySelector('#testo')
    let frase = document.createElement('div')

    frase.innerHTML = testo.value;
    frase.classList.add('alert', 'alert-success')

    document.querySelector('#lista').append(frase)

})