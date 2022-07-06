function calcola() {
    let c = document.getElementById('cristo').value
    let nascita = document.getElementById('nascita').valueAsNumber;
    console.log(c)
    if (c == 'D.C.') {
        let eta = 2022 - nascita
        document.getElementById("display").innerHTML = 'la tua età è di: ' + eta + ' anni'
    } else {
        let eta = 2022 + nascita
        document.getElementById("display").innerHTML = 'la tua età è di: ' + eta + ' anni'
    }


}

function incrementa() {
    let a = 0;
    return function () {
        return a++;
    };
}

let counter = incrementa();


incremento = () => {
    let contatore = document.getElementById('contatore')
    
    contatore.value = counter()
}






function mostra(x, y, o) {

    if (o == '+') {
        document.write(x + y)
    } else if (o == '*') {
        document.write(x * y)
    } else if (o == '/') {
        document.write(x / y)
    } else if (o == '-') {
        document.write(x - y)
    }
}
mostra(4, 5, '')
