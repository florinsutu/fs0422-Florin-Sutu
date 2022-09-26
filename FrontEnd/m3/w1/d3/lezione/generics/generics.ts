

function numero(x: number):number{
    return x
}

numero(5)

function numeroGenerico<T>(x:T):T{
    return x
}

numeroGenerico<number>(5)

interface IKeyValue<T,U>{
    prop1:T,
    prop2:U
}

let obj:IKeyValue<string,number> = {
    prop1:'prova',
    prop2: 5
}

class CustomArray<T>{

    private arr:T[] = [];
}