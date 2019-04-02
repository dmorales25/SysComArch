/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TechnologistComponentsPage, TechnologistDeleteDialog, TechnologistUpdatePage } from './technologist.page-object';

const expect = chai.expect;

describe('Technologist e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let technologistUpdatePage: TechnologistUpdatePage;
    let technologistComponentsPage: TechnologistComponentsPage;
    let technologistDeleteDialog: TechnologistDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Technologists', async () => {
        await navBarPage.goToEntity('technologist');
        technologistComponentsPage = new TechnologistComponentsPage();
        expect(await technologistComponentsPage.getTitle()).to.eq('sysComArchApp.technologist.home.title');
    });

    it('should load create Technologist page', async () => {
        await technologistComponentsPage.clickOnCreateButton();
        technologistUpdatePage = new TechnologistUpdatePage();
        expect(await technologistUpdatePage.getPageTitle()).to.eq('sysComArchApp.technologist.home.createOrEditLabel');
        await technologistUpdatePage.cancel();
    });

    it('should create and save Technologists', async () => {
        const nbButtonsBeforeCreate = await technologistComponentsPage.countDeleteButtons();

        await technologistComponentsPage.clickOnCreateButton();
        await promise.all([technologistUpdatePage.setNameInput('name')]);
        expect(await technologistUpdatePage.getNameInput()).to.eq('name');
        await technologistUpdatePage.save();
        expect(await technologistUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await technologistComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Technologist', async () => {
        const nbButtonsBeforeDelete = await technologistComponentsPage.countDeleteButtons();
        await technologistComponentsPage.clickOnLastDeleteButton();

        technologistDeleteDialog = new TechnologistDeleteDialog();
        expect(await technologistDeleteDialog.getDialogTitle()).to.eq('sysComArchApp.technologist.delete.question');
        await technologistDeleteDialog.clickOnConfirmButton();

        expect(await technologistComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
