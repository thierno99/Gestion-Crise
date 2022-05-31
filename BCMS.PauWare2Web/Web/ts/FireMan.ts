import Rescuer from "./Rescuer.js";

export default class FireMan extends Rescuer{
  private _Name:string="Pompier" as const;
  public constructor(){
    super();
  }
  public get_Name():string{
    return this._Name;
  }
}