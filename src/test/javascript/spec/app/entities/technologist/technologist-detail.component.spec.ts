/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysComArchTestModule } from '../../../test.module';
import { TechnologistDetailComponent } from 'app/entities/technologist/technologist-detail.component';
import { Technologist } from 'app/shared/model/technologist.model';

describe('Component Tests', () => {
    describe('Technologist Management Detail Component', () => {
        let comp: TechnologistDetailComponent;
        let fixture: ComponentFixture<TechnologistDetailComponent>;
        const route = ({ data: of({ technologist: new Technologist(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SysComArchTestModule],
                declarations: [TechnologistDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TechnologistDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TechnologistDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.technologist).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
