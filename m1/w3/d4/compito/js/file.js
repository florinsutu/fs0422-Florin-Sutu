function mostra(esercizio){
    console.log(esercizio)
}
var s = function separazione(){
    console.log('******************************')
}



let es1 = 'Questa è la stringa del primo esercizio'
let a = es1.toLowerCase()
let b = es1.toUpperCase()
let c = es1.split(" ")
let d = c[5]+' '+c[6] + ' completato'

mostra(a)
mostra(b)
mostra(c)
mostra(d)

s()

let es2 = ['Alderico','Gilberto','Genoveffa','Asdrubale']
let e = 'Isotta'
es2.push(e)

mostra(es2[2])
mostra (es2)
mostra(es2.length)

s()

let operatori = ['+','*','-','/']

function es3(arr){
    let somma = arr[0]
    return somma

}

let j = es3(operatori)
mostra(eval(5+j+6))

s()



function es4(arr){
    let r = arr.shift()
    return arr.push(r)
    
}



es4(es2)

mostra(es2)

