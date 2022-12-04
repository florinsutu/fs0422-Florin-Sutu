import { User } from "./user";

export interface PostDto {

  authorId?:number|null;
  title:string;
  text:string;
  image?: File;

}
