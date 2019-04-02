/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DocumentComponentsPage, DocumentDeleteDialog, DocumentUpdatePage } from './document.page-object';

const expect = chai.expect;

describe('Document e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let documentUpdatePage: DocumentUpdatePage;
    let documentComponentsPage: DocumentComponentsPage;
    /*let documentDeleteDialog: DocumentDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Documents', async () => {
        await navBarPage.goToEntity('document');
        documentComponentsPage = new DocumentComponentsPage();
        expect(await documentComponentsPage.getTitle()).to.eq('sysComArchApp.document.home.title');
    });

    it('should load create Document page', async () => {
        await documentComponentsPage.clickOnCreateButton();
        documentUpdatePage = new DocumentUpdatePage();
        expect(await documentUpdatePage.getPageTitle()).to.eq('sysComArchApp.document.home.createOrEditLabel');
        await documentUpdatePage.cancel();
    });

    /* it('should create and save Documents', async () => {
        const nbButtonsBeforeCreate = await documentComponentsPage.countDeleteButtons();

        await documentComponentsPage.clickOnCreateButton();
        await promise.all([
            documentUpdatePage.setTitleInput('title'),
            documentUpdatePage.setNameInput('name'),
            documentUpdatePage.setUploadDateInput('2000-12-31'),
            documentUpdatePage.setArchiveNameInput('archiveName'),
            documentUpdatePage.setDecriptionInput('decription'),
            documentUpdatePage.subjectSelectLastOption(),
            documentUpdatePage.studentSelectLastOption(),
        ]);
        expect(await documentUpdatePage.getTitleInput()).to.eq('title');
        expect(await documentUpdatePage.getNameInput()).to.eq('name');
        expect(await documentUpdatePage.getUploadDateInput()).to.eq('2000-12-31');
        expect(await documentUpdatePage.getArchiveNameInput()).to.eq('archiveName');
        expect(await documentUpdatePage.getDecriptionInput()).to.eq('decription');
        await documentUpdatePage.save();
        expect(await documentUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await documentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last Document', async () => {
        const nbButtonsBeforeDelete = await documentComponentsPage.countDeleteButtons();
        await documentComponentsPage.clickOnLastDeleteButton();

        documentDeleteDialog = new DocumentDeleteDialog();
        expect(await documentDeleteDialog.getDialogTitle())
            .to.eq('sysComArchApp.document.delete.question');
        await documentDeleteDialog.clickOnConfirmButton();

        expect(await documentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
