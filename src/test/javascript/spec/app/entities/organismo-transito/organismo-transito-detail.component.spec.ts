import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { OrganismoTransitoDetailComponent } from 'app/entities/organismo-transito/organismo-transito-detail.component';
import { OrganismoTransito } from 'app/shared/model/organismo-transito.model';

describe('Component Tests', () => {
  describe('OrganismoTransito Management Detail Component', () => {
    let comp: OrganismoTransitoDetailComponent;
    let fixture: ComponentFixture<OrganismoTransitoDetailComponent>;
    const route = ({ data: of({ organismoTransito: new OrganismoTransito('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [OrganismoTransitoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OrganismoTransitoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrganismoTransitoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load organismoTransito on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.organismoTransito).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
