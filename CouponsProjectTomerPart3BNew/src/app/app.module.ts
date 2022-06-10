import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeModule } from './features/home/home.module';
import { Page404Module } from './features/page404/page404.module';
import { AdminModule } from './features/admin/admin.module';
import { CompanyModule } from './features/company/company.module';
import { CustomerModule } from './features/customer/customer.module';

@NgModule({
  declarations: [AppComponent],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    HomeModule,
    Page404Module,
    AdminModule,
    CompanyModule,
    CustomerModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
