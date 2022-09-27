import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class App {
    public static void main(String[] args) {
        int[][] arrayInput = { { 1, 2, 3 }, { 6, 5, 4 } };
        int result = spiralMatrix(arrayInput);
        System.out.println("FINAL RESULT: " + result);
    }

    // Constante con las operaciones.
    final static ArrayList<String> operations = new ArrayList<String>(Arrays.asList("+", "-", "*", "/"));

    public static int spiralMatrix(int[][] arrayInput) {
        int m = arrayInput.length;// Fila
        int n = arrayInput[0].length;// Columna
        int left = 0;// Dirección izquierda
        int right = arrayInput[0].length - 1;// Dirección correcta
        int top = 0;// Hacia arriba
        int bottom = arrayInput.length - 1;// Hacia abajo

        int[] path = new int[m * n];// Arreglo para ir guardando el recorrido a traves de la matriz
        int temp = 0;// Variable para los indices del path
        String direction = "right";// La dirección a la cual recorrer el grafo

        Iterator<String> it = operations.iterator();// Iterator para determinar cual es la siguiente operacion a
                                                    // realizar

        int result = 0; // Variable donde se va a ir guardando el resultado

        // Hacer hasta que se llegue al centro
        while (left <= right && top <= bottom) {
            // Nos movemos por la izquierda
            if (direction.equals("right")) {
                // Itero sobre todos los elementos a lo --> de la matriz. Cuando se acaba el for
                // es porque se ha alcanzado el limite de la
                // derecha, por lo que entoces nos debemos mover hacia abajo.
                for (int i = left; i <= right; i++) {
                    // El primer elemento de la matiz no consume una operacion.
                    if (top == 0 && i == 0) {
                        result = arrayInput[top][i];
                        System.out.println("Valor inicial : " + arrayInput[top][i]);
                    } else {
                        // Agrego el elemento actual para guardar el recorrido a traves de la matriz.
                        path[temp++] = arrayInput[top][i];
                        System.out.println("Valor casilla : " + arrayInput[top][i]);
                        // Llamo al metodo calculateNextValue para calcular el valor.
                        result = calculateNextValue(result, it, arrayInput[top][i]);
                    }

                }
                top++; // como se ha recorrido una línea de la matriz, se aumenta el top
                direction = "bottom";// Se actualiza a la siguiente dirreccion que debemos tomar.
            } else if (direction.equals("bottom")) {
                for (int i = top; i <= bottom; i++) {
                    path[temp++] = arrayInput[i][right];
                    System.out.println("Valor casilla : " + arrayInput[i][right]);
                    result = calculateNextValue(result, it, arrayInput[i][right]);
                }
                right--;
                direction = "left";
            } else if (direction.equals("left")) {
                for (int i = right; i >= left; i--) {
                    path[temp++] = arrayInput[bottom][i];
                    System.out.println("Valor casilla : " + arrayInput[bottom][i]);
                    result = calculateNextValue(result, it, arrayInput[bottom][i]);
                }
                bottom--;
                direction = "top";
            } else if (direction.equals("top")) {
                for (int i = bottom; i >= top; i--) {
                    path[temp++] = arrayInput[i][left];
                    System.out.println("Valor casilla : " + arrayInput[i][left]);
                    result = calculateNextValue(result, it, arrayInput[i][left]);
                }
                left++;
                direction = "right";
            }
        }
        return result;

    }

    private static int calculateNextValue(int result, Iterator<String> it, int value) {
        // Si hay una siguiente operacion en el Iterator
        if (it.hasNext()) {
            String nextOperation = it.next(); // Operacion actual
            System.out.println("Operacion actual : " + nextOperation);
            System.out.println("Valor acomulado : " + result);
            // En dependencia de la operacion, se realiza el calculo.
            if (nextOperation.equals("+")) {
                result += value;
                System.out.println("Result: " + result);
            } else if (nextOperation.equals("-")) {
                result -= value;
                System.out.println("Result: " + result);
            } else if (nextOperation.equals("*")) {
                result *= value;
                System.out.println("Result: " + result);
            } else if (nextOperation.equals("/")) {
                result /= value;
                System.out.println("Result: " + result);
            }

            // Si no hay una siguiente operacion en el Iterator, significa que ya se llegó a
            // la última(/) y hay que reiniciar el
            // Iterator
        } else {
            it = operations.iterator(); // Se reinicia el Iterator
            String nextOperation = it.next();
            System.out.println("Operacion actual : " + nextOperation);
            System.out.println("Valor acomulado : " + result);
            if (nextOperation.equals("+")) {
                result += value;
                System.out.println("Result: " + result);
            } else if (nextOperation.equals("-")) {
                result -= value;
                System.out.println("Result: " + result);
            } else if (nextOperation.equals("*")) {
                result *= value;
                System.out.println("Result: " + result);
            } else if (nextOperation.equals("/")) {
                result /= value;
                System.out.println("Result: " + result);
            }
        }
        return result;
    }

}