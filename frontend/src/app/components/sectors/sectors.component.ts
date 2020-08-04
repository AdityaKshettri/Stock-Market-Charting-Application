import { Component, OnInit } from '@angular/core';

import { Sector } from '../../models/Sector';
import { SectorService } from '../../services/sector.service';

@Component({
  selector: 'app-sectors',
  templateUrl: './sectors.component.html',
  styleUrls: ['./sectors.component.css']
})
export class SectorsComponent implements OnInit {

  sectors: Sector[];

  constructor(private sectorService: SectorService) { }

  ngOnInit(): void {
    this.sectorService.getSectors()
      .subscribe(response => {
        this.sectors = response;
      });
  }

  onDeleteClick(id: string) {
    this.sectorService.deleteSector(id);
  }
}
