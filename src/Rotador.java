public class Rotador {

    private static int[][] transponerMatriz(int[][] matriz){
        int filas = matriz.length;
        int columnas = matriz[0].length;

        int[][] transpuesta = new int[columnas][filas];

        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
                transpuesta[j][i]=matriz[i][j];
            }
        }

        return transpuesta;
    }

    private static int[][] invertirMatriz(int[][] matriz){
        int filas = matriz.length;
        int columnas = matriz[0].length;

        int[][] inversa = new int[filas][columnas];

        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
                inversa[i][j]=matriz[i][columnas-j-1];
            }
        }

        return inversa;
    }

    public static int[][] clonarMatriz(int[][] matriz) {
        int[][] copia = new int[matriz.length][];
        for (int i = 0; i < matriz.length; i++) {
            copia[i] = matriz[i].clone();
        }
        return copia;
    }

    public static int[][] rotarMatriz(int[][] matriz){
        int[][] matrizRotada = transponerMatriz(matriz);
        matrizRotada = invertirMatriz(matrizRotada);

        return matrizRotada;
    }

}
