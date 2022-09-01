"use strict";
class User {
    constructor(name, cognome) {
        this.role = 'user';
        this.name = name;
        this.cognome = cognome;
    }
    saluto() {
        console.log(`ciao mi chiamo ${this.name}`);
    }
    get getNome() {
        return this.name;
    }
    set setId(id) {
        this.id = id;
    }
}
let user1 = new User('Mario', 'Rossi');
// console.log(user1.name); no, name è private
user1.saluto();
//# sourceMappingURL=script.js.map