export class Post {
  id:number | undefined;
  title!:string;
  body!:string
  active!:boolean;


  constructor( title:string, body:string, active:boolean=true) {
    this.title = title
    this.body = body
    this.active = active
  
}
}
