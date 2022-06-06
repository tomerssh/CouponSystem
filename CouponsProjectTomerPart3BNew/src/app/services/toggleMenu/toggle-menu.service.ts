import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ToggleMenuService {
  menuSubject = new BehaviorSubject<boolean>(false);

  get menu$() {
    return this.menuSubject.asObservable();
  }

  constructor() {}

  toggle() {
    this.menuSubject.next(!this.menuSubject.value);
  }
}
