package aplicacion.Calculadora;

/**
 * Clase que guarda los métodos para hacer operaciones con matrices
 */
public class Matriz {

    private  double [][] coeficientes ;


    /*** Método constructor que recibe como parámetro un arreglo bidimensional
     * @param arreglo .-   Recibe un arreglo bidimensional
     */
    public Matriz(double [][] arreglo) {

        this.coeficientes = arreglo ;

    }

    /*** Método constructor que recibe como parámetro una matriz
     * @param --Matriz.-   Recibe una matriz
     */
    public Matriz(Matriz arreglo) {

        this.coeficientes = arreglo.coeficientes ;

    }

    /***Método para multiplicar una matriz por un escalar
     *@param --double - Recibe un número para multiplicar la matriz
     */
    public Matriz multiplicacionEscalar (double numero){

        double [][] auxiliar = new double[coeficientes.length][coeficientes[0].length];
        for (int i = 0 ; i < this.coeficientes.length ; i++){
            for (int j = 0 ; j < this.coeficientes[0].length; j++){
                auxiliar [i][j] = numero * (this.coeficientes[i][j]) ;
            }
        }
        return new Matriz (auxiliar);
    }



    /*** Método para sumar dos matrices
     *@param --Matriz - Matriz que se desea sumar
     */

    public Matriz suma (Matriz arregloSuma  ) {

        double [][] auxiliar = new double [arregloSuma.coeficientes.length] [arregloSuma.coeficientes[0].length] ;

        if ((this.coeficientes.length == arregloSuma.coeficientes.length) && (this.coeficientes[0].length == arregloSuma.coeficientes[0].length)){
            for (int i = 0 ; i < this.coeficientes.length ; i++ ){
                for (int j = 0 ; j < this.coeficientes[0].length ; j++){
                    auxiliar[i][j] = this.coeficientes[i][j] + arregloSuma.coeficientes[i][j] ;
                }
            }
        } else {
            System.out.println ("No se puede sumar matrices de distintas dimensiones");

        }
        return new Matriz (auxiliar) ;
    }


    /*** Método para obtener la traspuesta de una matriz
     *
     */
    public Matriz traspuesta (){
        double [][] auxiliar =  new double[this.coeficientes[0].length][this.coeficientes.length];
        for (int i = 0 ; i < coeficientes.length ; i++){
            for (int j = 0 ; j < coeficientes[0].length ; j++){
                auxiliar[j][i] = coeficientes [i][j] ;
            }
        }
        return new Matriz (auxiliar);
    }


    /*** Método para multiplicar matrices
     *@param --matriz- Recibe como parámetro una matriz cuyo número de filas sea igual al número de comlumnas de la otra matriz
     */
    public Matriz multiplicacion (Matriz M){
        double [][] auxiliar = new double [this.coeficientes.length][M.coeficientes[0].length];
        if (this.coeficientes[0].length == M.coeficientes.length){
            for (int i = 0 ; i < auxiliar.length ; i++ ){
                for (int j = 0 ; j < auxiliar[0].length; j++){
                    auxiliar[i][j] = this.karatazo (coeficientes, M.coeficientes,i, j) ;
                }
            }

        } else {

            System.out.println("No se puede multiplicar las matrices porque las dimensiones no son las adecuadas");

            return null ;
        }
        return new Matriz (auxiliar);
    }


    // Método auxiliar para el método multiplicación
    private double karatazo (double [][] M1, double [][] M2,int m, int n){
        double suma = 0;
        for (int i = 0 ; i < M1[0].length ; i++){
            suma += M1[m][i] * M2 [i][n];

        }
        return suma ;
    }

    //Método auxiliar para el método gaussJordan

    private void pivotar (int fila, double [][] arreglo){
        double pivote = arreglo [fila][fila] ;

        if (pivote == 0 ){
            this.intercambiar(fila, fila+1, arreglo);
            pivote = arreglo[fila][fila];
        }

        for (int i = fila ; i < arreglo[0].length ; i++){

            arreglo[fila][i] = arreglo[fila][i]/pivote;

        }
    }

    //Método auxiliar para el método gaussJordan
    private void restarFila (int pivote, int fila, double[][] arreglo ){
        double constante = arreglo[fila][pivote];

        for (int i = pivote; i < arreglo[0].length ; i++){
            arreglo[fila][i] = (arreglo[fila][i])-(constante*arreglo[pivote][i]);

        }
    }


    //Método auxiliar para el método gaussJordan
    private void eliminarColumna (int pivote, double[][] arreglo){

        for (int i = pivote+1 ; i < arreglo.length ; i++){
            this.restarFila(pivote, i, arreglo);
        }
    }


    //Método auxiliar para el método gaussJordan
    private void diagonalInferior (double [][] arreglo){

        for(int i = 0 ; i < arreglo[0].length-2 ; i++){
            this.pivotar (i, arreglo);
            this.eliminarColumna(i, arreglo);

        }
        this.pivotar(arreglo[0].length-2, arreglo);

    }

