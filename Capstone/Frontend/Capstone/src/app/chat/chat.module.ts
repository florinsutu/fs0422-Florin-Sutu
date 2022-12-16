import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChatRoutingModule } from './chat-routing.module';
import { ChatComponent } from './chat.component';
import { NgMaterialModule } from '../ng-material/ng-material.module';
import { FormsModule } from '@angular/forms';
import { PrivateChatComponent } from './private-chat/private-chat.component';


@NgModule({
  declarations: [
    ChatComponent,
    PrivateChatComponent
  ],
  imports: [
    CommonModule,
    ChatRoutingModule,
    FormsModule,
    NgMaterialModule
  ]
})
export class ChatModule { }
