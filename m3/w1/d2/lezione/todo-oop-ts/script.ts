
class Todo{
    target !:HTMLElement|null
    input !:HTMLElement
    button !:HTMLElement

    constructor(selettore:string) {
        this.target = document.querySelector(selettore);
        this.creaHTMLBase()
    }

    creaHTMLBase(){

        let input:HTMLInputElement = document.createElement('input')
        let button:HTMLButtonElement = document.createElement('button')

        input.classList.add('form-control');
        button.classList.add('btn','btn-primary');
        button.textContent = 'Salva'

        this.target?.append(input,button)
    }
}

new Todo('#todo')