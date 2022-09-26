
abstract class Veicolo{

    nRuote:number = 1;
    vel:number = 0;
    velMax:number;
    motor:boolean;

    constructor(nRuote:number,velMax:number,motor:boolean) {
        this.nRuote = nRuote;
        this.velMax = velMax;
        this.motor = motor;
    }

    fermaVeicolo():void{
        this.vel = 0;
    }

    limite(vel:number):boolean{
        return vel <= this.velMax
    }

    abstract accelera(vel:number):void
    abstract frena(vel:number):void

}

class Automobile extends Veicolo{

    constructor(velMax:number){
        super(4,velMax,true)
    }

    accelera(vel:number) {
       this.vel += vel
    }
    frena(vel:number) {
        this.vel -= vel
    }
    
}

let auto = new Automobile(280);