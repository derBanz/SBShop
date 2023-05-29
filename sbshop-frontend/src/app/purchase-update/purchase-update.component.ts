import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-purchase-update',
  templateUrl: './purchase-update.component.html',
  styleUrls: ['./purchase-update.component.css', '../app.component.css'],
})
export class PurchaseUpdateComponent {
  articlesInit: any[] = [];
  articles: any[] = [];
  items: any[] = [];
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
      this.purchaseId = params['purchaseId'];
    });
    this.purchase['clientId'] = this.clientId;
    this.purchase['purchaseId'] = this.purchaseId;
    this.url = '/clients/' + this.clientId + '/purchases/';
    this.getArticles();
  }

  getArticles() {
    this.http.get<any>('http://localhost:8080/article').subscribe((data) => {
      this.articles = data.map((article: any) => {
        return { ...article, count: 0 };
      });
      this.getItems();
    });
  }

  getItems() {
    this.http
      .get<any>('http://localhost:8080/purchase/' + this.purchaseId + '/items')
      .subscribe((data) => {
        this.items = data.map((item: any) => {
          let article = this.articles.find(
            (article) => article.id === item.articleId
          );
          article['count'] += 1;
          return { ...item };
        });
        this.articlesInit = structuredClone(this.articles);
      });
  }

  submitForm(confirmed: boolean) {
    this.confirmed = confirmed;
    this.generateItems();
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
    this.updateItems(purchaseItems);
  }

  updateItems(items: any[]) {
    this.http.put('http://localhost:8080/item', items).subscribe(() => {
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
