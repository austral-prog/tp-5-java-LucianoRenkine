package com.cinema;

public class Cinema {

    private Seat[][] seats;

    public Cinema(int[] rows) {
        seats = new Seat[rows.length][];
        initSeats(rows);
    }

    private void initSeats(int[] rows) {
        for (int i = 0; i < rows.length; i++) {
            seats[i] = new Seat[rows[i]];
        }
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
    }

    public int countAvailableSeats() {
        int available_seats = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int k = 0; k < seats[i].length; k++) {
                if (seats[i][k].isAvailable()) {
                    available_seats++;
                    }
                }
            }
        return available_seats;
        }

    public Seat findFirstAvailableSeatInRow(int row) {
        for (int i = 0; i < seats[row].length; i++) {
            if (seats[row][i].isAvailable()) {
                return seats[row][i];
            }
        }
        return null;
    }

    public Seat findFirstAvailableSeat() {
        for (int i = 0; i < seats.length; i++) {
            for (int k = 0; k < seats[i].length; k++) {
                if (seats[i][k].isAvailable()) {
                    return seats[i][k];
                }
            }
        }
        return null;
    }

    public Seat getAvailableSeatsInRow(int row, int amount) {
        int seats_available = 0;
        int seat = 0;
        for (int i = 0; i < seats[row].length; i++) {
            if (seats_available == 0) {
                if (seats[row][i].isAvailable()) {
                    seats_available++;
                    seat = i;
                }
            }
            if (seats_available != 0) {
                if (seats_available < amount) {
                    if (seats[row][i].isAvailable()) {
                        seats_available++;
                    }
                    else {
                        seats_available = 0;
                    }
                }
                if (seats_available == amount) {
                    return seats[row][seat];
                }
            }
        }
        return null;
    }

    public Seat getAvailableSeats(int amount) {
        for (int i = 0; i < seats.length; i++) {
            Seat seat = getAvailableSeatsInRow(i, amount);
            return seat;
        }
        return null;
    }

    public void takeSeats(Seat seat, int amount) {
        for (int i = 0; i < amount; i++) {
            seats[seat.getRow()][seat.getSeatNumber() + i].takeSeat();
        }
    }

    public void releaseSeats(Seat seat, int amount) {
        for (int i = 0; i < amount; i++) {
            seats[seat.getRow()][seat.getSeatNumber() + i].releaseSeat();
        }
    }
}