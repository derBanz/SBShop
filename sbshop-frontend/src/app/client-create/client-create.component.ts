import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client-create',
  templateUrl: './client-create.component.html',
  styleUrls: ['./client-create.component.css', '../app.component.css'],
})
export class ClientCreateComponent {
  client: any = {};

  constructor(private http: HttpClient, private router: Router) {}

  submitForm() {
    this.http
      .post('http://localhost:8080/client', this.client)
      .subscribe(() => {
        this.router.navigate(['/clients']);
      });
  }

  cancel() {
    this.router.navigate(['/clients']);
  }
}
