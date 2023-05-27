import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-article-create',
  templateUrl: './article-create.component.html',
  styleUrls: ['./article-create.component.css', '../app.component.css'],
})
export class ArticleCreateComponent {
  newArticle: any = {};

  constructor(private http: HttpClient, private router: Router) {}

  submitForm() {
    this.http
      .post('http://localhost:8080/article', this.newArticle)
      .subscribe(() => {
        this.router.navigate(['/articles']);
      });
  }

  cancel() {
    this.router.navigate(['/articles']);
  }
}
