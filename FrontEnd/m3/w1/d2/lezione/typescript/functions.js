var _a;
function prova() {
}
function stringa() {
    return "stringa";
}
function bold(testo) {
    return "<b>".concat(testo, "</b>");
}
function $(selettore) {
    return document.querySelector(selettore);
}
var boldCiao = bold('ciao');
console.log(boldCiao);
function creaHTMLBold(testo) {
    document.write('<b>${testo}</b>');
}
(_a = $('.elemento')) === null || _a === void 0 ? void 0 : _a.style.color;
var X = function (selettore) { return document.querySelector(selettore); };
