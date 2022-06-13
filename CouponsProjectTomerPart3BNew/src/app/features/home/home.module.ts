import { NgModule } from '@angular/core';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './components/home/home.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ContentComponent } from './components/content/content.component';

@NgModule({
  declarations: [HomeComponent, ContentComponent],
  imports: [HomeRoutingModule, SharedModule],
})
export class HomeModule {}
