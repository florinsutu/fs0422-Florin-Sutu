function mostra(input) {
    let display = document.getElementById('display')
    display.value += input.getAttribute('data-simbolo')

}
function calcola() {
    document.getElementById("display").value =
        eval(document.getElementById("display").value)
}

function canc() {
    document.getElementById('display').value = ''
}