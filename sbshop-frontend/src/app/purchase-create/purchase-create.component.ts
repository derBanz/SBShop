import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-purchase-create',
  templateUrl: './purchase-create.component.html',
  styleUrls: ['./purchase-create.component.css', '../app.component.css'],
})
export class PurchaseCreateComponent {
  articles: any[] = [];
  clientId: String | undefined;
  purchase: any = {};
  purchaseId: Number | undefined;
  url: String | undefined;
  confirmed: boolean = false;

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.clientId = params['clientId'];
    });
    this.purchase['clientId'] = this.clientId;
    this.purchase['deliveryType'] = '';
    this.purchase['paymentMethod'] = '';
    this.purchase['isGift'] = false;
    this.purchase['isConfirmed'] = false;
    this.url = '/clients/' + this.clientId + '/purchases';
    this.purchaseId = 0;
    this.getArticles();
  }

  getArticles() {
    this.http.get<any>('http://localhost:8080/article').subscribe((data) => {
      this.articles = data.map((article: any) => {
        return { ...article };
      });
    });
  }

  submitForm(confirmed: boolean) {
    this.confirmed = confirmed;
    this.http
      .post('http://localhost:8080/purchase', this.purchase)
      .subscribe((response: any) => {
        this.purchaseId = response.id;
        this.generateItems();
      });
  }

  generateItems() {
    const selectedArticles = this.articles.filter((article) => {
      return article.count > 0;
    });
    const purchaseItems = selectedArticles.map((article) => {
      return {
        articleId: article.id,
        purchaseId: this.purchaseId,
        quantity: article.count,
      };
    });
    this.createItems(purchaseItems);
  }

  createItems(items: any[]) {
    this.http.post('http://localhost:8080/item', items).subscribe(() => {
      if (this.confirmed) {
        this.router.navigate([this.url + '/' + this.purchaseId + '/options']);
      } else {
        this.router.navigate([this.url]);
      }
    });
  }

  cancel() {
    this.router.navigate([this.url]);
  }
}
