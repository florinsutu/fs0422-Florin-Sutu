
class DisplayUsers {

    constructor(target, jsonFile) {

        this.target = document.querySelector(target);
        this.json = jsonFile;

        this.container = '';
        this.img = '';
        this.propList = '';

        this.ajaxCall()
    }

    ajaxCall() {
        fetch(this.json)
            .then(res => res.json())
            .then(userList => {
                for (let user of userList) //non servono le graffe per una sola riga con if, neanche per il for a quanto pare e probabilmente neanche per altre cose
                    this.createUser(user)
            })
    }

    createBaseHTML() {
        this.container = document.createElement('div');
        this.img = document.createElement('img');
        this.propList = document.createElement('ul');
        this.container.append(this.img, this.propList)
        this.target.append(this.container)
    }

    createUser(user) {

        this.createBaseHTML()

        for (let prop in user) {

            //con extra2 ho preferito cambiare da if in uno switch, anche se effettivamente bastava dargli una struttura diversa al if
            switch (prop) {
                case "profileURL":
                    this.img.src = user[prop];
                    break;
                case "gender":
                    this.container.classList.add(user[prop]);
                    break;
                case "id":
                    break;
                default:
                    let li = document.createElement('li')
                    li.innerHTML = `<span> ${prop} </span> : ${user[prop]} `
                    this.propList.append(li)

            }
        }

        this.adjustments()
    }

    adjustments() {
        if (this.img.src == "")
            this.img.src = 'https://media.istockphoto.com/vectors/default-image-icon-vector-missing-picture-page-for-website-design-or-vector-id1357365823?k=20&m=1357365823&s=612x612&w=0&h=ZH0MQpeUoSHM3G2AWzc8KkGYRg4uP_kuu0Za8GFxdFc='

        // if (!(this.container.classList.contains("Male" || "Female"))) mi sembrava più bello ma non funziona
        // if (this.container.classList == "") è più corto ma obbliga a non assegnare nessuna classe al container per funzionare e non mi piace
        
        if (!(this.container.classList.contains("Male") || this.container.classList.contains("Female")))
            this.container.classList.add('Unspecified') //serve solo per json che non contengono "gender" tra le proprietà degli utenti
    }
}

let traccia = new DisplayUsers('#grigliaUtenti', 'users.json');

let extra1 = new DisplayUsers('#grigliaUtenti', 'extra1.json')


//================================================================================================================================

const apiUtenti = 'http://localhost:3000/users';
let form = document.querySelector('#formUtenti')

button.addEventListener("click", function (e) {
    e.preventDefault();
    let user = {}
    for (let element of form) {
        if (!(element.id == 'button') && element.value != '')
            user[element.name] = element.value // in alternativa si può usare element.id come sopra (ovviamente cambiando anche l'html), non saprei qual è il metodo migliore
    }

    let options = {
        method: 'POST',
        body: JSON.stringify(user),
        headers: {"content-type": "application/json"}
    }

    fetch(apiUtenti, options)

})

// npm run extra2 per utilizzare il form
let extra2 = new DisplayUsers('#grigliaUtenti', apiUtenti)