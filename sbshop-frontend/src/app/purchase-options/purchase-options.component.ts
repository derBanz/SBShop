import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-purchase-options',
  templateUrl: './purchase-options.component.html',
  styleUrls: ['./purchase-options.component.css', '../app.component.css'],
})
export class PurchaseOptionsComponent {
  addresses: any[] = [];
  clientId: Number | undefined;
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
      this.clientId = parseInt(params['clientId']);
      this.purchaseId = parseInt(params['purchaseId']);
    });
    this.purchase['clientId'] = this.clientId;
    this.purchase['purchaseId'] = this.purchaseId;
    this.purchase['isConfirmed'] = false;
    this.url = '/clients/' + this.clientId + '/purchases';
    this.getPurchase();
    this.getAddresses();
  }

  getPurchase() {
    this.http
      .get('http://localhost:8080/purchase/' + this.purchaseId)
      .subscribe((response: any) => {
        this.purchase['deliveryType'] = response.deliveryType;
        this.purchase['paymentMethod'] = response.paymentMethod;
        this.purchase['addressId'] = response.addressId;
        this.purchase['isGift'] = response.isGift;
      });
  }

  getAddresses() {
    this.http
      .get('http://localhost:8080/address/client/' + this.clientId)
      .subscribe((response: any) => {
        this.addresses = response.map((address: any) => {
          return { ...address };
        });
      });
  }

  submitForm(confirmed: boolean) {
    this.confirmed = confirmed;
    this.purchase['isConfirmed'] = confirmed;
    this.http
      .put('http://localhost:8080/purchase/' + this.purchaseId, this.purchase)
      .subscribe(() => {
        this.router.navigate([this.url]);
      });
  }

  cancel() {
    this.router.navigate([this.url]);
  }
}
