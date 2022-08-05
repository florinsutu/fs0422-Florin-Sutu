
class DisplayUsers {

    constructor(target, json) {

        this.target = document.querySelector(target);
        this.json = json

        this.container = '';
        this.img = '';
        this.propList = '';

        this.ajaxCall()
    }

    ajaxCall() {
        fetch(this.json)
            .then(res => res.json())
            .then(userList => {
                for (let user of userList) {
                    this.createUser(user)
                }
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

            if (!(prop == "profileURL" || prop == "gender")) {
                let li = document.createElement('li')
                li.innerHTML = `<span> ${prop} </span> : ${user[prop]} `
                this.propList.append(li)

            } else if (prop == "profileURL") {
                this.img.src = user[prop]
            } else {
                this.container.classList.add(user[prop])
            }
        }

        this.adjustments()
    }

    adjustments() {
        if (this.img.src == "")
            this.img.src = 'https://media.istockphoto.com/vectors/default-image-icon-vector-missing-picture-page-for-website-design-or-vector-id1357365823?k=20&m=1357365823&s=612x612&w=0&h=ZH0MQpeUoSHM3G2AWzc8KkGYRg4uP_kuu0Za8GFxdFc='

        if (this.container.classList == "")
            this.container.classList.add('Unspecified')
    }
}

let traccia = new DisplayUsers('#grigliaUtenti', 'users.json');

let extra = new DisplayUsers('#grigliaUtenti', 'corso.json')