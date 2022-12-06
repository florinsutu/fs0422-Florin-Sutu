import { User } from "./user";

export class Message {

  id!:number
  text!: string
  sender!: User
  receiver!: User
  date!: Date
  edited!: boolean

}
