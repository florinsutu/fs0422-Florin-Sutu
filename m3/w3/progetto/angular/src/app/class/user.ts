export class User {
  name:string
  email: string
  id: number | undefined
  role:string
  constructor(name:string, email:string, role:string = 'user'){
    this.name = name;
    this.email = email;
    this.role = role
  }
}

