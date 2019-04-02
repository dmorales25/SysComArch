/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SysComArchTestModule } from '../../../test.module';
import { TechnologistDeleteDialogComponent } from 'app/entities/technologist/technologist-delete-dialog.component';
import { TechnologistService } from 'app/entities/technologist/technologist.service';

describe('Component Tests', () => {
    describe('Technologist Management Delete Component', () => {
        let comp: TechnologistDeleteDialogComponent;
        let fixture: ComponentFixture<TechnologistDeleteDialogComponent>;
        let service: TechnologistService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SysComArchTestModule],
                declarations: [TechnologistDeleteDialogComponent]
            })
                .overrideTemplate(TechnologistDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TechnologistDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TechnologistService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
