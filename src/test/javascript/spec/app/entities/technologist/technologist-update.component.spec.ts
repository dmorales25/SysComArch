/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SysComArchTestModule } from '../../../test.module';
import { TechnologistUpdateComponent } from 'app/entities/technologist/technologist-update.component';
import { TechnologistService } from 'app/entities/technologist/technologist.service';
import { Technologist } from 'app/shared/model/technologist.model';

describe('Component Tests', () => {
    describe('Technologist Management Update Component', () => {
        let comp: TechnologistUpdateComponent;
        let fixture: ComponentFixture<TechnologistUpdateComponent>;
        let service: TechnologistService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SysComArchTestModule],
                declarations: [TechnologistUpdateComponent]
            })
                .overrideTemplate(TechnologistUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TechnologistUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TechnologistService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Technologist(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.technologist = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Technologist();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.technologist = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
