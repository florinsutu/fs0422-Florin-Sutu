import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthResponse } from '../models/auth-response';
import { Message } from '../models/message';
import { MessageDto } from '../models/messageDto';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';
import { MessageService } from '../services/message.service';
import { UserService } from '../services/user.service';

type Chat = {
  senderId: number,
  receiverId: number
}

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  loggedUser!: AuthResponse
  textedUser!: User
  messageList: Message[] = [];
  currentMessage: Message = new Message()

  constructor(
    private authSvc: AuthService,
    private userSvc: UserService,
    private route: ActivatedRoute,
    private messageSvc: MessageService

  ) { }

  sendMessage() {
    let message: MessageDto = {
      text: this.currentMessage.text,
      senderId: this.loggedUser.id,
      receiverId: this.textedUser.id
    }

    this.messageSvc.addMessage(message).subscribe(res => {
      this.messageList.push(res)
      this.currentMessage = new Message()
    })
  }

  addPost(): void {

    let message: MessageDto = {
      text: this.currentMessage.text,
      senderId: this.loggedUser.id,
      receiverId: this.textedUser.id
    }

    this.messageSvc.addMessage(message).subscribe(res => {
      this.messageList.push(res)
      this.currentMessage = new Message()
      /*       Swal.fire({
              position: 'top-end',
              icon: 'success',
              title: 'New Post Created',
              showConfirmButton: false,
              timer: 2000
            }) */
    })
  }

  checkLog(): boolean {
    return this.authSvc.isUserLogged()
  }

  assignRole(id: number): boolean {
    return this.textedUser.id == id;
  }

  ngOnInit(): void {

    let textedUserId = this.route.snapshot.paramMap.get('id')

    if (this.checkLog() && textedUserId) {

      this.loggedUser = this.authSvc.getAccessData()
      this.userSvc.getUserById(textedUserId)
        .subscribe(res => this.textedUser = res)

      this.messageSvc.getAllMessagesOf(this.loggedUser.id + "", textedUserId).subscribe(
        res => this.messageList = res
      )
    }

  }

}
