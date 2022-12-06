import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from '../models/message';
import { MessageDto } from '../models/messageDto';



@Injectable({
  providedIn: 'root'
})
export class MessageService {

  apiUrl: string = 'http://localhost:8080/api/messages'

  constructor(private http: HttpClient) { }

  getAllMessagesOf(id: string, id2:string): Observable<Message[]> {
    return this.http.get<Message[]>(this.apiUrl+"/chat/"+id+"/"+id2 )
  }

  addMessage(message: MessageDto) {
    return this.http.post<Message>(this.apiUrl, message)
  }
}
