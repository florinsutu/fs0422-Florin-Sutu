const COUNTER_KEY = 'counter';

function countDown(i) {
    //callback = callback || function(){};
    timer = setInterval(function () {
        minutes = parseInt(i / 60, 10);
        seconds = parseInt(i % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        document.querySelector('#display').innerHTML = "tempo rimasto " + "0:" + minutes + ":" + seconds;

        if ((i--) > 0) {
            window.sessionStorage.setItem(COUNTER_KEY, i);
        } else {
            window.sessionStorage.removeItem(COUNTER_KEY);
            clearInterval(timer);
            
        }
    }, 1000);
}

// on window.onload innanzi tutto controlla se il valore sotto la chiave 'counter_key' esiste, se ci sta fai partire il timer da li, altrimenti parti da un valore di 0
window.onload = function () {
    let countDownTime = window.sessionStorage.getItem(COUNTER_KEY) || 60;
    countDown(countDownTime);
};



function timerPro() {

    setInterval(function () {
        let time = sessionStorage.getItem('lista') || 0
    
        time = time*1
        time += 1

        sessionStorage.setItem('lista',time)
    }, 1000)
}

timerPro()