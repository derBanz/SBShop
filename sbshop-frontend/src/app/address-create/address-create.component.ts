import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-address-create',
  templateUrl: './address-create.component.html',
  styleUrls: ['./address-create.component.css', '../app.component.css'],
})
export class AddressCreateComponent {
  clientId: String | undefined;
  newAddress: any = {};
  url: String | undefined;

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.clientId = params['clientId'];
      this.newAddress['clientId'] = this.clientId;
      this.url = '/clients/' + this.clientId + '/addresses';
    });
  }

  submitForm() {
    this.http
      .post('http://localhost:8080/address', this.newAddress)
      .subscribe(() => {
        this.router.navigate([this.url]);
      });
  }

  cancel() {
    this.router.navigate([this.url]);
  }
}
