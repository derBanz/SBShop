import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ArticleListComponent } from './article-list/article-list.component';
import { ClientListComponent } from './client-list/client-list.component';
import { PurchaseListComponent } from './purchase-list/purchase-list.component';
import { AddressListComponent } from './address-list/address-list.component';
import { ArticleCreateComponent } from './article-create/article-create.component';
import { ArticleUpdateComponent } from './article-update/article-update.component';
import { AddressCreateComponent } from './address-create/address-create.component';
import { AddressUpdateComponent } from './address-update/address-update.component';
import { ClientCreateComponent } from './client-create/client-create.component';
import { ClientUpdateComponent } from './client-update/client-update.component';
import { PurchaseCreateComponent } from './purchase-create/purchase-create.component';
import { PurchaseUpdateComponent } from './purchase-update/purchase-update.component';
import { PurchaseOptionsComponent } from './purchase-options/purchase-options.component';
import { PurchaseReadComponent } from './purchase-read/purchase-read.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ArticleListComponent,
    ClientListComponent,
    PurchaseListComponent,
    AddressListComponent,
    ArticleCreateComponent,
    ArticleUpdateComponent,
    AddressCreateComponent,
    AddressUpdateComponent,
    ClientCreateComponent,
    ClientUpdateComponent,
    PurchaseCreateComponent,
    PurchaseUpdateComponent,
    PurchaseOptionsComponent,
    PurchaseReadComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [DatePipe],
  bootstrap: [AppComponent],
})
export class AppModule {}
