import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int op;
        double monto = 0;
        boolean estado = true;
        String nCuenta = "";
        int depositoshechos=0;
        int retiroshechos=0;


        //SE AÑADIERON
        //TOPE DE VECES DE RETIRO (4)
        //TOPE DE VECES DE DEPOSITAR (5)
        //NO PUEDE DEPOSITAR 0 PESOS NI NUMEROS NEGATIVOS
        //NO PUEDE RETIRAR 0 PESOS NI NUMEROS NEGATIVOS
        //NO PUEDE RETIRAR MAS DEL SALDO ACTUAL
        //TRANFERIRIR DINERO DE UNA CUENAT A OTRA CUENTA


        ArrayList<cuentaBancaria> lstBanco = new ArrayList<>();

        cuentaBancaria cuenta = new cuentaBancaria();
        do {
            System.out.println("""
                    *-*-*BIENVENIDO A TU BANCO DE CONFIANZA MONIK*-*-*
                    
                                 ¿QUE QUIERES REALIZAR?  
                        
                    1.CREAR CLIENTE
                    2.MOSTRAR TODO
                    3.MOSTRAR INFO CUENTA ESPECIFICA
                    4.DEPOSITAR DINERO
                    5.RETIRAR DINERO
                    6.MOSTRAR CLIENTES DESDE LISTA
                    7.TRANSFERENCIA DE DIENRO ENTRE CUENTAS
                    8. SALIR
                    *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
                    """);
            op = teclado.nextInt();
            switch (op) {
                case 1 -> {
                    System.out.println("INGRESE NOMBRE DEL TITULAR");
                    cuenta.setTitular(teclado.next());
                    System.out.println("INGRESE SALDO DEL TITULAR");
                    cuenta.setSaldo(teclado.nextDouble());
                    System.out.println("INGRESE NUMERO DE LA CUENTA");
                    cuenta.setNumeroCuenta(teclado.next());
//                    cl1.setTipoCuenta(teclado.next());
                    //cl1 = new cuentaBancaria(cl1.getTitular(), cl1.getSaldo(), cl1.getNumeroCuenta());//CREA OBJETO

                    //lstBanco.add(cl1); UNA FORMA DE AÑADIR OBJETO A LISTA O ES IGUAL LA DE ABAJO
                    lstBanco.add(new cuentaBancaria(cuenta.getTitular(), cuenta.getSaldo(), cuenta.getNumeroCuenta()));//SE crea objeto y se añade a lista de una vez

                }
                case 2 -> {
                    // System.out.println(lstBanco);
                    for (cuentaBancaria cu : lstBanco) {
                        System.out.println(cu);
                    }

                }
                case 3 -> {
                    System.out.println("INGRESE NUMERO DE CUENTA");
                    nCuenta = teclado.next();
                    boolean cuentaubicada = false;

                    for (cuentaBancaria cu : lstBanco) {
                        if (cu.getNumeroCuenta().equalsIgnoreCase(nCuenta)) {
                            System.out.println(cu);
                            cuentaubicada = true;
                            break;
                        }
                    }
                    if (!cuentaubicada) {
                        System.out.println("LA CUENTA NO SE ENCONTRO");
                    }
                }
                case 4 -> {
                    System.out.println("DEPOSITAR");
                    if (depositoshechos >= 5) {
                        System.out.println("HA EXCEDIDO EL LIMITE DE DEPOSITOS");
                        break;
                    }
                    System.out.println("INGRESE NUMERO DE CUENTA");
                    String depositar = teclado.next();
                    for (cuentaBancaria cu : lstBanco) {
                        if (cu.getNumeroCuenta().equalsIgnoreCase(depositar)) {
                            do {
                                System.out.println("CUANTO DINERO DESEA DEPOSITAR");
                                monto = teclado.nextDouble();
                                if (monto <= 0) {
                                    System.out.println("NO SE PUEDE DEPOSITAR UNA CANTIDAD NEGATIVA o 0 PESOS");
                                }
                            } while (monto <= 0);
                            cu.depositarDinero(monto);
                            depositoshechos++;
                            System.out.println("HA DEPOSITADO : " + monto);
                            break;
                        }
                    }

                }
                case 5 -> {
                    System.out.println("RETIRAR");
                    if (retiroshechos >= 4) {
                        System.out.println("HA EXCEDIDO EL LIMITE DE RETIROS");
                        break;
                    }
                    System.out.println("INGRESE NUMEOR DE CUENTA");
                    String retirarD = teclado.next();
                    for (cuentaBancaria cu : lstBanco) {
                        if (cu.getNumeroCuenta().equalsIgnoreCase(retirarD)) {
                            do {
                                System.out.println("CUENTO QUIERE RETIRAR");
                                monto = teclado.nextDouble();
                                if (monto <= 0) {
                                    System.out.println("NO PUEDE RETIRAR 0 PESOS ni un valor negativo");

                                } else if (monto > cuenta.getSaldo()) {
                                    System.out.println("No tienes suficiente saldo.");
                                }
                            } while (monto <= 0);
                            cu.retirarDinero(monto);
                            retiroshechos++;
                            System.out.println("HA RETIRADO : " + monto);
                            break;

                        }
                    }
                }
                case 6 -> {
                    System.out.println("Mostrar datos de la cuenta desde la lista");
                    //System.out.println(lstBanco.toString());
                    for (cuentaBancaria cu : lstBanco) {
                        System.out.println(cu);
                    }
                }
                case 7 -> {
                    System.out.println("TRANSFERIR SALDO ENTRE CUENTAS");
                    cuentaBancaria origen = null;
                    cuentaBancaria destino = null;

                    System.out.println("INGRESE NUMERO DE CUENTA ORIGEN");
                    String cuentaOrigen = teclado.next();
                    for (cuentaBancaria cu : lstBanco) {
                        if (cu.getNumeroCuenta().equalsIgnoreCase(cuentaOrigen)) {
                            origen = cu;
                            break;
                        }
                    }

                    System.out.println("INGRESE NUMERO DE CUENTA DESTINO");
                    String cuentaDestino = teclado.next();

                    for (cuentaBancaria cu : lstBanco) {
                        if (cu.getNumeroCuenta().equalsIgnoreCase(cuentaDestino)) {
                            destino = cu;
                            break;
                        }
                    }
                    if (cuentaOrigen != null && cuentaDestino != null) {
                        System.out.println("CUANTO DINERO DESEA TRANSFERIR");
                        monto = teclado.nextDouble();

                        if (monto > 0 && monto <= origen.getSaldo()) {
                            origen.retirarDinero(monto);
                            destino.depositarDinero(monto);
                            System.out.println("*-*-*TRANSFERENCIA EXITOSA*-*-");

                        } else {
                            System.out.println("SU  SALDO ES INSUFICIENTE EN ESTA CUENTA");
                        }
                    } else {
                        System.out.println("ALGUNA DE LAS CUENTAS NO EXISTEN");
                    }
                }
            case 8 -> {
                System.out.println("*-*VUELVE PRONTOO.....*-*-*-");
                estado = false;
            }
            default -> {
                    System.out.println("Esta opcion no existe");

                        }

                    }
                }
                while (estado = true) ;
            }
        }


