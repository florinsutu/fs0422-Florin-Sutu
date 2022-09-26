class TodoElement{
    constructor(testo,valore){
        this.testo = testo;
        this.valore = valore;
    }
}


let button = document.querySelector('#salva')

button.addEventListener('click', function (e) {

    e.preventDefault();

    let testo = document.querySelector('#testo')
    let data = document.querySelector('#data')

    let div = document.createElement('div')
    div.innerHTML = testo.value
    div.className = ('alert alert-success')

    let dataDiv = document.createElement('div')
    dataDiv.innerHTML = data.value

    let todo = new TodoElement(testo.value, data.value)

    let todoJson = JSON.stringify(todo)
    localStorage.setItem('lista', todoJson)

    div.append(dataDiv)
    document.querySelector('#lista').append(div)

    document.querySelector('#formToDo').reset
})