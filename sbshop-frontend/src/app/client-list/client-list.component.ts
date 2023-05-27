import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css', '../app.component.css'],
})
export class ClientListComponent implements OnInit {
  clients: any[] = [];

  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private router: Router
  ) {}

  ngOnInit() {
    this.getClients();
  }

  getClients() {
    this.http.get<any>('http://localhost:8080/client').subscribe((data) => {
      this.clients = data.map((client: any) => {
        const formattedDate = this.datePipe.transform(
          client.dateOfBirth,
          'dd. MMM yyyy'
        );
        return { ...client, dateOfBirth: formattedDate };
      });
    });
  }

  toggleClient(client: any) {
    this.clients.forEach((c) => {
      if (c !== client) {
        c.isActive = false;
      }
    });
    client.isActive = !client.isActive;
  }

  navigateToPurchases(clientId: number) {
    this.router.navigate(['/clients', clientId, 'purchases']);
  }

  navigateToAddresses(clientId: number) {
    this.router.navigate(['/clients', clientId, 'addresses']);
  }
}
