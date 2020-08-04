import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateIpoComponent } from './create-ipo.component';

describe('CreateIpoComponent', () => {
  let component: CreateIpoComponent;
  let fixture: ComponentFixture<CreateIpoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateIpoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateIpoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
