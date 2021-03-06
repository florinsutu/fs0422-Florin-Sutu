/*
let data = new Date()

function display(n,x){
    document.getElementById('display'+n).innerHTML = x
}


const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }

display('0',data.toLocaleTimeString('it-IT',options))

setInterval(function(){
    let time = new Date();
    display('1',time)
},1000)

let data1 = new Date()
data1.setHours(1)
display('2',data1)
*/

let contenitore = document.querySelector('#contenitore')

let div1 = document.createElement("div");
let div2 = document.createElement("div");
let div3 = document.createElement("div");

let date = new Date();

//richiamare la data attuale
div1.innerText = `Oggi e' il giorno: ` + date;

contenitore.appendChild(div1);

//mostrare elementi singoli della data
div2.innerText = 'Giorno: ' + date.getDay() +
 ' Mese: ' + date.getMonth() + ' Numero: ' + date.getDate();
contenitore.appendChild(div2);
 
//crere un sistema per la formattazione in formato europero
const options = { 
    weekday: 'long', 
    year: 'numeric', 
    month: 'long', 
    day: 'numeric' 
};

div3.innerHTML = date.toLocaleTimeString('it-IT',options)
contenitore.appendChild(div3);