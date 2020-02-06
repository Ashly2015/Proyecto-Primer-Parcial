import java.util.Scanner;

public class planilla {
    public static void main(String[] args) {
        int cont = 0;
        int op = 0;
        int opc = 0;
        int opsalir = 0;
        String[][] Dtempleado = new String[20][2];
        float[][]valores=new float[20][19];
        float bonificaciones = (float) 154.50;


        Scanner leer = new Scanner(System.in);

        do {
            System.out.println("MENU");
            System.out.println("1. Ingresar empleado");
            System.out.println("2. Mostrar Planilla");
            System.out.println("3. Salir");
            opc = leer.nextInt();

            switch (opc) {
                case 1:

                    do {
                        System.out.println("----------------------------------------------------");
                        System.out.println("");
                        Dtempleado[cont][0] = leer.nextLine();
                        System.out.println("¿Cual es tu nombre?");
                        Dtempleado[cont][0] = leer.nextLine();
                        System.out.println("¿Cual es tu puesto?");
                        Dtempleado[cont][1] =leer.nextLine();

                        System.out.println("¿Cuantos dias laboro?");
                        valores[cont][0] = Float.parseFloat(leer.nextLine());
                        System.out.println("¿Cuantas horas extras hizo?");
                        valores[cont][1] = Float.parseFloat(leer.nextLine());
                        System.out.println("¿Cual es su sueldo base?");
                        valores[cont][2] = Float.parseFloat(leer.nextLine());
                        System.out.println("¿Cuanto fueron de comisiones?");
                        valores[cont][3] = Float.parseFloat(leer.nextLine());
                        System.out.println("¿Otro sueldo?");
                        valores[cont][4] = Float.parseFloat(leer.nextLine());
                        valores[cont][5] = valores[cont][2] / 30;
                        valores[cont][6] = valores[cont][5] * valores[cont][0];
                        valores[cont][7] = (float) (valores[cont][5] * 1.5 * valores[cont][1]);
                        System.out.println("¿Falto algun dia en la semana?");
                        System.out.println("1. No");
                        System.out.println("2. Si");
                        op = leer.nextInt();
                        switch (op) {

                            case 1:
                                valores[cont][8] = 4 * valores[cont][5];
                                valores[cont][9] = valores[cont][7] + valores[cont][6] + valores[cont][8] +valores[cont][3] + valores[cont][4];
                                break;
                            case 2:
                                System.out.println("¿Cuantos dias falto en las 4 semanas?");
                                valores[cont][8] = (float) ((4 - leer.nextDouble()) * valores[cont][5]);

                                valores[cont][9] = valores[cont][7] + valores[cont][6] + valores[cont][8] + valores[cont][3] + valores[cont][4];
                                break;

                        }
                        valores[cont][10]=bonificaciones;


                        valores[cont][11] = (float) (valores[cont][9] + bonificaciones);
                        valores[cont][12] = (float) (valores[cont][9] * 0.0483);

                        if (valores[cont][9] > 6000 & valores[cont][9] <7500) {
                            valores[cont][13] = (float) (valores[cont][9] * 0.05);
                        } else if (valores[cont][9] > 7500 & valores[cont][9] <9000){
                            valores[cont][13] = (float) (valores[cont][9] * 0.06);
                        } else if (valores[cont][9] > 9000) {
                            valores[cont][13] = (float) (valores[cont][9] * 0.09);
                        }else {
                            valores[cont][13] = 0;
                        }
                        System.out.println("Ingrese monto de Anticipo");
                        valores[cont][14] = leer.nextFloat();
                        System.out.println("Ingrese monto descuentos judiciales");
                        valores[cont][15] = leer.nextFloat();
                        System.out.println("Ingrese otros descuentos");
                        valores[cont][16]= leer.nextFloat();

                        valores[cont][17] = valores[cont][12] +valores[cont][13] + valores[cont][14] + valores[cont][15] + valores[cont][16];

                       valores[cont][18] = valores[cont][11] - valores[cont][17];

                        System.out.println("----------------------------------------------------");
                        cont++;
                        System.out.println("¿Desea ingresar otro empleado?");
                        System.out.println("1. Si");
                        System.out.println("2. NO");
                        opsalir = leer.nextInt();
                        System.out.println("----------------------------------------------------");



                    }  while (opsalir != 2);
                    break;
                case 2:
                    System.out.println("NOMBRE"+"    "+    "PUESTO" +"    "+"DIAS LABORADOS"+"    "+"HORAS EXTRAS"+"    "+"SUELDO BASE"+"       "+"COMISIONES"+"         "+    "OTROS"+"     "+"       "+"SUELDO_DIARIO"  +"    "+"SUELDO_ORDINARIO"+"    " +"SUELDO_EXTRAORDINARIO"+"    "+"SEPTIMO"+"              "+"SUBTOTAL"+"             "+"BONIFICACIONES"    +"    "+  "TOTAL_DEVENGADO"+"            "+       "IGSS"  +"               "+          "ISR"  +"                 "+   "ANTICIPOS"  +"    "+  "DESCUENTO_JUDICIAL"  +"    "+      "OTROS_DESCUENTOS"   +"    "+      "TOTAL_DESCUENTOS"     +"    "+     "TOTAL_LIQUIDO");
                    for (int i = 0; i < cont; i++) {
                        for (int j = 0; j < 2; j++) {
                            System.out.print(Dtempleado[i][j]+"     ");

                        }
                        for (int j = 0; j < 19; j++) {
                            System.out.print(valores[i][j]+"             ");

                        }
                        System.out.println("");
                    }




                    break;
            }




        }while(opc!=3);


    }
}
