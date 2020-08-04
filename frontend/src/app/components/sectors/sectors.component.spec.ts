import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SectorsComponent } from './sectors.component';

describe('SectorsComponent', () => {
  let component: SectorsComponent;
  let fixture: ComponentFixture<SectorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SectorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SectorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
