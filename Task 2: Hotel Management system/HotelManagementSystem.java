import java.util.ArrayList;
import java.util.Scanner;

// Room class
class Room {
    private int roomNumber;
    private String type;
    private boolean isBooked;

    public Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isBooked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookRoom() {
        this.isBooked = true;
    }

    public void unbookRoom() {
        this.isBooked = false;
    }
}

// Guest class
class Guest {
    private String name;
    private int id;

    public Guest(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

// Booking class
class Booking {
    private Guest guest;
    private Room room;

    public Booking(Guest guest, Room room) {
        this.guest = guest;
        this.room = room;
        room.bookRoom();
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Booking [Guest: " + guest.getName() + ", Room: " + room.getRoomNumber() + " (" + room.getType() + ")]";
    }
}

// Hotel class
class Hotel {
    private ArrayList<Room> rooms;
    private ArrayList<Guest> guests;
    private ArrayList<Booking> bookings;

    public Hotel() {
        rooms = new ArrayList<>();
        guests = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
    }

    public void bookRoom(int guestId, int roomNumber) {
        Guest guest = findGuestById(guestId);
        Room room = findRoomByNumber(roomNumber);
        if (guest != null && room != null && !room.isBooked()) {
            bookings.add(new Booking(guest, room));
            System.out.println("Room booked successfully.");
        } else {
            System.out.println("Booking failed. Either room is already booked or guest/room does not exist.");
        }
    }

    public void showAllBookings() {
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    private Guest findGuestById(int id) {
        for (Guest guest : guests) {
            if (guest.getId() == id) {
                return guest;
            }
        }
        return null;
    }

    private Room findRoomByNumber(int number) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == number) {
                return room;
            }
        }
        return null;
    }
}

// Main class
public class HotelManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Hotel hotel = new Hotel();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Hotel Management System");
            System.out.println("1. Add Room");
            System.out.println("2. Add Guest");
            System.out.println("3. Book Room");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    addGuest();
                    break;
                case 3:
                    bookRoom();
                    break;
                case 4:
                    viewAllBookings();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addRoom() {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter room type: ");
        String type = scanner.nextLine();
        hotel.addRoom(new Room(roomNumber, type));
        System.out.println("Room added successfully.");
    }

    private static void addGuest() {
        System.out.print("Enter guest ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter guest name: ");
        String name = scanner.nextLine();
        hotel.addGuest(new Guest(name, id));
        System.out.println("Guest added successfully.");
    }

    private static void bookRoom() {
        System.out.print("Enter guest ID: ");
        int guestId = scanner.nextInt();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        hotel.bookRoom(guestId, roomNumber);
    }

    private static void viewAllBookings() {
        hotel.showAllBookings();
    }
}
