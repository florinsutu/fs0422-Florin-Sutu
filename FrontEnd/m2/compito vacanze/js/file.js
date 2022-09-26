

class GuestBook {

    constructor(target) {

        this.guestBook = target;
        this.target = document.querySelector(target)
        this.nameInput = '';
        this.commentInput = '';
        this.button = '';
        this.list = '';
        this.date = '';
        this.options = {
            weekday: 'long',
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        }
        this.allComments = localStorage.getItem(target) ? JSON.parse(localStorage.getItem(target)) : [];

        this.createBaseHTML()

        if (this.allComments.length > 0) {
            for (let td of this.allComments) {
                this.addGreeting(td)
            }
        }

    }

    createInputs() {
        let commentInput = this.createElementWithClass('input', 'form-control');
        commentInput.type = 'text';
        commentInput.placeholder = 'Comment'
        this.commentInput = commentInput;

        let nameInput = this.createElementWithClass('input', 'form-control fw-bold');
        nameInput.type = 'text';
        nameInput.placeholder = 'Name'
        this.nameInput = nameInput;
    }

    createbutton() {
        let button = this.createElementWithClass('button', 'btn btn-primary');
        button.innerHTML = 'Save';

        button.addEventListener('click', () => this.addGreeting())

        this.button = button;
    }

    addGreeting(td) {
        let greet = this.createElementWithClass('div', 'alert alert-success');
        this.takeDate();

        greet.innerHTML = td || (this.date + '<br>' + '<b>' + this.nameInput.value + '</b>' + ': ' + this.commentInput.value);

        td || this.allComments.push(this.date + '<br>' + '<b>' + this.nameInput.value + '</b>' + ': ' + this.commentInput.value);
        td || this.saveTodos()

        this.list.append(greet);
        this.commentInput.value = '';
        this.nameInput.value = '';
    }

    takeDate() {
        let date = new Date();

        this.date = date.toLocaleTimeString('it-IT', this.options)
    }

    createBaseHTML() {

        //creo i containers
        let formContainer = this.createElementWithClass('div', 'container')
        let listContainer = this.createElementWithClass('div', 'container')

        //creo input
        this.createInputs()

        //creo bottone
        this.createbutton()

        //creo lista
        let list = this.createElementWithClass('div', 'row');
        this.list = list;

        //inserisco gli elementi nei rispettivi container
        formContainer.append(this.nameInput, this.commentInput, this.button)
        listContainer.append(list)
        this.target.append(formContainer, listContainer)

    }

    saveTodos() {
        localStorage.setItem(this.guestBook, JSON.stringify(this.allComments))
    }

    createElementWithClass(tag, className) {
        let element = document.createElement(tag);
        element.className = className
        return element;
    }

}

let guest = new GuestBook('#target');