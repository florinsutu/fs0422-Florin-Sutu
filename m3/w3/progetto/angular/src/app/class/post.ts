export class Post {
  id:number | undefined;
  userId:number | undefined;
  title:string;
  content:string;

  constructor(title:string, content:string) {
    this.title = title;
    this.content = content;
  }
}
