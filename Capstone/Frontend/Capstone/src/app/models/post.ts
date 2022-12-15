import { User } from "./user";

export class Post {

  id!:number;
  author!:User
  title!:string;
  text!:string;
  edited!:boolean;
  image!:any;
  likes!:User[];
  date!:Date;

}
