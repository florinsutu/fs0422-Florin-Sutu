let obj: {} = {}

let states: {
    italy: string,
    england: string,
    id: any
} = {
    italy: 'Rome',
    england: 'London',
    id: function ():string {
        return this.italy
    }
}

console.log(states.italy, states.england)
console.log(states.id)
console.log(states.id())