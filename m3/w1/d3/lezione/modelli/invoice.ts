import {User} from './user'
import {Address} from './address';

class Invoice{
    id:number| undefined;
    date!:Date;
    expiration!:Date;
    userId!:number;
    user:User| undefined;
    billingAddress!:Address;
    rows:any[] = [];

    constructor(billingAddress:Address){
        this.billingAddress = billingAddress || this.user?.billingAddress
    }
}