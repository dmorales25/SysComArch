import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Technologist } from 'app/shared/model/technologist.model';
import { TechnologistService } from './technologist.service';
import { TechnologistComponent } from './technologist.component';
import { TechnologistDetailComponent } from './technologist-detail.component';
import { TechnologistUpdateComponent } from './technologist-update.component';
import { TechnologistDeletePopupComponent } from './technologist-delete-dialog.component';
import { ITechnologist } from 'app/shared/model/technologist.model';

@Injectable({ providedIn: 'root' })
export class TechnologistResolve implements Resolve<ITechnologist> {
    constructor(private service: TechnologistService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Technologist> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Technologist>) => response.ok),
                map((technologist: HttpResponse<Technologist>) => technologist.body)
            );
        }
        return of(new Technologist());
    }
}

export const technologistRoute: Routes = [
    {
        path: 'technologist',
        component: TechnologistComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'sysComArchApp.technologist.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'technologist/:id/view',
        component: TechnologistDetailComponent,
        resolve: {
            technologist: TechnologistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sysComArchApp.technologist.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'technologist/new',
        component: TechnologistUpdateComponent,
        resolve: {
            technologist: TechnologistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sysComArchApp.technologist.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'technologist/:id/edit',
        component: TechnologistUpdateComponent,
        resolve: {
            technologist: TechnologistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sysComArchApp.technologist.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const technologistPopupRoute: Routes = [
    {
        path: 'technologist/:id/delete',
        component: TechnologistDeletePopupComponent,
        resolve: {
            technologist: TechnologistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sysComArchApp.technologist.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
