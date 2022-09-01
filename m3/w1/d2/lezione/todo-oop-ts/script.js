"use strict";
class Todo {
    constructor(selettore) {
        this.target = document.querySelector(selettore);
        this.creaHTMLBase();
    }
    creaHTMLBase() {
        var _a;
        let input = document.createElement('input');
        let button = document.createElement('button');
        input.classList.add('form-control');
        button.classList.add('btn', 'btn-primary');
        button.textContent = 'Salva';
        (_a = this.target) === null || _a === void 0 ? void 0 : _a.append(input, button);
    }
}
new Todo('#todo');
//# sourceMappingURL=script.js.map