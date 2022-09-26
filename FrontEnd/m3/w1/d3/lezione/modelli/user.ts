import {Address} from './address';

export class User {

    protected id:number| undefined;
    name:string;
    lastname:string;
    address:Address| undefined;
    billingAddress:Address| undefined;

    constructor(name:string, lastname:string, ){
        this.name = name;
        this.lastname = lastname;
    }
 
    set setAddress(address:Address){
        this.address = address; 
    }

    set setBillingAddress(billingAddress:Address){
        this.billingAddress = billingAddress; 
    }

}

let user:User = new User('marios', 'rossi');

let address:Address = new Address('via roma1', 'roma', 'italia', 65455);
user.setAddress = address;