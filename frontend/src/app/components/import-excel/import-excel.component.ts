import { Component, OnInit } from '@angular/core';
import * as XLSX from 'xlsx';

import { StockPrice } from '../../models/StockPrice';
import { StockPriceService } from '../../services/stock-price.service';

@Component({
  selector: 'app-import-excel',
  templateUrl: './import-excel.component.html',
  styleUrls: ['./import-excel.component.css']
})
export class ImportExcelComponent implements OnInit {

  file: File;
  arrayBuffer: any;
  fileList: any;
  numberOfRecords: number;
  stockPrices: StockPrice[] = [];
  stockPrice: StockPrice;
  isUploaded: boolean = false;
  companyCode: string;
  stockExchangeName: string;
  fromDate: string;
  toDate: string;

  constructor(private stockPriceService: StockPriceService) { }

  ngOnInit(): void {
  }

  onUpload(event) {
    this.file= event.target.files[0];
    let fileReader = new FileReader();
    fileReader.readAsArrayBuffer(this.file);
    fileReader.onload = (e) => {
      this.arrayBuffer = fileReader.result;
      var data = new Uint8Array(this.arrayBuffer);
      var arr = new Array();
      for(var i = 0; i != data.length; ++i) {
        arr[i] = String.fromCharCode(data[i]);
      }
      var bstr = arr.join("");
      var workbook = XLSX.read(bstr, {type:"binary"});
      var first_sheet_name = workbook.SheetNames[0];
      var worksheet = workbook.Sheets[first_sheet_name];
      var records = XLSX.utils.sheet_to_json(worksheet,{raw:true});
      this.numberOfRecords = records.length;
      console.log(this.numberOfRecords);
      records.filter(record => {
        this.stockPrice = {
          companyCode: record["Company Code"],
          stockExchangeName: record["Stock Exchange"],
          price: record["Price Per Share(in Rs)"],
          date: record["Date"].trim(),
          time: record["Time"].trim()
        }
        this.stockPrices.push(this.stockPrice);
      });
      console.log(this.stockPrices);
      this.companyCode = this.stockPrices[0].companyCode;
      this.stockExchangeName = this.stockPrices[0].stockExchangeName;
      this.fromDate = this.stockPrices[0].date;
      this.toDate = this.stockPrices[this.numberOfRecords-1].date;
      this.isUploaded = true;
    }
    this.stockPriceService.addStockPriceList(this.stockPrices);
  }

  importAgain() {
    this.isUploaded = false;
  }
}
