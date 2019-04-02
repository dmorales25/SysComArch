export interface ITechnologist {
    id?: number;
    name?: string;
}

export class Technologist implements ITechnologist {
    constructor(public id?: number, public name?: string) {}
}
