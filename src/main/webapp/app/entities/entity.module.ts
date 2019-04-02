import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { SysComArchTechnologistModule } from './technologist/technologist.module';
import { SysComArchSubjectModule } from './subject/subject.module';
import { SysComArchStudentModule } from './student/student.module';
import { SysComArchDocumentModule } from './document/document.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        SysComArchTechnologistModule,
        SysComArchSubjectModule,
        SysComArchStudentModule,
        SysComArchDocumentModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SysComArchEntityModule {}
