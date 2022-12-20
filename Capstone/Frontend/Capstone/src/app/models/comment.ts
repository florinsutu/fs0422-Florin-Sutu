import { Post } from "./post"
import { User } from "./user"

export class Comment {

  id!:number
  text!: string
  sender!: User
  post!: Post
  date!: Date
  edited!: boolean
  
}
