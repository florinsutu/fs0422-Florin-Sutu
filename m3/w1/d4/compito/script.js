"use strict";
const API_CAPI = "http://localhost:3000/capi";
//creo la classe che verrà istanziata mediante la funzione fetch
class Capo {
    constructor(id, codprod, collezione, capo, modello, quantita, colore, prezzoIvaEsclusa, prezzoIvaInclusa, disponibile, saldo) {
        this.id = id;
        this.codprod = codprod;
        this.collezione = collezione;
        this.capo = capo;
        this.modello = modello;
        this.quantita = quantita;
        this.colore = colore;
        this.prezzoIvaEsclusa = prezzoIvaEsclusa;
        this.prezzoIvaInclusa = prezzoIvaInclusa;
        this.disponibile = disponibile;
        this.saldo = saldo;
        console.log(this);
        console.log(this.getacquistocapo());
    }
    //metodo get per calcolare il saldo del capo da sottrarre al momento dell'acqusto
    getsaldocapo() {
        return (this.prezzoIvaInclusa / 100) * this.saldo;
    }
    //metodo get per calcolare il prezzo del capo d'abbigliamento una volta applicato lo sconto
    getacquistocapo() {
        return this.prezzoIvaInclusa - this.getsaldocapo();
    }
}
//funzione fetch che permette di visualizzare in console le caratteristiche dei singoli capi
fetch(API_CAPI)
    .then(res => res.json())
    .then(res => {
    for (let capo of res) {
        new Capo(capo.id, capo.codprod, capo.collezione, capo.capo, capo.modello, capo.quantita, capo.colore, capo.prezzoIvaEsclusa, capo.prezzoIvaInclusa, capo.disponibile, capo.saldo);
    }
});
//# sourceMappingURL=script.js.map