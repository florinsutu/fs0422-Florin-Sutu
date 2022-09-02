

interface ISmartphone { //si, la traccia dice "di nome Smartphone", però è un'interfaccia dopotutto

    carica: number;
    numeroChiamate: number;
    costoPerMinuto: number;

    ricarica(unaRicarica: number): void;
    chiamata(minutiDurata: number): void;
    numero404(): number;
    getNumeroChiamate(): number;
    azzeraChiamate(): void;

}

console.log('')
console.log('|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||')
console.log('')

class FirstUser implements ISmartphone {
    carica: number;
    numeroChiamate: number;
    costoPerMinuto: number = 0.20;

    constructor(carica: number, numeroChiamate: number = 0) {
        this.carica = carica;
        this.numeroChiamate = numeroChiamate;
    }

    ricarica(unaRicarica: number): void {
        this.carica += unaRicarica;
    }
    chiamata(minutiDurata: number): void {
        this.carica -= minutiDurata * this.costoPerMinuto;
        this.numeroChiamate++;
    }
    numero404(): number {
        return this.carica;
    }
    getNumeroChiamate(): number {
        return this.numeroChiamate;
    }
    azzeraChiamate(): void {
        this.numeroChiamate = 0;
    }

}

let user1 = new FirstUser(200);
console.log(user1)

user1.ricarica(50)
console.log(`La somma disponibile per le chiamate è di ${user1.numero404()}€`)

user1.chiamata(100);
console.log(`Il numero di chiamate effettuate è ${user1.getNumeroChiamate()} e la somma disponibile per le chiamate è di ${user1.numero404()}€`)

console.log(`Il nuovo piano tariffario prevede un costo di ${user1.costoPerMinuto = 1}€ al minuto`)

user1.chiamata(100);
user1.azzeraChiamate()
console.table(user1)


console.log('')
console.log('|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||')
console.log('')


class SecondUser implements ISmartphone {
    carica: number;
    numeroChiamate: number;
    costoPerMinuto: number = 0.20;

    constructor(carica: number, numeroChiamate: number = 0) {
        this.carica = carica;
        this.numeroChiamate = numeroChiamate;
    }

    ricarica(unaRicarica: number): void {
        this.carica += unaRicarica;
    }
    chiamata(minutiDurata: number): void {
        this.carica -= minutiDurata * this.costoPerMinuto;
        this.numeroChiamate++;
    }
    numero404(): number {
        return this.carica;
    }
    getNumeroChiamate(): number {
        return this.numeroChiamate;
    }
    azzeraChiamate(): void {
        this.numeroChiamate = 0;
    }

}

let user2 = new SecondUser(100,5);
console.log(user2)

user2.ricarica(50)
console.log(`La somma disponibile per le chiamate è di ${user2.numero404()}€`)

user2.chiamata(100);
console.log(`Il numero di chiamate effettuate è ${user2.getNumeroChiamate()} e la somma disponibile per le chiamate è di ${user2.numero404()}€`)

console.log(`Il nuovo piano tariffario prevede un costo di ${user2.costoPerMinuto = 0.5}€ al minuto`)

user2.chiamata(100);
user2.azzeraChiamate()
console.table(user2)

console.log('')
console.log('|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||')
console.log('')

class ThirdtUser implements ISmartphone {
    carica: number;
    numeroChiamate: number;
    costoPerMinuto: number = 0.20;

    constructor(carica: number, numeroChiamate: number = 0) {
        this.carica = carica;
        this.numeroChiamate = numeroChiamate;
    }

    ricarica(unaRicarica: number): void {
        this.carica += unaRicarica;
    }
    chiamata(minutiDurata: number): void {
        this.carica -= minutiDurata * this.costoPerMinuto;
        this.numeroChiamate++;
    }
    numero404(): number {
        return this.carica;
    }
    getNumeroChiamate(): number {
        return this.numeroChiamate;
    }
    azzeraChiamate(): void {
        this.numeroChiamate = 0;
    }

}

let user3 = new FirstUser(1000);
console.log(user3)

user3.ricarica(10)
console.log(`La somma disponibile per le chiamate è di ${user3.numero404()}€`)

user3.chiamata(100);
console.log(`Il numero di chiamate effettuate è ${user3.getNumeroChiamate()} e la somma disponibile per le chiamate è di ${user3.numero404()}€`)

console.log(`Il nuovo piano tariffario prevede un costo di ${user3.costoPerMinuto = 5}€ al minuto`)

user3.chiamata(100);
user3.azzeraChiamate()
console.table(user3)