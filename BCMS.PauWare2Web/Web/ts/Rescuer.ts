export default abstract class  Rescuer{
  private _nbCarsToSend:number;
  private _isRoadChosed:boolean;
  private _isRoadValide:boolean;
  private _okayWithRoad:boolean;
  private _islog:boolean;
  private _Dispatch:boolean;
  private _isArrived:boolean;
  private _breakdown:string;

  public constructor(){
    this._nbCarsToSend=0;
    this._isRoadChosed=false;
    this._isRoadValide=false;
    this._okayWithRoad=false;
    this._islog=false;
    this._Dispatch=false;
    this._isArrived=false;
    this._breakdown="";
  }
  
  public set_nbCarsToSend(nbvehicul:number):void{
    this._nbCarsToSend=nbvehicul;
  }

  public set_isRoadChosed(ischoosed:boolean):void{
    this._isRoadChosed=ischoosed;
  }

  public set_isRoadValide(isok:boolean){
    this._isRoadValide=isok;
  }

  public set_okayWithRoad(okayWithRoad:boolean){
    this._okayWithRoad=okayWithRoad;
  }
  public set_islog(islog:boolean){
    this._islog=islog;
  }
  public set_Dispatch(Dispatch:boolean){
    this._Dispatch=Dispatch;
  }
  public set_isArrived(isArrived:boolean){
    this._isArrived=isArrived;
  }
  public set_breakdown(breakdown:string){
    this._breakdown=breakdown;
  }


  public get_nbCarsToSend():number{
    return this._nbCarsToSend;
  }

  public get_isRoadChosed():boolean{
    return this._isRoadChosed;
  }

  public get_isRoadValide():boolean{
    return this._isRoadValide;
  }

  public get_okayWithRoad():boolean{
    return this._okayWithRoad;
  }
  public get_islog():boolean{
    return this._islog;
  }
  public get_Dispatch():boolean{
    return this._Dispatch;
  }
  public get_isArrived():boolean{
    return this._isArrived;
  }
  public get_breakdown():string{
    return this._breakdown;
  }
}