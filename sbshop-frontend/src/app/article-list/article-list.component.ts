import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.css', '../app.component.css'],
})
export class ArticleListComponent implements OnInit {
  articles: any[] = [];

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit() {
    this.getArticles();
  }

  getArticles() {
    this.http.get<any>('http://localhost:8080/article').subscribe((data) => {
      this.articles = data.map((article: any) => {
        return { ...article };
      });
    });
  }

  createArticle() {
    this.router.navigate(['/articles/create']);
  }

  editArticle(article: any) {
    this.router.navigate(['/articles/' + article.id + '/update']);
  }

  deleteArticle(article: any) {
    this.http
      .delete('http://localhost:8080/article/' + article.id)
      .subscribe((s) => {
        this.router
          .navigateByUrl('/', { skipLocationChange: true })
          .then(() => {
            this.router.navigate(['/articles']);
          });
      });
  }
}
