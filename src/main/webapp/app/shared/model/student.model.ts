export interface IStudent {
    id?: number;
    name?: string;
    enrollment?: string;
    age?: number;
    email?: string;
    technologistName?: string;
    technologistId?: number;
}

export class Student implements IStudent {
    constructor(
        public id?: number,
        public name?: string,
        public enrollment?: string,
        public age?: number,
        public email?: string,
        public technologistName?: string,
        public technologistId?: number
    ) {}
}
