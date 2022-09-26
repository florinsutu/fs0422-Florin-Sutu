window.document.write('Hello World!');

document.getElementById('test').innerHTML='ciao';
document.getElementById('test').style.backgroundColor='red';

console.log('ciao');

//variabili

var nomeVariabile = 'ciao';

var a = 4
var b = 5

console.log(a + b)

a=1

console.log(a)

//tipi di dato 

var x = 5    //numero
var y = '5'   //stringa
var h = [1, 2, 3, 'ciao']   //array
console.log(h [3])

var b= true //booleano

var o = {      //oggetto

    nome: 'edoardo',
    età: 27,
    sposato: false,
}    

console.log(o)

//conversioni e operazioni

a = 2
b = 2

console.log(a*b)

c = '2'

console.log(b+c)
console.log(b+true)

console.log(typeof(a))

//funzioni

function saluta(){
    console.log('ciao')
}
saluta()
