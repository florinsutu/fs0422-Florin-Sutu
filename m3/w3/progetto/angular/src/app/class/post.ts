export class Post {
  id:number | undefined;
  userId:number | undefined;
  title:string;
  content:string;
  like:boolean

  constructor(title:string, content:string, like:boolean = false) {
    this.title = title;
    this.content = content;
    this.like = like;
  }
}
