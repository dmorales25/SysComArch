import { Moment } from 'moment';
import { TextQuery } from 'app/shared/models/text-query';

export interface IDocument {
    id?: number;
    title?: string;
    name?: string;
    uploadDate?: Moment;
    archiveName?: string;
    decription?: string;
    subjectName?: string;
    subjectId?: number;
    studentName?: string;
    studentId?: number;
    textQueries?: TextQuery[];
}

export class Document implements IDocument {
    constructor(
        public id?: number,
        public title?: string,
        public name?: string,
        public uploadDate?: Moment,
        public archiveName?: string,
        public decription?: string,
        public subjectName?: string,
        public subjectId?: number,
        public studentName?: string,
        public studentId?: number,
        public textQueries?: TextQuery[]
    ) {}
}
