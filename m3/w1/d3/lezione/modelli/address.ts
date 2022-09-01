
export class Address {

    street: string;
    city: string;
    state: string;
    zip: number;

    constructor(street: string, city: string, state: string, zip:number) {
        this.street = street;
        this.city = city;
        this.state = state
        this.zip = zip;
    }
}