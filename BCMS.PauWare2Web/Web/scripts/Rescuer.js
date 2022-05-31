export default class Rescuer {
    constructor() {
        this._nbCarsToSend = 0;
        this._isRoadChosed = false;
        this._isRoadValide = false;
        this._okayWithRoad = false;
        this._islog = false;
        this._Dispatch = false;
        this._isArrived = false;
        this._breakdown = "";
    }
    set_nbCarsToSend(nbvehicul) {
        this._nbCarsToSend = nbvehicul;
    }
    set_isRoadChosed(ischoosed) {
        this._isRoadChosed = ischoosed;
    }
    set_isRoadValide(isok) {
        this._isRoadValide = isok;
    }
    set_okayWithRoad(okayWithRoad) {
        this._okayWithRoad = okayWithRoad;
    }
    set_islog(islog) {
        this._islog = islog;
    }
    set_Dispatch(Dispatch) {
        this._Dispatch = Dispatch;
    }
    set_isArrived(isArrived) {
        this._isArrived = isArrived;
    }
    set_breakdown(breakdown) {
        this._breakdown = breakdown;
    }
    get_nbCarsToSend() {
        return this._nbCarsToSend;
    }
    get_isRoadChosed() {
        return this._isRoadChosed;
    }
    get_isRoadValide() {
        return this._isRoadValide;
    }
    get_okayWithRoad() {
        return this._okayWithRoad;
    }
    get_islog() {
        return this._islog;
    }
    get_Dispatch() {
        return this._Dispatch;
    }
    get_isArrived() {
        return this._isArrived;
    }
    get_breakdown() {
        return this._breakdown;
    }
}
