export class Todo {
    id: string;
    description: string;
    status: string;
    file: string;

    constructor() {
        this.id = "";
        this.description = "";
        this.status = "pendiente";
        this.file = null;
    }
}
