import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITechnologist } from 'app/shared/model/technologist.model';

@Component({
    selector: 'jhi-technologist-detail',
    templateUrl: './technologist-detail.component.html'
})
export class TechnologistDetailComponent implements OnInit {
    technologist: ITechnologist;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ technologist }) => {
            this.technologist = technologist;
        });
    }

    previousState() {
        window.history.back();
    }
}
