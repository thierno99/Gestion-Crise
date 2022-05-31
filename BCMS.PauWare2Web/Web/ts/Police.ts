import Rescuer from "./Rescuer.js";

export default class Police extends Rescuer{
  private _Name:string="Police" as const;
  public constructor(){
    super();
  }
  public get_Name():string{
    return this._Name;
  }
}