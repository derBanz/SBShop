import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css', '../app.component.css'],
})
export class HomeComponent {
  constructor(private http: HttpClient) {}

  initializeData() {
    console.log('Calling initialization');
    this.http.post('http://localhost:8080/init-data', '').subscribe((r) => {});
    console.log('Finished initialization');
  }
}
