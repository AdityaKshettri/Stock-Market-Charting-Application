import { Component, OnInit } from '@angular/core';

import { Company } from '../../../models/Company';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit {

  company: Company = {
    name: '',
    code: '',
    turnover: '',
    ceo: '',
    boardOfDirectors: '',
    sectorName: '',
    description: ''
  };

  constructor(private companyService: CompanyService) { }

  ngOnInit(): void {
  }

  onSubmit({value, valid}: {value: Company, valid: boolean}) {
    if(!valid) {

    }
    else {
      this.companyService.addCompany(value);
    }
  }
}
