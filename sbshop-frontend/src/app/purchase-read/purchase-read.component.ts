import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-purchase-read',
  templateUrl: './purchase-read.component.html',
  styleUrls: ['./purchase-read.component.css', '../app.component.css'],
})
export class PurchaseReadComponent implements OnInit {
  articles: any[] = [];
  articlesActive: any[] = [];
  purchasePrice: Number | undefined;
  purchaseId: Number | undefined;
  clientId: Number | undefined;
  purchaseTime: any | undefined;

  constructor(
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute,
    private datePipe: DatePipe
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.clientId = parseInt(params['clientId']);
      this.purchaseId = parseInt(params['purchaseId']);
    });
    this.getPurchase();
    this.getArticles();
  }

  getPurchase() {
    this.http
      .get('http://localhost:8080/purchase/' + this.purchaseId)
      .subscribe((response: any) => {
        this.purchaseTime = this.datePipe.transform(
          response['timeOfPurchase'],
          'dd. MMM yyyy HH:mm'
        );
        this.purchasePrice = response['price'];
      });
  }

  getArticles() {
    this.http.get<any>('http://localhost:8080/article').subscribe((data) => {
      this.articles = data.map((article: any) => {
        return { ...article, quantity: 0 };
      });
      this.getItems();
    });
  }

  getItems() {
    this.http
      .get<any>('http://localhost:8080/purchase/' + this.purchaseId + '/items')
      .subscribe((response) => {
        response.map((item: any) => {
          let article = this.articles.find((art) => art.id === item.articleId);
          article['quantity'] += 1;
          article['price'] = item.price;
        });
        this.articles.map((art) => {
          if (art['quantity'] > 0) {
            this.articlesActive.push(art);
          }
        });
      });
  }

  goBack() {
    this.router.navigate(['/clients/' + this.clientId + '/purchases']);
  }
}
