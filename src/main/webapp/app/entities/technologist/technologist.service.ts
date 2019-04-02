import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITechnologist } from 'app/shared/model/technologist.model';

type EntityResponseType = HttpResponse<ITechnologist>;
type EntityArrayResponseType = HttpResponse<ITechnologist[]>;

@Injectable({ providedIn: 'root' })
export class TechnologistService {
    public resourceUrl = SERVER_API_URL + 'api/technologists';

    constructor(private http: HttpClient) {}

    create(technologist: ITechnologist): Observable<EntityResponseType> {
        return this.http.post<ITechnologist>(this.resourceUrl, technologist, { observe: 'response' });
    }

    update(technologist: ITechnologist): Observable<EntityResponseType> {
        return this.http.put<ITechnologist>(this.resourceUrl, technologist, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITechnologist>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITechnologist[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
