var petPreferito = 'gatto';
console.log(petPreferito); //  nel definire una variabile non si usano spazi

/*var pet = 'gatto';
var Pet = 'cane';*/
petPreferito = 'criceto'; //nel assegnare nuovo valore a una variabile non si usa riscrivere var
var PetPreferito = 'coniglio';

console.log(petPreferito); // riassegnazione di una variabile, non è coniglio perché JS è case-sensitive

var $pet = 'giraffa';
console.log($pet);
var _pet = 'leone';
console.log(_pet);
var _pet2 = 'pantera';
// document.write(_pet2); scrive la stringa 'pantera' nel documento ma io non voglio
var stringaNumero = '12';

console.log(stringaNumero*5); //stringa*numero = numero
console.log(stringaNumero+true) 
console.log(typeof(stringaNumero+true)) //stringa+boolean = stringa
console.log(5*(true+3)) // true ha valore numerico 1

console.log(document.write='fine di naming.js')
console.log(document.write='')

