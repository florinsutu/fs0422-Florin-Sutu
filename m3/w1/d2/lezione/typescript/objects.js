var obj = {};
var states = {
    italy: 'Rome',
    england: 'London',
    id: function () {
        return this.italy;
    }
};
console.log(states.italy, states.england);
console.log(states.id);
console.log(states.id());
