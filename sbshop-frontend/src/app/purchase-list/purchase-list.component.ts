import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-purchase-list',
  templateUrl: './purchase-list.component.html',
  styleUrls: ['./purchase-list.component.css', '../app.component.css'],
})
export class PurchaseListComponent implements OnInit {
  purchases: any[] = [];
  clientId: String | undefined;
  clientName: String | undefined;
  url: String | undefined;

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router,
    private datePipe: DatePipe
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.clientId = params['clientId'];
    });
    this.url = '/clients/' + this.clientId + '/purchases/';
    this.getPurchases();
  }

  getPurchases() {
    this.http
      .get<any>('http://localhost:8080/client/' + this.clientId)
      .subscribe((data) => {
        this.clientName = data.name + ' ' + data.surname;
        this.purchases = data.purchases.map((purchase: any) => {
          const formattedDate = this.datePipe.transform(
            purchase.timeOfPurchase,
            'dd. MMM yyyy hh:mm'
          );
          return { ...purchase, timeOfPurchase: formattedDate };
        });
      });
  }

  createPurchase() {
    this.router.navigate(['./create'], { relativeTo: this.route });
  }

  readPurchase(purchase: any) {
    this.router.navigate([this.url + purchase.id + '/read']);
  }

  editPurchase(purchase: any) {
    this.router.navigate([this.url + purchase.id + '/update']);
  }

  confirmPurchase(purchase: any) {
    var canBeConfirmed = false;
    console.log(purchase);
    this.http
      .get('http://localhost:8080/purchase/' + purchase.id)
      .subscribe((response: any) => {
        canBeConfirmed =
          response['deliveryType'] != '' &&
          response['paymentMethod'] != '' &&
          response['addressId'] > 0;
        if (canBeConfirmed) {
          purchase['isConfirmed'] = true;
          this.http
            .put('http://localhost:8080/purchase/' + purchase.id, purchase)
            .subscribe(() => {
              this.router
                .navigateByUrl('/', { skipLocationChange: true })
                .then(() => {
                  this.router.navigate([this.url]);
                });
            });
        } else {
          this.router.navigate([this.url + purchase.id + '/options']);
        }
      });
  }

  cancelPurchase(purchase: any) {
    this.http
      .delete('http://localhost:8080/purchase/' + purchase.id)
      .subscribe(() => {
        this.router
          .navigateByUrl('/', { skipLocationChange: true })
          .then(() => {
            this.router.navigate([this.url]);
          });
      });
  }
}
