import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateStockExchangeComponent } from './create-stock-exchange.component';

describe('CreateStockExchangeComponent', () => {
  let component: CreateStockExchangeComponent;
  let fixture: ComponentFixture<CreateStockExchangeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateStockExchangeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateStockExchangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
