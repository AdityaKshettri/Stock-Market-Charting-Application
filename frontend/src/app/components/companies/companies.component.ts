import { Component, OnInit } from '@angular/core';

import { Company } from '../../models/Company';
import { CompanyService } from '../../services/company.service';

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css']
})
export class CompaniesComponent implements OnInit {

  companies: Company[];

  constructor(private companyService: CompanyService) { }

  ngOnInit(): void {
    this.companyService.getCompanies()
      .subscribe((response) => {
        this.companies = response;
      });
  }

  onDeleteClick(id: string) {
    this.companyService.deleteCompany(id);
  }

}
