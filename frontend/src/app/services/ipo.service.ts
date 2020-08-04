import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { IPO } from '../models/IPO';

//const BACKEND_URL = environment.apiUrl + '/company-service/ipos/';

@Injectable({providedIn: 'root'})
export class IpoService {

  url: string;

  constructor(private http: HttpClient, private router: Router) {
    this.url = 'http://companyservice-env.eba-2jpmpvgp.ap-south-1.elasticbeanstalk.com/ipos/';
  }

  public getIpos(): Observable<IPO[]> {
    return this.http.get<IPO[]>(this.url);
  }

  getIpo(id: string): Observable<IPO> {
    return this.http.get<IPO>(this.url + id);
  }

  addIpo(ipo: IPO) {
    this.http.post<IPO>(this.url, ipo)
      .subscribe((responseData) => {
        this.router.navigate(['/ipos']);
      });
  }

  updateIpo(ipo: IPO) {
    this.http.put(this.url, ipo)
      .subscribe(response => {
        this.router.navigate(['/ipos']);
      });
  }

  deleteIpo(id: string) {
    this.http.delete(this.url + id)
      .subscribe(response => {
        this.router.navigate(['/ipos']);
      });
  }
}

