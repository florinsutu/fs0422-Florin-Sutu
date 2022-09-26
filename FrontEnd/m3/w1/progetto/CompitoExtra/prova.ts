
class InitializePage {

    protected target: HTMLElement | null;
    protected key: string;
    protected inputForm!: HTMLFormElement;
    protected inputName!: HTMLInputElement;
    protected inputAmount!: HTMLInputElement;
    protected inputCreate!: HTMLInputElement;
    protected userId!: number;
    protected allUsers: DisplayUser[];

    constructor(target: string) {

        this.key = target; // la chiave con cui verrà salvato sul local storage l'array di users
        this.target = document.querySelector(target)

        let dataBase: string = localStorage.getItem(target) || '[]'
        this.allUsers = JSON.parse(dataBase)

        if (!this.target)
            throw new Error('No valid target specified')

        this.createFormHTML();

        if (this.allUsers.length > 0) {
            for (let user of this.allUsers) {
                new DisplayUser(user.id, user.name, user.carica, user.numeroChiamate, user.costoPerMinuto, user.durataChiamate)
            }
        }
    }

    protected createFormHTML(): void {

        this.inputForm = document.createElement('form');
        this.inputForm.append(
            this.inputName = this.createInput('Nome', 'text'),
            this.inputAmount = this.createInput('Carica', 'number'),
            this.inputCreate = this.createInput('', 'submit', 'Crea')
        );
        this.target?.append(this.inputForm);

        this.createUser();
    }

    protected createUser(): void {
        this.inputForm.addEventListener('submit', (e) => {
            e.preventDefault();

            this.userId = this.allUsers.length || 0;
            let user = new DisplayUser(this.userId, this.inputName.value, this.inputAmount.valueAsNumber);
            this.allUsers.push(user)
            localStorage.setItem(this.key, JSON.stringify(this.allUsers))
            this.userId++

            this.inputForm.reset();

            /**per ogni nuovo utente viene creato un codice numerico progressivo che servirà per determinate procedure in seguito */
        });
    }

    protected createInput(label: string, type: string, value?: string): HTMLInputElement {
        let input = document.createElement('input')
        input.placeholder = label
        input.value = value || ''
        input.type = type
        return input
    }


}



class DisplayUser {

     target: HTMLElement | null
     container: HTMLElement
     id: number

     inputRicarica!: HTMLInputElement
     inputChiamata!: HTMLInputElement
     inputPianoTariffario!: HTMLInputElement

     name: string;
     carica: number;
     numeroChiamate: number;
     costoPerMinuto: number;
     durataChiamate: number;



    constructor(id: number, name: string, carica: number, numeroChiamate: number = 0, costoPerMinuto: number = 0.20, durataChiamate: number = 0) {
        this.target = document.querySelector('#target')
        this.container = this.createContainer('userDiv');

        this.id = id;
        this.name = name;
        this.carica = carica;
        this.numeroChiamate = numeroChiamate;
        this.costoPerMinuto = costoPerMinuto;
        this.durataChiamate = durataChiamate;

        this.target?.append(this.container)
        this.createUser()
    }

    // Creazione Utente

    protected createUser(): void {

        this.createDataHTML()
        this.createInputHTML()
        this.createButtonHTML()

    }

    protected createDataHTML(): void {
        let dataDiv: HTMLElement = this.createContainer('dataDiv');

        dataDiv.append(
            this.createDiv('Nome', 'userName', this.name),
            this.createDiv('Saldo', 'userBalance', this.carica),
            this.createDiv('Chiamate', 'userCalls', this.numeroChiamate),
            this.createDiv('Piano Tariffario', 'userPricing', this.costoPerMinuto),
            this.createDiv('Durata Chiamate', 'userCallTime', this.durataChiamate)
        )
        this.container.append(dataDiv)
    }

    protected createButtonHTML(): void {
        let buttonDiv: HTMLElement = this.createContainer('buttonDiv');

        buttonDiv.append(
            this.createButton('Ricarica', () => this.ricarica()),
            this.createButton('Effettua Chiamata', () => this.chiamata()),
            this.createButton('Modifica Piano Tariffario', () => this.modificaPiano()),
            this.createButton('Azzera Chiamate', () => this.azzeraChiamate())
        )
        this.container.append(buttonDiv)
    }

