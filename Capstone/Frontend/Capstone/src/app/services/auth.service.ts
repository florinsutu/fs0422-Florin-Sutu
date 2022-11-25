import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';

type AuthResponse = {
  token: string,
  id:number,
  username:string,
  roles:string[],
  data:Date
}

 type ILogin = {
  username: string,
  password: string
}

 type IRegister = {
  name:string,
  email: string,
  password: string
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }

  apiUrl:string = 'http://localhost:8080/auth'

  register(registerData:IRegister){
    return this.http.post<AuthResponse>(this.apiUrl+'/register', registerData)
  }


  login(loginData:ILogin){
    return this.http.post<AuthResponse>(this.apiUrl+'/login', loginData)
  }

  isUserLogged():boolean{
    return localStorage.getItem('access') != null
  }

  getLoggedUser():User{
    let db = localStorage.getItem('access')
    return db ? JSON.parse(db).user : null
  }

  getAccessToken():string{
    let db = localStorage.getItem('access')
    console.log(db);

    return db ? JSON.parse(db).accessToken : null
  }

  saveAccessData(data:AuthResponse){
    localStorage.setItem('access',JSON.stringify(data))
  }

  logOut(){
    localStorage.removeItem('access')
  }

}

