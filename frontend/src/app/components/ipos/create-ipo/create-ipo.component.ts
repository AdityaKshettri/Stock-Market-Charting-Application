import { Component, OnInit } from '@angular/core';

import { IPO } from '../../../models/IPO';
import { IpoService } from 'src/app/services/ipo.service';

@Component({
  selector: 'app-create-ipo',
  templateUrl: './create-ipo.component.html',
  styleUrls: ['./create-ipo.component.css']
})
export class CreateIpoComponent implements OnInit {

  ipo: IPO = {
    companyName: '',
    stockExchangeName: '',
    price: 0,
    shares: 0,
    openDateTime: '',
    remarks: ''
  };

  constructor(private ipoService: IpoService) { }

  ngOnInit(): void {
  }

  onSubmit({value, valid}: {value: IPO, valid: boolean}) {
    if(!valid) {

    }
    else {
      this.ipoService.addIpo(value);
    }
  }

}
