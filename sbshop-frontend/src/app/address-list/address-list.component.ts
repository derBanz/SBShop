import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-address-list',
  templateUrl: './address-list.component.html',
  styleUrls: ['./address-list.component.css', '../app.component.css'],
})
export class AddressListComponent implements OnInit {
  addresses: any[] = [];
  clientId: String | undefined;
  clientName: String | undefined;

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.clientId = params['clientId'];
    });
    this.getAddresses();
  }

  getAddresses() {
    this.http
      .get<any>('http://localhost:8080/client/' + this.clientId)
      .subscribe((data) => {
        this.clientName = data.name + ' ' + data.surname;
        this.addresses = data.addresses.map((address: any) => {
          return { ...address };
        });
      });
  }

  createAddress() {
    this.router.navigate(['./create'], { relativeTo: this.route });
  }

  editAddress(address: any) {
    this.router.navigate([
      '/clients/' + this.clientId + '/addresses/' + address.id + '/update',
    ]);
  }

  deleteAddress(address: any) {
    this.http
      .delete('http://localhost:8080/address/' + address.id)
      .subscribe((s) => {
        this.router
          .navigateByUrl('/', { skipLocationChange: true })
          .then(() => {
            this.router.navigate(['clients/' + this.clientId + '/addresses']);
          });
      });
  }
}
