import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ArticleListComponent } from './article-list/article-list.component';
import { ClientListComponent } from './client-list/client-list.component';
import { PurchaseListComponent } from './purchase-list/purchase-list.component';
import { AddressListComponent } from './address-list/address-list.component';
import { ArticleCreateComponent } from './article-create/article-create.component';
import { ArticleUpdateComponent } from './article-update/article-update.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'articles', component: ArticleListComponent },
  { path: 'articles/create', component: ArticleCreateComponent },
  { path: 'articles/:articleId/update', component: ArticleUpdateComponent },
  { path: 'clients', component: ClientListComponent },
  { path: 'clients/:clientId/purchases', component: PurchaseListComponent },
  { path: 'clients/:clientId/addresses', component: AddressListComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
