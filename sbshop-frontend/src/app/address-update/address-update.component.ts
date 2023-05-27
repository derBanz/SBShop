import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-address-update',
  templateUrl: './address-update.component.html',
  styleUrls: ['./address-update.component.css', '../app.component.css'],
})
export class AddressUpdateComponent {
  address: any = {};
  clientId: String | undefined;
  addressId: String | undefined;
  url: String | undefined;

  constructor(
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.clientId = params['clientId'];
      this.addressId = params['addressId'];
      this.address['clientId'] = this.clientId;
      this.url = '/clients/' + this.clientId + '/addresses';
    });
    this.http
      .get<any>('http://localhost:8080/address/' + this.addressId)
      .subscribe((data) => {
        this.address = { ...data };
      });
  }

  submitForm() {
    this.http
      .put('http://localhost:8080/address/' + this.addressId, this.address)
      .subscribe(() => {
        this.router.navigate([this.url]);
      });
  }

  cancel() {
    this.router.navigate([this.url]);
  }
}
