package Cine;

public class CinemaStarter {
    private boolean[][] asientos;  // true = reserved, false = available
    private int filas;
    private int columnas;
    private int totalReservas;

    public CinemaStarter(int filas, int columnas) {
        // Initialize the cinema
    }

    public void Sillas() {
        System.out.println("\n     === PANTALLA ===");
        
        // TODO: Print column numbers (hint: print "     " first for alignment)
        // Example: "     0  1  2  3  4"
        
        // TODO: For each row:
        //   - Print row number
        //   - For each seat in that row:
        //     - Print 'O' if available (false)
        //     - Print 'X' if reserved (true)
        
        System.out.println(); // Empty line at the end
    }
    
    /**
     * Method 2: Reserve a seat
     * Time: 5 minutes
     */
    public boolean reservarSilla(int fila, int columna) {
        // TODO: Check if row is valid (0 to rows-1)
        // TODO: Check if col is valid (0 to columns-1)
        // If invalid, print error and return false
        
        // TODO: Check if seat is already reserved
        // If reserved, print "Seat already reserved!" and return false
        
        // TODO: Reserve the seat (set to true)
        // TODO: Increment totalReservations
        // TODO: Print success message like "Seat [row,col] reserved successfully!"
        // TODO: Return true
        
        return false;
    }
    
    /**
     * Method 3: Cancel a reservation
     * Time: 5 minutes
     */
    public boolean cancelarReserva(int fila, int columna) {
        // TODO: Check if row and col are valid
        
        // TODO: Check if seat is actually reserved
        // If not reserved, print "Seat is not reserved!" and return false
        
        // TODO: Make seat available (set to false)
        // TODO: Decrement totalReservations
        // TODO: Print success message
        // TODO: Return true
        
        return false;
    }
    
    /**
     * Method 4: Count available seats
     * Time: 5 minutes
     */
    public int contarAsientosDisponibles() {
        int count = 0;
        
        // TODO: Use nested loops to go through all seats
        // TODO: Count seats that are false (available)
        // Hint:
        // for (int i = 0; i < rows; i++) {
        //     for (int j = 0; j < columns; j++) {
        //         if (!seats[i][j]) count++;
        //     }
        // }
        
        return count;
    }
    
    /**
     * Helper method: Check if a specific seat is available
     */
    public boolean asientosDisponibles(int fila, int columna) {
        // TODO: Check bounds first
        // TODO: Return true if seat is available (false in array)
        return false;
    }
    
    /**
     * Helper method: Get total reservations
     */
    public int totalReservas() {
        return totalReservas;
    }
    
    /**
     * Helper method: Get occupancy percentage
     */
    public double obtenerPorcentajeOcupacion() {
        // TODO: Calculate percentage of reserved seats
        // Hint: (totalReservations * 100.0) / (rows * columns)
        return 0.0;
    }
    
}
