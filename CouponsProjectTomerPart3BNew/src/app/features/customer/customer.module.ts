import { NgModule } from '@angular/core';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerComponent } from './components/customer/customer.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ContentComponent } from './components/content/content.component';

@NgModule({
  declarations: [CustomerComponent, ContentComponent],
  imports: [CustomerRoutingModule, SharedModule],
})
export class CustomerModule {}
