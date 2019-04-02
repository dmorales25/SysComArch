import { element, by, ElementFinder } from 'protractor';

export class StudentComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-student div table .btn-danger'));
    title = element.all(by.css('jhi-student div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class StudentUpdatePage {
    pageTitle = element(by.id('jhi-student-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    enrollmentInput = element(by.id('field_enrollment'));
    ageInput = element(by.id('field_age'));
    emailInput = element(by.id('field_email'));
    technologistSelect = element(by.id('field_technologist'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNameInput(name) {
        await this.nameInput.sendKeys(name);
    }

    async getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    async setEnrollmentInput(enrollment) {
        await this.enrollmentInput.sendKeys(enrollment);
    }

    async getEnrollmentInput() {
        return this.enrollmentInput.getAttribute('value');
    }

    async setAgeInput(age) {
        await this.ageInput.sendKeys(age);
    }

    async getAgeInput() {
        return this.ageInput.getAttribute('value');
    }

    async setEmailInput(email) {
        await this.emailInput.sendKeys(email);
    }

    async getEmailInput() {
        return this.emailInput.getAttribute('value');
    }

    async technologistSelectLastOption() {
        await this.technologistSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async technologistSelectOption(option) {
        await this.technologistSelect.sendKeys(option);
    }

    getTechnologistSelect(): ElementFinder {
        return this.technologistSelect;
    }

    async getTechnologistSelectedOption() {
        return this.technologistSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class StudentDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-student-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-student'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
