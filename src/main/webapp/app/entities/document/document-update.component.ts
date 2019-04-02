import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IDocument } from 'app/shared/model/document.model';
import { DocumentService } from './document.service';
import { ISubject } from 'app/shared/model/subject.model';
import { SubjectService } from 'app/entities/subject';
import { IStudent } from 'app/shared/model/student.model';
import { StudentService } from 'app/entities/student';
import { FileUploadServiceService } from 'app/shared/services/file-upload-service.service';

@Component({
    selector: 'jhi-document-update',
    templateUrl: './document-update.component.html'
})
export class DocumentUpdateComponent implements OnInit {
    document: IDocument;
    isSaving: boolean;

    subjects: ISubject[];

    students: IStudent[];
    uploadDateDp: any;

    documentFile: File;
    documentPath: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private documentService: DocumentService,
        private subjectService: SubjectService,
        private studentService: StudentService,
        private fileUploadService: FileUploadServiceService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ document }) => {
            this.document = document;
        });
        this.subjectService.query().subscribe(
            (res: HttpResponse<ISubject[]>) => {
                this.subjects = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.studentService.query().subscribe(
            (res: HttpResponse<IStudent[]>) => {
                this.students = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.document.id !== undefined) {
            this.subscribeToSaveResponse(this.documentService.update(this.document));
        } else {
            this.fileUploadService.uploadFile(this.documentFile).subscribe(data => {
                console.log(data.body);
                this.document.archiveName = data.body.fileName;
                this.subscribeToSaveResponse(this.documentService.create(this.document));
            });
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDocument>>) {
        result.subscribe((res: HttpResponse<IDocument>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackSubjectById(index: number, item: ISubject) {
        return item.id;
    }

    trackStudentById(index: number, item: IStudent) {
        return item.id;
    }

    onFileChange(event: any) {
        if (event.target.files && event.target.files[0]) {
            const reader = new FileReader();
            this.documentFile = event.target.files[0];
            this.documentPath = this.documentFile.name;
            reader.onload = (evente: ProgressEvent) => {
                console.log('Imagen cargada.');
            };
            reader.readAsDataURL(event.target.files[0]);
        }
    }
}
