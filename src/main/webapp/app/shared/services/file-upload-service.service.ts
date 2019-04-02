import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { SERVER_API_URL } from 'app/app.constants';
import { FileModel } from 'app/shared/models/file-model';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class FileUploadServiceService {
    public resourceUrl = SERVER_API_URL + 'api/';

    constructor(private http: HttpClient) {}

    uploadFile(file: File): Observable<HttpResponse<FileModel>> {
        const formData: FormData = new FormData();
        formData.append('file', file);

        return this.http.post<FileModel>(this.resourceUrl + 'uploadFile', formData, { observe: 'response' });
    }
}
