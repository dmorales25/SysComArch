import { element, by, ElementFinder } from 'protractor';

export class DocumentComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-document div table .btn-danger'));
    title = element.all(by.css('jhi-document div h2#page-heading span')).first();

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

export class DocumentUpdatePage {
    pageTitle = element(by.id('jhi-document-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    titleInput = element(by.id('field_title'));
    nameInput = element(by.id('field_name'));
    uploadDateInput = element(by.id('field_uploadDate'));
    archiveNameInput = element(by.id('field_archiveName'));
    decriptionInput = element(by.id('field_decription'));
    subjectSelect = element(by.id('field_subject'));
    studentSelect = element(by.id('field_student'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setTitleInput(title) {
        await this.titleInput.sendKeys(title);
    }

    async getTitleInput() {
        return this.titleInput.getAttribute('value');
    }

    async setNameInput(name) {
        await this.nameInput.sendKeys(name);
    }

    async getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    async setUploadDateInput(uploadDate) {
        await this.uploadDateInput.sendKeys(uploadDate);
    }

    async getUploadDateInput() {
        return this.uploadDateInput.getAttribute('value');
    }

    async setArchiveNameInput(archiveName) {
        await this.archiveNameInput.sendKeys(archiveName);
    }

    async getArchiveNameInput() {
        return this.archiveNameInput.getAttribute('value');
    }

    async setDecriptionInput(decription) {
        await this.decriptionInput.sendKeys(decription);
    }

    async getDecriptionInput() {
        return this.decriptionInput.getAttribute('value');
    }

    async subjectSelectLastOption() {
        await this.subjectSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async subjectSelectOption(option) {
        await this.subjectSelect.sendKeys(option);
    }

    getSubjectSelect(): ElementFinder {
        return this.subjectSelect;
    }

    async getSubjectSelectedOption() {
        return this.subjectSelect.element(by.css('option:checked')).getText();
    }

    async studentSelectLastOption() {
        await this.studentSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async studentSelectOption(option) {
        await this.studentSelect.sendKeys(option);
    }

    getStudentSelect(): ElementFinder {
        return this.studentSelect;
    }

    async getStudentSelectedOption() {
        return this.studentSelect.element(by.css('option:checked')).getText();
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

export class DocumentDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-document-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-document'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
