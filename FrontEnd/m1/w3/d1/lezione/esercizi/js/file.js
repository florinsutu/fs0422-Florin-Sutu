var bloccoRosso = document.getElementById('blocco-rosso')
console.log(bloccoRosso)

bloccoRosso.style.backgroundColor = 'red'

var div = document.getElementsByTagName('div')

console.log(div)
console.log(div[1].innerHTML)
div[1].style.backgroundColor = 'blue'
div[2].style.backgroundColor = 'blue'
div[3].style.backgroundColor = 'blue'

var blocchiGialli = document.getElementsByClassName('blocchi-gialli')

blocchiGialli[0].style.backgroundColor = 'yellow'
blocchiGialli[1].style.backgroundColor = 'yellow'
blocchiGialli[2].style.backgroundColor = 'yellow'


//consigli di michele

var bloccoRossoNew = document.querySelector('#blocco-rosso')