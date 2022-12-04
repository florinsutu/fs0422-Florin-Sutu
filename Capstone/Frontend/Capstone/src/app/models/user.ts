export interface User {
  
  id:number;
  username:string;
  email:string;
  password:string;
  active:boolean;
  roles: Array<string>;

  name:string;
  lastname:string;
  description:string;
  isOnline: boolean;
  isPrivate: boolean;

  followers: Array<User>;
  followed: Array<User>;

}
