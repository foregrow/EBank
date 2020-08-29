import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BankaDetailComponent } from './banka-detail.component';

describe('BankaDetailComponent', () => {
  let component: BankaDetailComponent;
  let fixture: ComponentFixture<BankaDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BankaDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BankaDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
