import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { InfraccionDetailComponent } from 'app/entities/infraccion/infraccion-detail.component';
import { Infraccion } from 'app/shared/model/infraccion.model';

describe('Component Tests', () => {
  describe('Infraccion Management Detail Component', () => {
    let comp: InfraccionDetailComponent;
    let fixture: ComponentFixture<InfraccionDetailComponent>;
    const route = ({ data: of({ infraccion: new Infraccion('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [InfraccionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InfraccionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InfraccionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load infraccion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.infraccion).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