    protected createInputHTML(): void {
        let inputDiv: HTMLElement = this.createContainer('inputDiv')

        inputDiv.append(
            this.inputRicarica = this.createInput('Inserire somma ricarica'),
            this.inputChiamata = this.createInput('Inserire durata chiamata'),
            this.inputPianoTariffario = this.createInput('Inserire nuovo piano')
        )
        this.container.append(inputDiv)
    }

    // Metodi DOM

    createContainer(identifier: string): HTMLElement {
        let div = document.createElement('div')
        div.classList.add(identifier)
        return div
    }

    createDiv(label: string, spanIdentifier: string, prop: string | number): HTMLElement {
        let div = document.createElement('div')
        div.textContent = `${label}: `
        let span = document.createElement('span')
        span.classList.add(spanIdentifier)
        span.textContent = String(prop);
        div.append(span)
        return div
    }

    createButton(label: string, operation: any): HTMLButtonElement {
        let button = document.createElement('button')
        button.textContent = label
        button.addEventListener('click', operation)
        return button
    }

    createInput(label: string): HTMLInputElement {
        let input = document.createElement('input')
        input.placeholder = label
        input.type = 'number'
        return input
    }

    $(className: string): HTMLElement {
        return this.container.querySelector(className)!
    }

    // Operazioni Telefoniche

    protected ricarica(): void | never {

        if (this.inputRicarica.valueAsNumber > 0) {

            this.carica += this.inputRicarica.valueAsNumber;
            this.$('.userBalance').textContent = String(this.carica)
        }
        else throw new Error('Invalid input')

        this.aggiornaLocalStorage(this.id);
    }

    protected chiamata(): void | never {

        if (!this.inputChiamata.valueAsNumber)
            throw new Error('Invalid input')

        if (this.carica <= 0)
            throw new Error('Credito Insufficiente')

        else if (this.carica >= this.inputChiamata.valueAsNumber * this.costoPerMinuto) {
            this.carica -= this.inputChiamata.valueAsNumber * this.costoPerMinuto;
            this.durataChiamate += this.inputChiamata.valueAsNumber

        } else {
            this.durataChiamate += this.carica / this.costoPerMinuto
            this.carica = 0
        }

        this.numeroChiamate++;
        this.$('.userBalance').textContent = String(this.carica)
        this.$('.userCalls').textContent = String(this.numeroChiamate)
        this.$('.userCallTime').textContent = String(this.durataChiamate);

        this.aggiornaLocalStorage(this.id);
    }

    protected modificaPiano(): void | never {

        if (!this.inputPianoTariffario.valueAsNumber || this.inputPianoTariffario.valueAsNumber <= 0)
            throw new Error('Invalid input')

        this.costoPerMinuto = this.inputPianoTariffario.valueAsNumber
        this.$('.userPricing').textContent = String(this.costoPerMinuto);

        this.aggiornaLocalStorage(this.id);
    }

    protected azzeraChiamate(): void {

        this.numeroChiamate = this.durataChiamate = 0
        this.$('.userCalls').textContent = String(this.numeroChiamate);
        this.$('.userCallTime').textContent = String(this.durataChiamate);

        this.aggiornaLocalStorage(this.id);
    }

    // Local storage

    protected aggiornaLocalStorage(id: number): void {

        //tramite l'id, si va a modificare l'user specifico nell'array, grazie al fatto che la posizione che occupano è rappresentata dalla stessa cifra dell'id

        let dataBase: string = localStorage.getItem('#target') || '[]'
        let allUsers: DisplayUser[] = JSON.parse(dataBase)

        allUsers[id].carica = this.carica;
        allUsers[id].numeroChiamate = this.numeroChiamate;
        allUsers[id].costoPerMinuto = this.costoPerMinuto;
        allUsers[id].durataChiamate = this.durataChiamate;

        localStorage.setItem('#target', JSON.stringify(allUsers))
    }
}


new InitializePage('#target')