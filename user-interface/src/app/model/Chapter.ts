import { Panel } from "./Panel";

export class Chapter {
    private _number: number;
    private _url: string;
    private _panels: Array<Panel>;

    constructor(number: number, link: string, panels: Array<Panel>) {
        this._number = number;
        this._url = link;
        this._panels = panels;
    }

    public get number(): number {
        return this._number;
    }

    public set number(value: number) {
        this._number = value;
    }

    public get url(): string {
        return this._url;
    }

    public set url(value: string) {
        this._url = value;
    }

    public get panels(): Array<Panel> {
        return this._panels;
    }
    public set panels(value: Array<Panel>) {
        this._panels = value;
    }
}