import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { MaterialModule } from 'src/app/material.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { LoginComponent } from './components/login/login.component';
import { ContentComponent } from './components/content/content.component';

@NgModule({
  declarations: [
    LoginComponent,
    ContentComponent
  ],
  imports: [CommonModule, LoginRoutingModule, MaterialModule, SharedModule],
})
export class LoginModule {}
