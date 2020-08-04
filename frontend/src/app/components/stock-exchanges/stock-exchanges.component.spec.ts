import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockExchangesComponent } from './stock-exchanges.component';

describe('StockExchangesComponent', () => {
  let component: StockExchangesComponent;
  let fixture: ComponentFixture<StockExchangesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockExchangesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockExchangesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
