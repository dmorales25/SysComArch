import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SysComArchSharedModule } from 'app/shared';
import {
    TechnologistComponent,
    TechnologistDetailComponent,
    TechnologistUpdateComponent,
    TechnologistDeletePopupComponent,
    TechnologistDeleteDialogComponent,
    technologistRoute,
    technologistPopupRoute
} from './';

const ENTITY_STATES = [...technologistRoute, ...technologistPopupRoute];

@NgModule({
    imports: [SysComArchSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TechnologistComponent,
        TechnologistDetailComponent,
        TechnologistUpdateComponent,
        TechnologistDeleteDialogComponent,
        TechnologistDeletePopupComponent
    ],
    entryComponents: [
        TechnologistComponent,
        TechnologistUpdateComponent,
        TechnologistDeleteDialogComponent,
        TechnologistDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SysComArchTechnologistModule {}
