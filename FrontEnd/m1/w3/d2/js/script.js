let nome = "Mirtilla";
console.log(nome);
var utente = "cliente";
console.log(utente);
const PI = 3.14;
console.log(PI);

// nome = "Lilla"; //riassegnazione nome
console.log(nome);
utente = "amministratore";
console.log(utente);
//PI = 4;   una const non si può riassegnare
//console.log(PI);

let primoLavoro = "developing";
let secondoLavoro = "formazione";

let lavoro = primoLavoro + " e " + secondoLavoro;

console.log('io mi occupo di: ' + lavoro);
console.log('io mi occupo di: ' + primoLavoro + " e " + secondoLavoro); // fare la stessa cosa con e senza una variabile che concatena le due stringhe

let JS = true;
console.log('Let JS: ' + JS);

let anno;
console.log(anno); //anno ancora non ha un valore assegnato

anno = 1991; 

const annoAttuale = 2021;

let anniMirtilla = annoAttuale - anno;
console.log(anniMirtilla);

console.log(nome + " ha: " + anniMirtilla + " anni");

let a = 10 + 5;
let b = 3;
let c = 10;
let somma = b + c + 2;
console.log(somma);

c++;
console.log(c);
a--;
console.log(a);

let nomePet = "Billo";
let colorePet = "rosso";
const pet = "il nome del pet: " + nomePet + "e il suo colore è: " + colorePet;
console.log(pet);

// sintassi letterale con backtick -> apice inverso
const pet1 = `il nome del pet: ${nomePet}  e il suo colore è: ${colorePet}`;
console.log(pet1);

console.log('string con righe \n\ multiple');
console.log(`string
 con 
righe 
multiple`);  // backtick permette di andare a capo senza \n\

// differenze var/let const

var aa = 5
console.log(aa)

{
    let bb = 6
    console.log(bb)
    console.log(aa)
}
// console.log(bb) // not defined

{
    const AA = 45
    console.log(AA)
}

console.log(aa)
// console.log(AA) // not defined


let cc = true
let dd = ' tubero'
console.log(cc + dd) // true viene convertito in stringa

console.log(aa + cc) //true viene convertito in numero (1)

//alert(cc+dd)

//document.write(cc+dd)

console.log('mario' ==)