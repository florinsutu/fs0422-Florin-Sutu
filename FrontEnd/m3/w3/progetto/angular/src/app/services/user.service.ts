import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../class/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  apiUrl:string = 'http://localhost:3000/users'

  getAllUsers():Observable<User[]>{
    return this.http.get<User[]>(this.apiUrl)
  }

  addUser(user:User):Observable<User>{
    return this.http.post<User>(this.apiUrl, user)
  }

  editUser(user:User):Observable<User>{
    return this.http.patch<User>(this.apiUrl + '/' + user.id, user)
  }

  deleteUser(user:User):Observable<User>{
    return this.http.delete<User>(this.apiUrl + '/' + user.id)
  }

}
