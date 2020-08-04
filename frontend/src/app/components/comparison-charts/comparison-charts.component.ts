import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';

import { Comparison } from '../../models/Comparison';
import { StockPriceService } from 'src/app/services/stock-price.service';

@Component({
  selector: 'app-comparison-charts',
  templateUrl: './comparison-charts.component.html',
  styleUrls: ['./comparison-charts.component.css']
})
export class ComparisonChartsComponent implements OnInit {

  chart: any = [];

  comparison: Comparison = {
    name: '',
    stockExchangeName: '',
    fromPeriod: '',
    toPeriod: '',
    periodicity: ''
  }

  constructor(private stockPriceService: StockPriceService) { }

  ngOnInit(): void {
  }

  onSubmit({value, valid}: {value: Comparison, valid: boolean}) {
    if(!valid) {

    }
    else {
      console.log(value);
      this.stockPriceService.getCompanyStockPrices(value)
        .subscribe(response => {
          console.log(response);
          let prices = response.map(res => res.price);
          let dates = response.map(res => res.date);
          console.log(prices);
          console.log(dates);

          this.chart = new Chart('canvas', {
            type: 'line',
            data: {
              labels: dates,
              datasets: [
                {
                  data: prices,
                  borderColor: "#3cba9f",
                  fill: false
                },,
              ]
            },
            options: {
              legend: {
                display: false
              },
              scales: {
                xAxes: [{
                  display: true
                }],
                yAxes: [{
                  display: true
                }],
              }
            }
          });
        });

    }
  }

}
