import Rescuer from "./Rescuer.js";
export default class Police extends Rescuer {
    constructor() {
        super();
        this._Name = "Police";
    }
    get_Name() {
        return this._Name;
    }
}
