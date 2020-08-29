import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DelatnostDetailComponent } from './delatnost-detail.component';

describe('DelatnostDetailComponent', () => {
  let component: DelatnostDetailComponent;
  let fixture: ComponentFixture<DelatnostDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DelatnostDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DelatnostDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