    //Método auxiliar para el método gaussJordan
    private void eliminarColumnaSup (int pivote, double[][] arreglo){

        for (int i = pivote-1 ; i >= 0 ; i--){
            this.restarFila(pivote, i, arreglo);
        }
    }



    //Método auxiliar para el método gaussJordan
    private void diagonalSuperior (double [][] arreglo){
        for (int i = arreglo[0].length-2 ; i > 0 ; i--){
            this.eliminarColumnaSup(i, arreglo);
        }
    }

    /***Método para resolver sistemas de ecuaciones
     *
     */

    public String  gaussJordan(){
        String c = "";
        int fila = this.coeficientes.length;
        int columnas = this.coeficientes[0].length ;
        if (fila+1 == columnas ){
            double [][] auxiliar = new double[this.coeficientes.length][this.coeficientes[0].length];
            for (int i = 0 ; i < auxiliar.length ; i++){
                for (int j = 0 ; j < auxiliar[0].length ; j++){

                    auxiliar[i][j] = this.coeficientes[i][j];
                }
            }

            this.diagonalInferior(auxiliar);
            this.diagonalSuperior(auxiliar);

            for (int i = 0 ; i < auxiliar.length ; i++){
                double  a = auxiliar[i][auxiliar[0].length-1];

                c += "X" + (i+1) + " = " + a  + "\n" ;
            }

        } else {
            System.out.println("No se puede hacer la operación solicitada. La matriz no cumple con las condiciones requeridas");

        }
        return c ;
    }

    //Método auxiliar para el método determinante
    private void restarFilaDet (int pivote, int fila, double[][] arreglo ){

        double constante1 = arreglo[fila][pivote];
        double constante2 = arreglo[pivote][pivote];
        if (constante2 == 0 ){
            this.intercambiar(pivote, fila, arreglo);
            constante1 = arreglo[fila][pivote];
            constante2 = arreglo[pivote][pivote];
        }


        for (int i = pivote; i < arreglo[0].length ; i++){
            arreglo[fila][i] = (arreglo[fila][i])-((constante1/constante2) * arreglo[pivote][i]);

        }
    }

    //Método para intercambiar filas
    private void intercambiar (int pivote, int fila, double[][] arreglo ){

        double constante2 = arreglo[pivote][pivote];
        while ((constante2 == 0) && (fila < arreglo.length) ){
            double [] arreglo2 = new double [arreglo[0].length];
            for (int j = 0 ; j <  arreglo[0].length ; j++){
                arreglo2[j] = arreglo[pivote][j];
            }
            for (int j = 0 ; j <  arreglo[0].length ; j++){
                arreglo[pivote][j] = arreglo[fila][j];
            }
            for (int j = 0 ; j <  arreglo[0].length ; j++){
                arreglo[fila][j] = arreglo2[j] ;
            }

            constante2 = arreglo[pivote][pivote];
            fila++ ;

        }

    }

    //Método auxiliar para el método determinante
    private void eliminarColumnaDet (int pivote, double[][] arreglo){

        for (int i = pivote+1 ; i < arreglo.length ; i++){
            this.restarFilaDet(pivote, i, arreglo);
        }
    }


    //Método auxiliar para el método determinante
    private void diagonalInferiorDet (double [][] arreglo){

        for(int i = 0 ; i < arreglo[0].length-1 ; i++){
            this.eliminarColumnaDet(i, arreglo);
        }
    }

    /** Método para obtener el determinante de una matriz
     *
     */
    public double determinante(){
        double [][] auxiliar = new double[this.coeficientes.length][this.coeficientes[0].length];
        for (int i = 0 ; i < auxiliar.length ; i++){
            for (int j = 0 ; j <  auxiliar[0].length ; j++){

                auxiliar[i][j] = this.coeficientes[i][j];
            }
        }


        double resultado = 1 ;
        this.diagonalInferiorDet(auxiliar);
        for (int i = 0 ; i < auxiliar[0].length ; i++){
            resultado *= auxiliar[i][i];

        }
        return resultado ;
    }


    /***Método para imprimir matrices en pantalla
     *@return String - Regresa un matriz en forma string
     */
    public String toString(){
        String a = "" ;
        for (int i = 0 ; i < this.coeficientes.length ; i++){
            a += "(";
            for (int j = 0 ; j < this.coeficientes[0].length ; j++){
                if (j < this.coeficientes[0].length-1){

                    a += this.coeficientes[i][j] + " " ;
                } else {
                    a += this.coeficientes[i][j] ;
                }
            }
            a += ")" + "\n" ;
        }
        return a ;
    }



    public double [][] obtenerCoef(){
        return this.coeficientes ;

    }

}

