import { Post } from "./post"
import { User } from "./user"

export class Comment {
  [x: string]: any

  id!:number
  text!: string
  sender!: User
  post!: Post
  date!: Date
  edited!: boolean
}
