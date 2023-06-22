package unal.todosalau.triquicicloforanidado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[][] board = new String[3][3];
    private int player = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa los botones del tablero
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int buttonId = getResources().getIdentifier("button" + (i*3+j+1), "id", getPackageName());
                Button button = findViewById(buttonId);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Lógica del juego
                        Button button = (Button) v;

                        // Obtén la posición del botón en el tablero
                        int row = 0;
                        int col = 0;
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (button.getId() == getResources().getIdentifier("button" + (i*3+j+1), "id", getPackageName())) {
                                    row = i;
                                    col = j;
                                }
                            }
                        }

                        // Establece el valor del botón según el jugador actual
                        if (player == 0) {
                            button.setText("X");
                            board[row][col] = "X";
                            player = 1;
                        } else {
                            button.setText("O");
                            board[row][col] = "O";
                            player = 0;
                        }

                        // Comprueba si hay un ganador
                        if (checkWinner()) {
                            // Muestra el mensaje de ganador
                            Toast.makeText(MainActivity.this, "¡Jugador " + (player + 1) + " ha ganado!", Toast.LENGTH_SHORT).show();
                        } else {
                            // Continúa el juego
                        }
                    }
                });
            }
        }
    }

    private boolean checkWinner() {
        // Comprueba las filas
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])) {
                return true;
            }
        }

        // Comprueba las columnas
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != null && board[0][j].equals(board[1][j]) && board[0][j].equals(board[2][j])) {
                return true;
            }
        }

        // Comprueba las diagonales
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) {
            return true;
        }
        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) {
            return true;
        }

        // No hay ganador
        return false;
    }

}