import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContructionComponent } from './contruction.component';

describe('ContructionComponent', () => {
  let component: ContructionComponent;
  let fixture: ComponentFixture<ContructionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContructionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContructionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
