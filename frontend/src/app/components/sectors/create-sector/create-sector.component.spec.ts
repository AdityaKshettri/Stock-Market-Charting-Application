import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSectorComponent } from './create-sector.component';

describe('CreateSectorComponent', () => {
  let component: CreateSectorComponent;
  let fixture: ComponentFixture<CreateSectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateSectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateSectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
