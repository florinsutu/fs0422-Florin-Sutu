import { Injectable } from '@angular/core';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  allUsers: User[] = [
    {
      id:1,
      name:'mario',
      lastname:'rossi',
      active: true,
    },
    {
      id:2,
      name:'maria',
      lastname:'rossi',
      active: true,
    },
    {
      id:3,
      name:'mariano',
      lastname:'rossi',
      active: false,
    }
  ];

  addNewUser(user: User): void {

    let userCopy = Object.assign({}, user);
    this.allUsers.push(userCopy);
  }

  deleteUser(id: string | number): void {

  let index = this.allUsers.findIndex(user => user.id === id);
  this.allUsers.splice(index,1)
  }

  getUserById(id: string | number): User | undefined {
    return this.allUsers.find(u => u.id == id)
  }

  getAllActiveUsers(): User[] {
    return this.allUsers.filter(u => u.active)
  }

  getAllInactiveUsers(): User[] {
    return this.allUsers.filter(u => !u.active)
  }

}
