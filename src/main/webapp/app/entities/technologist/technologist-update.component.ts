import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITechnologist } from 'app/shared/model/technologist.model';
import { TechnologistService } from './technologist.service';

@Component({
    selector: 'jhi-technologist-update',
    templateUrl: './technologist-update.component.html'
})
export class TechnologistUpdateComponent implements OnInit {
    technologist: ITechnologist;
    isSaving: boolean;

    constructor(private technologistService: TechnologistService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ technologist }) => {
            this.technologist = technologist;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.technologist.id !== undefined) {
            this.subscribeToSaveResponse(this.technologistService.update(this.technologist));
        } else {
            this.subscribeToSaveResponse(this.technologistService.create(this.technologist));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITechnologist>>) {
        result.subscribe((res: HttpResponse<ITechnologist>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
