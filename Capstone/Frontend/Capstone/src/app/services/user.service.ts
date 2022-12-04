import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  apiUrl:string = 'http://localhost:8080/api/users'

  getUserById(id:string):Observable<User>{
    return this.http.get<User>(this.apiUrl + '/' + id)
  }

  getAllUsers():Observable<User[]>{
    return this.http.get<User[]>(this.apiUrl)
  }

 // non serve un post in questo caso: equivale al register in authService

  editUser(user:User):Observable<User>{
    return this.http.patch<User>(this.apiUrl + '/' + user.id, user)
  }

  deleteUser(user:User):Observable<User>{
    return this.http.delete<User>(this.apiUrl + '/' + user.id)
  }

}
