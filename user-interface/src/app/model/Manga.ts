import { Chapter } from "./Chapter";

export class Manga {
    private _title: string;
    private _description: string;
    private _chapters: Array<Chapter>;


    constructor(title: string, description: string, chapters: Array<Chapter>) {
        this._title = title;
        this._description = description;
        this._chapters = chapters;
    }

    public get title(): string {
        return this._title;
    }

    public set title(value: string) {
        this._title = value;
    }

    public get description(): string {
        return this._description;
    }

    public set description(value: string) {
        this._description = value;
    }

    public get chapters(): Array<Chapter> {
        return this._chapters;
    }

    public set chapters(value: Array<Chapter>) {
        this._chapters = value;
    }
}