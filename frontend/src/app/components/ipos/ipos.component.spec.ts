import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IposComponent } from './ipos.component';

describe('IposComponent', () => {
  let component: IposComponent;
  let fixture: ComponentFixture<IposComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IposComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IposComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
