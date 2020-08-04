import { Component, OnInit } from '@angular/core';

import { StockExchange } from '../../../models/StockExchange';

@Component({
  selector: 'app-create-stock-exchange',
  templateUrl: './create-stock-exchange.component.html',
  styleUrls: ['./create-stock-exchange.component.css']
})
export class CreateStockExchangeComponent implements OnInit {

  stockExchange: StockExchange = {
    name: '',
    description: '',
    address: '',
    remarks : ''
  };

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit({value, valid}: {value: StockExchange, valid: boolean}) {
    if(!valid) {
      
    }
  }
}
