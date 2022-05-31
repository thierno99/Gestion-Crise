import Rescuer from "./Rescuer.js";
export default class FireMan extends Rescuer {
    constructor() {
        super();
        this._Name = "Pompier";
    }
    get_Name() {
        return this._Name;
    }
}
