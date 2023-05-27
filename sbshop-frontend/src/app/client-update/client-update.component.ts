import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-client-update',
  templateUrl: './client-update.component.html',
  styleUrls: ['./client-update.component.css', '../app.component.css'],
})
export class ClientUpdateComponent {
  client: any = {};
  clientId: String | undefined;

  constructor(
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute,
    private datePipe: DatePipe
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.clientId = params['clientId'];
    });
    this.http
      .get<any>('http://localhost:8080/client/' + this.clientId)
      .subscribe((data) => {
        this.client = { ...data };
        let dob = new Date(this.client.dateOfBirth);
        this.client.dateOfBirth = this.datePipe.transform(dob, 'yyyy-MM-dd');
      });
  }

  submitForm() {
    this.http
      .put('http://localhost:8080/client/' + this.clientId, this.client)
      .subscribe(() => {
        this.router.navigate(['/clients']);
      });
  }

  cancel() {
    this.router.navigate(['/clients']);
  }
}
