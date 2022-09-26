"use strict";
class InitializePage {
    constructor(target) {
        this.key = target; // la chiave con cui verrà salvato sul local storage l'array di users
        this.target = document.querySelector(target);
        let dataBase = localStorage.getItem(target) || '[]';
        this.allUsers = JSON.parse(dataBase);
        if (!this.target)
            throw new Error('No valid target specified');
        this.createFormHTML();
        if (this.allUsers.length > 0) {
            for (let user of this.allUsers) {
                new DisplayUser(user.id, user.name, user.carica, user.numeroChiamate, user.costoPerMinuto, user.durataChiamate);
            }
        }
    }
    createFormHTML() {
        var _a;
        this.inputForm = document.createElement('form');
        this.inputForm.append(this.inputName = this.createInput('Nome', 'text'), this.inputAmount = this.createInput('Carica', 'number'), this.inputCreate = this.createInput('', 'submit', 'Crea'));
        (_a = this.target) === null || _a === void 0 ? void 0 : _a.append(this.inputForm);
        this.createUser();
    }
    createUser() {
        this.inputForm.addEventListener('submit', (e) => {
            e.preventDefault();
            this.userId = this.allUsers.length || 0;
            let user = new DisplayUser(this.userId, this.inputName.value, this.inputAmount.valueAsNumber);
            this.allUsers.push(user);
            localStorage.setItem(this.key, JSON.stringify(this.allUsers));
            this.userId++;
            this.inputForm.reset();
            /**per ogni nuovo utente viene creato un codice numerico progressivo che servirà per determinate procedure in seguito */
        });
    }
    createInput(label, type, value) {
        let input = document.createElement('input');
        input.placeholder = label;
        input.value = value || '';
        input.type = type;
        return input;
    }
}
class DisplayUser {
    constructor(id, name, carica, numeroChiamate = 0, costoPerMinuto = 0.20, durataChiamate = 0) {
        var _a;
        this.target = document.querySelector('#target');
        this.container = this.createContainer('userDiv');
        this.id = id;
        this.name = name;
        this.carica = carica;
        this.numeroChiamate = numeroChiamate;
        this.costoPerMinuto = costoPerMinuto;
        this.durataChiamate = durataChiamate;
        (_a = this.target) === null || _a === void 0 ? void 0 : _a.append(this.container);
        this.createUser();
    }
    // Creazione Utente
    createUser() {
        this.createDataHTML();
        this.createInputHTML();
        this.createButtonHTML();
    }
    createDataHTML() {
        let dataDiv = this.createContainer('dataDiv');
        dataDiv.append(this.createDiv('Nome', 'userName', this.name), this.createDiv('Saldo', 'userBalance', this.carica), this.createDiv('Chiamate', 'userCalls', this.numeroChiamate), this.createDiv('Piano Tariffario', 'userPricing', this.costoPerMinuto), this.createDiv('Durata Chiamate', 'userCallTime', this.durataChiamate));
        this.container.append(dataDiv);
    }
    createButtonHTML() {
        let buttonDiv = this.createContainer('buttonDiv');
        buttonDiv.append(this.createButton('Ricarica', () => this.ricarica()), this.createButton('Effettua Chiamata', () => this.chiamata()), this.createButton('Modifica Piano Tariffario', () => this.modificaPiano()), this.createButton('Azzera Chiamate', () => this.azzeraChiamate()));
        this.container.append(buttonDiv);
    }
    createInputHTML() {
        let inputDiv = this.createContainer('inputDiv');
        inputDiv.append(this.inputRicarica = this.createInput('Inserire somma ricarica'), this.inputChiamata = this.createInput('Inserire durata chiamata'), this.inputPianoTariffario = this.createInput('Inserire nuovo piano'));
        this.container.append(inputDiv);
    }
    // Metodi DOM
    createContainer(identifier) {
        let div = document.createElement('div');
        div.classList.add(identifier);
        return div;
    }
    createDiv(label, spanIdentifier, prop) {
        let div = document.createElement('div');
        div.textContent = `${label}: `;
        let span = document.createElement('span');
        span.classList.add(spanIdentifier);
        span.textContent = String(prop);
        div.append(span);
        return div;
    }
    createButton(label, operation) {
        let button = document.createElement('button');
        button.textContent = label;
        button.addEventListener('click', operation);
        return button;
    }
    createInput(label) {
        let input = document.createElement('input');
        input.placeholder = label;
        input.type = 'number';
        return input;
    }
    $(className) {
        return this.container.querySelector(className);
    }
    // Operazioni Telefoniche
    ricarica() {
        if (this.inputRicarica.valueAsNumber > 0) {
            this.carica += this.inputRicarica.valueAsNumber;
            this.$('.userBalance').textContent = String(this.carica);
        }
        else
            throw new Error('Invalid input');
        this.aggiornaLocalStorage(this.id);
    }
    chiamata() {
        if (!this.inputChiamata.valueAsNumber)
            throw new Error('Invalid input');
        if (this.carica <= 0)
            throw new Error('Credito Insufficiente');
        else if (this.carica >= this.inputChiamata.valueAsNumber * this.costoPerMinuto) {
            this.carica -= this.inputChiamata.valueAsNumber * this.costoPerMinuto;
            this.durataChiamate += this.inputChiamata.valueAsNumber;
        }
        else {
            this.durataChiamate += this.carica / this.costoPerMinuto;
            this.carica = 0;
        }
        this.numeroChiamate++;
        this.$('.userBalance').textContent = String(this.carica);
        this.$('.userCalls').textContent = String(this.numeroChiamate);
        this.$('.userCallTime').textContent = String(this.durataChiamate);
        this.aggiornaLocalStorage(this.id);
    }
    modificaPiano() {
        if (!this.inputPianoTariffario.valueAsNumber || this.inputPianoTariffario.valueAsNumber <= 0)
            throw new Error('Invalid input');
        this.costoPerMinuto = this.inputPianoTariffario.valueAsNumber;
        this.$('.userPricing').textContent = String(this.costoPerMinuto);
        this.aggiornaLocalStorage(this.id);
    }
    azzeraChiamate() {
        this.numeroChiamate = this.durataChiamate = 0;
        this.$('.userCalls').textContent = String(this.numeroChiamate);
        this.$('.userCallTime').textContent = String(this.durataChiamate);
        this.aggiornaLocalStorage(this.id);
    }
    // Local storage
    aggiornaLocalStorage(id) {
        //tramite l'id, si va a modificare l'user specifico nell'array, grazie al fatto che la posizione che occupano è rappresentata dalla stessa cifra dell'id
        let dataBase = localStorage.getItem('#target') || '[]';
        let allUsers = JSON.parse(dataBase);
        allUsers[id].carica = this.carica;
        allUsers[id].numeroChiamate = this.numeroChiamate;
        allUsers[id].costoPerMinuto = this.costoPerMinuto;
        allUsers[id].durataChiamate = this.durataChiamate;
        localStorage.setItem('#target', JSON.stringify(allUsers));
    }
}
new InitializePage('#target');
//# sourceMappingURL=script.js.map