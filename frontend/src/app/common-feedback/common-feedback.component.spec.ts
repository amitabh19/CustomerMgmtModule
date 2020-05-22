import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommonFeedbackComponent } from './common-feedback.component';

describe('CommonFeedbackComponent', () => {
  let component: CommonFeedbackComponent;
  let fixture: ComponentFixture<CommonFeedbackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommonFeedbackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommonFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
