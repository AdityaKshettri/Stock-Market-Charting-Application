import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { Company } from '../models/Company';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Sector } from '../models/Sector';

//const BACKEND_URL = environment.apiUrl + '/sector-service/sectors/';

@Injectable({providedIn: 'root'})
export class SectorService {

  url: string;

  constructor(private http: HttpClient, private router: Router) {
    this.url = 'http://sectorservice-env.eba-uxpehump.ap-south-1.elasticbeanstalk.com/sectors';
  }

  getSectors(): Observable<Sector[]> {
    return this.http.get<Sector[]>(this.url);
  }

  getSector(id: string): Observable<Sector> {
    return this.http.get<Sector>(this.url + id);
  }

  getSectorCompanies(id: string): Observable<Company[]> {
    return this.http.get<Company[]>(this.url + id + "/companies");
  }

  addSector(sector: Sector) {
    this.http.post<Sector>(this.url, sector)
      .subscribe((responseData) => {
        this.router.navigate(['/sectors']);
      });
  }

  updateSector(sector: Sector) {
    this.http.put(this.url, sector)
      .subscribe(response => {
        this.router.navigate(['/sectors']);
      });
  }

  deleteSector(id: string) {
    this.http.delete(this.url + id)
      .subscribe(response => {
        this.router.navigate(['/sectors']);
      });
  }
}

