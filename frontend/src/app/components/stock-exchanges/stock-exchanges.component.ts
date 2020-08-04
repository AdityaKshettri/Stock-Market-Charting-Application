import { Component, OnInit } from '@angular/core';

import { StockExchange } from '../../models/StockExchange';
import { StockExchangeService } from '../../services/stock-exchange.service';

@Component({
  selector: 'app-stock-exchanges',
  templateUrl: './stock-exchanges.component.html',
  styleUrls: ['./stock-exchanges.component.css']
})
export class StockExchangesComponent implements OnInit {

  stockExchanges: StockExchange[];

  constructor(private stockExchangeService: StockExchangeService) { }

  ngOnInit(): void {
    this.stockExchangeService.getStockExchanges()
      .subscribe(response => {
        this.stockExchanges = response;
      });
  }

  onDeleteClick(id: string) {
    this.stockExchangeService.deleteStockExchange(id);
  }
}
