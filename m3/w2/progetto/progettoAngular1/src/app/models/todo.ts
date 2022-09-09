import { ITodo } from "./itodo";

export class Todo implements ITodo{
  id!: number | undefined;
  title: string;
  completed: boolean = false; //una task già svolta non ha senso in una to do

  constructor(title: string) {
    this.title = title;
  }

}
