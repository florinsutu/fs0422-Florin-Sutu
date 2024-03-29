export class User {
  [x: string]: any;

  id!:number;
  username!:string;
  email!:string;
  password!:string;
  active!:boolean;
  roles!: Array<string>;

  name!:string;
  lastname!:string;
  description!:string;
  isOnline!: boolean;
  isPrivate!: boolean;
  image:any;
  registration!:Date;

  followers!: Array<User>;
  followed!: Array<User>;

}
