import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-article-update',
  templateUrl: './article-update.component.html',
  styleUrls: ['./article-update.component.css', '../app.component.css'],
})
export class ArticleUpdateComponent {
  article: any = {};
  articleId: String | undefined;

  constructor(
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.articleId = params['articleId'];
    });
    this.http
      .get<any>('http://localhost:8080/article/' + this.articleId)
      .subscribe((data) => {
        this.article = { ...data }; // Assign the fetched data to the article property
      });
  }

  submitForm() {
    this.http
      .put('http://localhost:8080/article/' + this.articleId, this.article)
      .subscribe(() => {
        this.router.navigate(['/articles']);
      });
  }

  cancel() {
    this.router.navigate(['/articles']);
  }
}
