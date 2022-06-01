export class Panel {
    private _number: number;
    private _link: string;

    constructor(number: number, link: string) {
        this._number = number;
        this._link = link;
    }

    public get number(): number {
        return this._number;
    }

    public set number(value: number) {
        this._number = value;
    }

    public get link(): string {
        return this._link;
    }

    public set link(value: string) {
        this._link = value;
    }

}