import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Page404RoutingModule } from './page404-routing.module';
import { Page404Component } from './components/page404/page404.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    Page404Component
  ],
  imports: [
    CommonModule,
    Page404RoutingModule,
    SharedModule
  ]
})
export class Page404Module { }
