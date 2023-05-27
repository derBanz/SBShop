import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-address-list',
  templateUrl: './address-list.component.html',
  styleUrls: ['./address-list.component.css', '../app.component.css'],
})
export class AddressListComponent implements OnInit {
  addresses: any[] = [];
  clientId: String | undefined;
  clientName: String | undefined;

  constructor(private http: HttpClient, private route: ActivatedRoute) {}

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
}
