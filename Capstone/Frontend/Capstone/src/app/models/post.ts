import { User } from "./user";

export class Post {
  id:number | undefined;
  author!:User
  title!:string;
  text!:string;
  edited!:boolean;
  image!:any;
  likes!:User[];
  date!:Date;

  /* constructor(title:string, content:string) {
    this.title = title;
    this.text = content;
  } */
}
