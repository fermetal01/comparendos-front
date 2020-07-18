import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { GeneroDetailComponent } from 'app/entities/genero/genero-detail.component';
import { Genero } from 'app/shared/model/genero.model';

describe('Component Tests', () => {
  describe('Genero Management Detail Component', () => {
    let comp: GeneroDetailComponent;
    let fixture: ComponentFixture<GeneroDetailComponent>;
    const route = ({ data: of({ genero: new Genero('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [GeneroDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeneroDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeneroDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load genero on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.genero).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
