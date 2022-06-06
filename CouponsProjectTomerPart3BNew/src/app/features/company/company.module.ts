import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompanyRoutingModule } from './company-routing.module';
import { CompanyComponent } from './components/company/company.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ContentComponent } from './components/content/content.component';
import { MaterialModule } from 'src/app/material.module';


@NgModule({
  declarations: [
    CompanyComponent,
    ContentComponent
  ],
  imports: [
    CommonModule,
    CompanyRoutingModule,
    SharedModule,
    MaterialModule
  ]
})
export class CompanyModule { }
