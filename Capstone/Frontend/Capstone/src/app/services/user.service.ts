import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { UserUpdate } from '../models/user-update';

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

  getTextedUsers(id: number) {
    return this.http.get<User[]>(this.apiUrl + '/chat_users/' + id)
  }

  getAllEmails(){
    return this.http.get<string[]>(this.apiUrl+"/get_emails")
  }

  getAllUsernames(){
    return this.http.get<string[]>(this.apiUrl+"/get_usernames")
  }

 // non serve un post in questo caso: equivale al register in authService

  editUser(user:UserUpdate):Observable<User>{
    return this.http.put<User>(this.apiUrl + '/' + user.id, user)
  }

  editProfilePic(id:number, userPic: FormData) {
    return this.http.put<User>(this.apiUrl+"/"+id+"/updateProfilePic", userPic)
  }

  follow(followedId:number, followerId:number):Observable<User> {
    return this.http.put<User>(this.apiUrl + '/' + followedId +"/follow", followerId)
  }

  getFollowers(user: User) {
    return this.http.get<User[]>(this.apiUrl+"/"+user.id+"/followers")
  }

  getFollowed(user: User) {
    return this.http.get<User[]>(this.apiUrl+"/"+user.id+"/followed")
  }

  deleteUser(user:User){
    return this.http.delete<User>(this.apiUrl + '/' + user.id)
  }

}
