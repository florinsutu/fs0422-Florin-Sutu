function prova():void {

}

function stringa():string {
    return "stringa";

}

function bold(testo:string):string {
    return `<b>${testo}</b>`;

}
function $(selettore:string):HTMLElement | null {
    return document.querySelector(selettore);
}
 
let boldCiao:string = bold('ciao');

console.log(boldCiao);

function creaHTMLBold(testo:string):void{
    document.write('<b>${testo}</b>');
}

$('.elemento')?.style.color

let X = (selettore:string):HTMLElement | null => document.querySelector(selettore);