public class cuentaBancaria {
    private String titular;
    private double saldo;
    private String numeroCuenta;



    public cuentaBancaria() {
    }


    public cuentaBancaria(String titular, double saldo, String numeroCuenta) {
        this.titular = titular;
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;


    }


    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }



    @Override
    public String toString() {
        return "cuentaBancaria{" +
                "titular='" + titular + '\'' +
                ", saldo=" + saldo +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                '}';
    }

    public void depositarDinero(double monto) {
        this.saldo += monto;
    }

    public void retirarDinero(double monto) {
        this.saldo -= monto;


    }


    public void mostrarInformacion() {
        System.out.println("EL NOMBRE DEL TIULAR ES : " + this.titular);
        System.out.println("EL SALDO DEL TIULAR ES : " + this.saldo);
        System.out.println("EL NUMERO DE CUENTA DEL TIULAR ES : " + this.numeroCuenta);

        System.out.println();
    }
}



