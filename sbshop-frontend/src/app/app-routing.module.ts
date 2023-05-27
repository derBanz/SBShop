import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AddressListComponent } from './address-list/address-list.component';
import { AddressCreateComponent } from './address-create/address-create.component';
import { AddressUpdateComponent } from './address-update/address-update.component';
import { ArticleListComponent } from './article-list/article-list.component';
import { ArticleCreateComponent } from './article-create/article-create.component';
import { ArticleUpdateComponent } from './article-update/article-update.component';
import { ClientListComponent } from './client-list/client-list.component';
import { ClientCreateComponent } from './client-create/client-create.component';
import { ClientUpdateComponent } from './client-update/client-update.component';
import { PurchaseListComponent } from './purchase-list/purchase-list.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'articles', component: ArticleListComponent },
  { path: 'articles/create', component: ArticleCreateComponent },
  { path: 'articles/:articleId/update', component: ArticleUpdateComponent },
  { path: 'clients', component: ClientListComponent },
  { path: 'clients/create', component: ClientCreateComponent },
  { path: 'clients/:clientId/update', component: ClientUpdateComponent },
  { path: 'clients/:clientId/purchases', component: PurchaseListComponent },
  { path: 'clients/:clientId/addresses', component: AddressListComponent },
  {
    path: 'clients/:clientId/addresses/create',
    component: AddressCreateComponent,
  },
  {
    path: 'clients/:clientId/addresses/:addressId/update',
    component: AddressUpdateComponent,
  },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
