import { Component, OnInit, Inject, ViewChild, ElementRef, AfterViewChecked } from '@angular/core';

import { MesssagesService } from '../../services/messsages.service';
import { MAT_DIALOG_DATA, MatChipList } from '@angular/material';

@Component({
  selector: 'app-messages-dialog',
  templateUrl: './messages-dialog.component.html',
  styleUrls: ['./messages-dialog.component.css']
})
export class MessagesDialogComponent implements OnInit, AfterViewChecked {

  messages = [];
  newMessage = '';
  private scroll = false;
  @ViewChild('messageContainer') messageContainer: ElementRef;

  constructor(private messageService: MesssagesService,
              @Inject(MAT_DIALOG_DATA) private data: number) { }

  ngOnInit() {
    this.messageService.getMessages(this.data).subscribe(
      messages => this.messages = messages
    );
  }

  ngAfterViewChecked() {
    if (this.scroll) {
      this.scroll = false;
      this.messageContainer.nativeElement.scrollTop = this.messageContainer.nativeElement.scrollHeight;
    }
  }

  onSend() {
    this.messageService.sendMessage(this.data, this.newMessage)
      .subscribe(message => {
        this.messages.push(message);
        this.newMessage = '';
        this.scroll = true;
      });
  }

}
