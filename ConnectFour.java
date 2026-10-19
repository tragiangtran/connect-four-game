public class ConnectFour {
    // Attribute:
    private int[][] field; // array für rows und columns
    private int currentPlayer;
    private int status;
    private static int ROWS = 6;
    private static int COLS = 7;

    // Konstruktor:
    public ConnectFour ()
    {
        field = new int[ROWS][COLS];
        reset ();
        currentPlayer = 1;
        status = 0;
    }

    // Methode:
    // reset-Method
    public void reset ()
    {
        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLS; j++) {
                field [i][j] = 0;
            }
        }
        status = 0;
        currentPlayer = 1;
    }

    // dropDisc-Method
    public void dropDisc (int column)
    {
        if (column<1 || column>7)
        {
            System.out.println ("Eingabe ist ungültig");
            return;
        }
        if (isColumnFull(column)) {
            System.out.println("Diese Spalte ist voll");
            return;
        }
        else {
            int r = findFreeRow(column);
            field[r][column-1] = currentPlayer; 
        }
       if (hasWon(currentPlayer)) {
        status = currentPlayer;
       }
       else {
        if (isBoardFull())
            status = 3;
        else {
            switchPlayer();
            status = 0;
        }
       }
       printBoard();
    }

    // isColumnFull-Method
    private boolean isColumnFull (int column)
    {
        if (field[0][column-1] != 0)
            return true;
        return false;
    }

    // findFreeRow-Method
    private int findFreeRow (int column)
    {
        for (int i=ROWS-1; i>=0; i--) {
            if (field[i][column-1] == 0) return i;
        } // return the index of the blank row
        return -1; 
    }

    // printBoard-Method
    public void printBoard()
    {
        System.out.println ("  1 2 3 4 5 6 7");
        for (int i=0; i<ROWS; i++) {
            System.out.print ("| ");
            for (int j=0; j<COLS; j++) {
                switch (field[i][j])
                {
                    case 0:
                        System.out.print (". ");
                        break;
                    case 1:
                        System.out.print ("X ");
                        break;
                    case 2:
                        System.out.print ("O ");
                        break;
                }
            }
            System.out.println ("|");
        }
        System.out.println();
        printStatusMessage();
    }

    // getCurrentStatus-Method
    public int getCurrentStatus () {
        return status;
    }

    // printStatusMessage-Method
    private void printStatusMessage ()
    {
        switch (status)
        {
            case 0:
                System.out.println ("Am Zug: Spieler " + currentPlayer);
                break;
            case 1:
                System.out.println ("Spieler 1 hat gewonnen");
                break;
            case 2:
                System.out.println ("Spieler 2 hat gewonnen");
                break;
            case 3:
                System.out.println ("Das Spiel endete unentschieden");
                break;
        }
    }
    
    // getCurrentPlayer-Method
    public int getCurrentPlayer () {
        return currentPlayer;
    }

    // switchPlayer-Method
    private void switchPlayer ()
    {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    // hasWon-Method
    private boolean hasWon (int currentPlayer)
    {
        // Die Waargerechte überprüfen
        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<=COLS-4; j++) {
                if (
                    field[i][j] == currentPlayer &&
                    field[i][j+1] == currentPlayer &&
                    field[i][j+2] == currentPlayer &&
                    field[i][j+3] == currentPlayer
                )
                return true;
            }
        }
        // Die Senkrechte überprüfen
        for (int i=0; i<=ROWS-4; i++) {
            for (int j=0; j<COLS; j++) {
                if (
                    field[i][j] == currentPlayer &&
                    field[i+1][j] == currentPlayer &&
                    field[i+2][j] == currentPlayer &&
                    field[i+3][j] == currentPlayer
                )
                return true;
            }
        }
        // Die Diagonale überprüfen
        for (int i=0; i<=ROWS-4; i++) {
            for (int j=0; j<=COLS-4; j++) {
                if (
                    field[i][j] == currentPlayer &&
                    field[i+1][j+1] == currentPlayer &&
                    field[i+2][j+2] == currentPlayer &&
                    field[i+3][j+3] == currentPlayer
                )
                return true;
            }
        }

        for (int i=3; i<ROWS; i++) {
            for (int j=0; j<=COLS-4; j++) {
                if (
                    field[i][j] == currentPlayer &&
                    field[i-1][j+1] == currentPlayer &&
                    field[i-2][j+2] == currentPlayer &&
                    field[i-3][j+3] == currentPlayer
                )
                return true;
            }
        }
        return false;
    }

    // isBoardFull-Method
    private boolean isBoardFull ()
    {
        for (int j=1; j<=COLS; j++) {
            if (!isColumnFull(j)) {
                return false; // phải để là !isColumnFull(j), nếu ko khi máy check thấy 1 cột đầy sẽ dừng lại và báo true
            }
        }
        return true;
    }
    public static void main(String[] args)
    {
        ConnectFour game = new ConnectFour();
        
        // dropDisc-Method überprüfen
        System.out.println ("Test: Volle Spalte");
        for (int i=0; i<6; i++)
            game.dropDisc(1);
        // dropDisc-Method in einer vollen Spalte zu versuchen
        System.out.println ("Test: dropDisc in eine volle Spalte");
        game.dropDisc(1);

        // isColumnFull-Method überprüfen
        System.out.println ("game.isColumnFull (2) -> false");
        System.out.println (game.isColumnFull (2));

        // getCurrentStatus-Method überprüfen
        System.out.println ("game.getCurrentStatus () -> 0");
        System.out.println (game.getCurrentStatus ());

        // printStatusMessage-Method überprüfen
        System.out.println ("game.printStatusMessage () -> Am Zug: Spieler 1");
        game.printStatusMessage ();

        // getCurrentPlayer-Method überprüfen
        System.out.println ("game.getCurrentPlayer() -> 1");
        System.out.println (game.getCurrentPlayer());

        // Nehmen wir an, dass Spieler 1 waargerecht gewinnt
        System.out.println ("Test: Spieler 1 gewinnt waargerecht");
        game.reset();
        game.dropDisc(1); // S1
        game.dropDisc(1); // S2
        game.dropDisc(2); // S1
        game.dropDisc(2); // S2
        game.dropDisc(3); // S1
        game.dropDisc(3); // S2
        game.dropDisc(4); // S1 gewinnt

        // Nehmen wir an, dass Spieler 1 diagonal gewinnt
        System.out.println ("Test: Spieler 1 gewinnt diagonal");
        game.reset();
        game.dropDisc(1); // S1
        game.dropDisc(2); // S2
        game.dropDisc(2); // S1
        game.dropDisc(3); // S2
        game.dropDisc(3); // S1
        game.dropDisc(4); // S2
        game.dropDisc(3); // S1
        game.dropDisc(4); // S2
        game.dropDisc(4); // S1
        game.dropDisc(6); // S2 
        game.dropDisc(4); // S1 gewinnt
    }
}