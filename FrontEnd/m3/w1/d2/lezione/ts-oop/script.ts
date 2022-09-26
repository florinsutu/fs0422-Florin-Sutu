class User {

    protected id: number | undefined;
    private name: string;
    private cognome: string;
    public role: string = 'user';

    constructor(name:string, cognome:string) {
        this.name = name;
        this.cognome = cognome;
    }

    public saluto():void {
        console.log(`ciao mi chiamo ${this.name}`)
    }

    get getNome():string {
        return this.name;
    }

    set setId(id:number) {
        this.id = id;
    }
}

let user1 = new User('Mario','Rossi');

// console.log(user1.name); no, name è private

user1.saluto()
