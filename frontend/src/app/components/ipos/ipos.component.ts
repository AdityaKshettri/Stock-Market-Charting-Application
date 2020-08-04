import { Component, OnInit } from '@angular/core';

import { IPO } from '../../models/IPO';
import { IpoService } from '../../services/ipo.service';

@Component({
  selector: 'app-ipos',
  templateUrl: './ipos.component.html',
  styleUrls: ['./ipos.component.css']
})
export class IposComponent implements OnInit {

  ipos: IPO[];

  constructor(private ipoService: IpoService) { }

  ngOnInit(): void {
    this.ipoService.getIpos()
      .subscribe(response => {
        this.ipos = response;
      })
  }

  onDeleteClick(id: string) {
    this.ipoService.deleteIpo(id);
  }
}
