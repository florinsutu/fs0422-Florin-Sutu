
interface IBook{
    nPagine:number;
    getBook():string;
}

interface IBook2{
    nPagine:number;
    getBook2():string;
}

class Book implements IBook, IBook2{
    getBook2(): string {
        throw new Error("Method not implemented.");
    }
    
    nPagine: number;
    getBook(): string {
        throw new Error("Method not implemented.");
    }

}