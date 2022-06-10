import { NgModule } from '@angular/core';

import { LoginRoutingModule } from './login-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { LoginComponent } from './components/login/login.component';
import { ContentComponent } from './components/content/content.component';

@NgModule({
  declarations: [LoginComponent, ContentComponent],
  imports: [LoginRoutingModule, SharedModule],
})
export class LoginModule {}