/* do{
 System.out.println("""

 1.DEPOSITAR DINERO
 2.RETIRAR DINERO
 3.MOSTRAR DATOS DE LA CUENTAS
 4.MOSTRAR CLIENTES
 5.SALIDA
 """);

 op = teclado.nextInt();

 switch (op){
     case 1 ->{
         System.out.println("DEPOSITAR");
         do {
             System.out.println("CUANTO DINERO DESEA DEPOSITAR");
             monto = teclado.nextDouble();
         }while (monto<0);
         cl1.depositarDinero(monto);
     }
     case 2 ->{
         System.out.println("RETIRAR");
         do {
             System.out.println("CUANTO DINERO DESEA DEPOSITAR");
             monto = teclado.nextDouble();
         }while (monto<=0);
         cl1.retirarDinero(monto);
     }
     case 3 ->{
         System.out.println("Mostrar informacion de la cuenta");
     }
     case 4 ->{
         System.out.println("Mostrar datos de la cuenta desde la lista");
         //System.out.println(lstBanco.toString());
         for (CuentaBancaria cu : lstBanco){
             System.out.println(cu);
         }
     }
     case 5 ->{
         System.out.println("SALIR...");
         estado=false;
     } default -> {
         System.out.println("Esta opcion no existe");
     }
 }

 }while(estado);

 */