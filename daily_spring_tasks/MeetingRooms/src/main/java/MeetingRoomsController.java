import java.util.Optional;
import java.util.Scanner;

public class MeetingRoomsController {

    private Scanner scanner = new Scanner(System.in);
    private MeetingRoomsService meetingRoomsService = new MeetingRoomsService(new MariadbMeetingRoomsRepository());

    public static void main(String[] args) {

        new MeetingRoomsController().menu();
    }

    public void menu() {

        System.out.println("""
                                
                0. Tárgyaló rögzítése
                1. Tárgyalók névsorrendben
                2. Tárgyalók név alapján visszafele sorrendben
                3. Minden második tárgyaló
                4. Területek
                5. Keresés pontos név alapján
                6. Keresés névtöredék alapján
                7. Keresés terület alapján
                8. Kilépés""");

        String choosedMenu = scanner.nextLine();

        switch (choosedMenu) {
            case "0" -> addMeetingRoom();
            case "1" -> sortedRoomNames();
            case "2" -> reverseSortedRoomNames();
            case "3" -> everySecondRooms();
            case "4" -> roomsByArea();
            case "5" -> findByFullName();
            case "6" -> findByPartOfName();
            case "7" -> areaMoreThan();
            case "8" -> System.exit(0);
        }
    }

    private void areaMoreThan() {
        System.out.println("Give the minimum area:");
        int minArea = scanner.nextInt();
        System.out.println(meetingRoomsService.areaMoreThan(minArea));
        menu();
    }


    private void findByPartOfName() {
        System.out.println("Part of room name:");
        String partName = scanner.nextLine();
        Optional<String> result = meetingRoomsService.findByPartOfName(partName);
        if (!result.isEmpty()) {
            System.out.println(result.get());
        }
        menu();
    }

    private void findByFullName() {
        System.out.println("Room name to search:");
        String roomName = scanner.nextLine();
        Optional<String> result = meetingRoomsService.findByFullName(roomName);
        if (!result.isEmpty()) {
            System.out.println(result.get());
        }
        menu();
    }

    private void roomsByArea() {
        System.out.println(meetingRoomsService.roomsByArea());
        menu();
    }

    private void everySecondRooms() {
        System.out.println(meetingRoomsService.everySecondRooms());
        menu();
    }

    private void reverseSortedRoomNames() {
        System.out.println(meetingRoomsService.reverseSortedRoomNames());
        menu();
    }

    private void sortedRoomNames() {
        System.out.println(meetingRoomsService.sortedRoomNames());
        menu();
    }

    private void addMeetingRoom() {
        System.out.println("Room name:");
        String name = scanner.nextLine();
        System.out.println("Room width:");
        int width = scanner.nextInt();
        System.out.println("Room length:");
        int length = scanner.nextInt();
        scanner.nextLine(); // ha ez nincs itt, akkor kilép a menüből, mert bent marad egy enter
        meetingRoomsService.save(new MeetingRoom(name, width, length));
        System.out.println("Meeting room saved.");
        menu();
    }
}
