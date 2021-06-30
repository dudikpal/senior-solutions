import org.flywaydb.core.Flyway;


import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MariadbMeetingRoomsRepository implements MeetingRoomsRepository {

    private JdbcTemplate jdbcTemplate;
    MariaDbDataSource dataSource;

    public MariadbMeetingRoomsRepository() {
        try {
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3308/employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");

            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            //flyway.clean();
            flyway.migrate();

            jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot create datasource", e);
        }
    }


    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from meetingrooms");
    }

    @Override
    public List<MeetingRoom> areaMoreThan(int minArea) {
        List<MeetingRoom> result = jdbcTemplate.query("select * from meetingrooms where area > ?",
                (rs, i) -> new MeetingRoom(rs.getString("name"),
                        rs.getInt("width"),
                        rs.getInt("length")),
                minArea);
        return result;
    }

    @Override
    public Optional<String> findByPartOfName(String partName) {
        MeetingRoom meetingRoom;
        List<MeetingRoom> result = jdbcTemplate.query("select * from meetingrooms where name like ? order by name",
                (rs, i) -> new MeetingRoom(rs.getString("name"),
                        rs.getInt("width"),
                        rs.getInt("length")),
                "%" + partName + "%");
        if (result.size() != 0) {
            return Optional.of(result.toString());
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findByFullName(String roomName) {
        /*return Optional.of(jdbcTemplate.queryForObject("select * from meetingrooms where name = ?",
                MeetingRoom.class, roomName));*/
        try {

            Optional<MeetingRoom> result = jdbcTemplate.queryForObject("select * from meetingrooms where name = ?",
                    new RowMapper<Optional<MeetingRoom>>() {
                        @Override
                        public Optional<MeetingRoom> mapRow(ResultSet resultSet, int i) throws SQLException {
                            return Optional.of(new MeetingRoom(
                                    resultSet.getString("name"),
                                    resultSet.getInt("width"),
                                    resultSet.getInt("length")
                            ));
                        }
                    }, new Object[]{roomName}
            );
            if (result.isPresent()) {
                return Optional.of("Width: " + result.get().getWidth() +
                        " Length: " + result.get().getLength() +
                        " Area: " + result.get().getArea());
            }
        } catch (EmptyResultDataAccessException e) {

        }
        return Optional.empty();
    }

    @Override
    public List<String> roomsByArea() {
        return jdbcTemplate.query("select * from meetingrooms order by area desc",
                (rs, i) -> new StringBuilder()
                        .append("\nRoom name: ").append(rs.getString("name"))
                        .append(", Width: ").append(rs.getInt("width"))
                        .append(", Length: ").append(rs.getInt("length"))
                        .append(", Area: ").append(rs.getInt("area"))
                        .toString());
    }

    @Override
    public List<String> everySecondRooms() {
        return listAll().stream()
                .map(MeetingRoom::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> reverseSortedRoomNames() {
        /*return listAll().stream()
                .map(MeetingRoom::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());*/
        return jdbcTemplate.query("select name from meetingrooms order by name desc",
                (rs, i) -> new String(rs.getString("name")));
    }

    @Override
    public List<String> sortedRoomNames() {
        /*return listAll().stream()
                .map(MeetingRoom::getName)
                .sorted()
                .collect(Collectors.toList());*/
        return jdbcTemplate.query("select name from meetingrooms order by name",
                (rs, i) -> new String(rs.getString("name")));
    }


    @Override
    public List<MeetingRoom> listAll() {
        List<MeetingRoom> rooms = jdbcTemplate.query("select id, name, width, length  from meetingrooms",
                (rs, i) -> new MeetingRoom(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("width"), rs.getInt("length")
                ));

        for (MeetingRoom room : rooms) {
            room.addMeetingList(
                    jdbcTemplate.query("select start_meeting, end_meeting from meetings where room_id = ?",
                            (rs, i) -> new Meeting(
                                    LocalDateTime.parse(rs.getString("start_meeting")),
                                    LocalDateTime.parse(rs.getString("end_meeting"))
                            ),
                            room.getId())
            );
        }

        return rooms;
        /* soronként külön adja vissza a  meetingeket
        return jdbcTemplate.query("select mr.id, mr.name, mr.width, mr.length, m.start_meeting, m.end_meeting, m.room_id " +
                "from meetingrooms as mr " +
                "inner join meetings as m " +
                "where mr.id = m.room_id",
                rs -> {
                    List<MeetingRoom> result = new ArrayList<>();
                    while (rs.next()) {
                       MeetingRoom mr = new MeetingRoom(
                               rs.getLong("id"),
                               rs.getString("name"),
                               rs.getInt("width"),
                               rs.getInt("length")
                       );

                       List<Meeting> meetings = new ArrayList<>();
                       if (rs.getString("m.start_meeting") != null) {
                           mr.addMeeting(new Meeting(
                                   LocalDateTime.parse(rs.getString("start_meeting")),
                                   LocalDateTime.parse(rs.getString("end_meeting"))
                           ));
                       }
                        result.add(mr);
                    }
                   return result;
                });*/
    }

    /*@Override
    public void save(String name, int width, int length) {
        jdbcTemplate.update("insert into meetingrooms(name, width, length, area) values(?, ?, ?, ?)", name, width, length, width * length);
    }*/

    @Override
    public void save(MeetingRoom meetingRoom) {
        try (Connection conn = dataSource.getConnection()) {

            try (PreparedStatement stmt = conn.prepareStatement("insert into meetingrooms(name, width, length, area) values(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                conn.setAutoCommit(false);
                stmt.setString(1, meetingRoom.getName());
                stmt.setInt(2, meetingRoom.getWidth());
                stmt.setInt(3, meetingRoom.getLength());
                stmt.setInt(4, meetingRoom.getArea());
                stmt.executeUpdate();
                long key = 0;
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        key = rs.getLong(1);
                    }
                }

                for (Meeting meeting : meetingRoom.getMeetings()) {
                    try (PreparedStatement stmt2 = conn.prepareStatement(
                            "insert into meetings(start_meeting, end_meeting, room_id) values (?, ?, ?)"
                    )) {

                        stmt2.setString(1, meeting.getStartMeeting().toString());
                        stmt2.setString(2, meeting.getEndMeeting().toString());
                        stmt2.setLong(3, key);
                        stmt2.executeUpdate();
                    }
                }
                conn.commit();
            } catch (SQLException se) {
                System.out.println(se);
                conn.rollback();
            }
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
    }
}
