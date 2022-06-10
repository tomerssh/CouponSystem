import { NgModule } from '@angular/core';

import { CompanyRoutingModule } from './company-routing.module';
import { CompanyComponent } from './components/company/company.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ContentComponent } from './components/content/content.component';

@NgModule({
  declarations: [CompanyComponent, ContentComponent],
  imports: [CompanyRoutingModule, SharedModule],
})
export class CompanyModule {}
