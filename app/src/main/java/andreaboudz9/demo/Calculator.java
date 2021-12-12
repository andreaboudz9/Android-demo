package andreaboudz9.demo;

public class Calculator {

    private double resul = 0;
    public Calculator Clear(){
        this.resul = 0;
        return this;
    }
    public Calculator addition(double a, double b){
        this.resul = this.resul + a + b;
        return this;
    }
    public Calculator multiplication(double a, double b){
        if(this.resul == 0){
            this.resul = a * b;
            return this;
        }
        this.resul = this.resul * a * b;
        return this;
    }
    public Calculator division(double a, double b){
        if(this.resul == 0 && b != 0){
            this.resul = a / b;
            return this;
        }
        if(a != 0 && b != 0){
            this.resul = this.resul / a / b;
            return this;
        }
        return this;
    }
    public Calculator soustration(double a, double b){
        this.resul = this.resul - a - b;
        return this;
    }
    public Calculator reste(double a, double b){
        if(this.resul == 0 && b != 0){
            this.resul = a % b;
            return this;
        }
        if(a != 0 && b != 0){
            this.resul = this.resul % a % b;
            return this;
        }
        return this;
    }
    public int toInt(){
        //Premiere methode
        int resul = (int) this.resul;
        return resul;
    }
    public double toDouble(){
        //Premiere methode
        return this.resul;
    }
}