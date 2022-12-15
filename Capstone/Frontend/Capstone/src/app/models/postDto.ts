import { FileHandle } from "./file-model";
import { User } from "./user";

export interface PostDto {

  authorId?:number;
  title:string;
  text:string;
  image?: File;

}
