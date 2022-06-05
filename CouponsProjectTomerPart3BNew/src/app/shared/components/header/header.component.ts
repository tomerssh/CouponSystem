import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  @Input()
  content: string = '';
  @Input()
  btn: string = '';
  @Input()
  btnUrl: string = '';

  constructor() {}

  ngOnInit(): void {}
}
