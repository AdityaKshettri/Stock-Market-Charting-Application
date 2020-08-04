import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { Company } from '../models/Company';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { StockPrice } from '../models/StockPrice';
import { Comparison } from '../models/Comparison';

@Injectable({providedIn: 'root'})
export class StockPriceService {

  url: string;

  constructor(private http: HttpClient, private router: Router) {
    this.url = 'http://stockpriceservice-env.eba-ivrhmdf7.ap-south-1.elasticbeanstalk.com/stockPrices';
  }

  public getStockPrices(): Observable<StockPrice[]> {
    return this.http.get<StockPrice[]>(this.url);
  }

  getStockPrice(id: string): Observable<StockPrice> {
    return this.http.get<StockPrice>(this.url + id);
  }

  addStockPriceList(stockPrices: StockPrice[]) {
    this.http.post<StockPrice[]>(this.url, stockPrices)
      .subscribe(response => response);
  }

  updateStockPrice(stockPrice: StockPrice) {
    this.http.put(this.url, stockPrice)
      .subscribe(response => {
        this.router.navigate(['/stock-prices']);
      });
  }

  deleteStockPrice(id: string) {
    this.http.delete(this.url + id)
      .subscribe(response => {
        this.router.navigate(['/stock-prices']);
      });
  }

  getCompanyStockPrices(comparsion: Comparison) {
    return this.http.post<StockPrice[]>(this.url + "/compareCompany", comparsion);
  }

  getSectorStockPrices(comparsion: Comparison) {
    return this.http.post<StockPrice[]>(this.url + "/compareSector", comparsion);
  }
}

