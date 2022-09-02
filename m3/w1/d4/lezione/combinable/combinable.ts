type StrNum = number | string;

let x: StrNum = '5'
let y: StrNum = 5

function combina(input1: StrNum, input2: StrNum): StrNum {

    if (typeof input1 === 'string' && input2 === 'string') {
        return input1 + ' ' + input2;
    } else {
        return Number(input1) + Number(input2);
    }
}

type Admin = {
    name: string,
    privileges: string[]
}

type Employee = {
    startDate: Date,
    skills: string
}

let amministratore:Admin = {
    name: 'Rossana',
    privileges: ['access']
}

type PromotedEmployee = Admin & Employee