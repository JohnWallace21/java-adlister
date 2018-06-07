import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection;

    public MySQLAdsDao(Config config) throws SQLException {
        DriverManager.registerDriver(new Driver());

            this.connection = (Connection) DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()

            );

    }


    @Override
    public List<Ad> all() {
//        1.create a statement
//        2.execute statement
//        3.work with resultset to get a list of ads
        List<Ad> ads = new ArrayList<>();
        String query = "SELECT * FROM ads";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                long id = rs.getLong("id");
                long user_id = rs.getLong("user_id");
                String title = rs.getString("title");
                String description = rs.getString("description");

                Ad ad = new Ad(id, user_id, title, description);
                ads.add(ad);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ads;
    }

    @Override
    public Long insert(Ad ad) {
        try {
            Statement stmt = connection.createStatement();
            String sql = createInsertQuery(ad);
            System.out.println("Preparing to run query: " + sql);
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting a new ad", e);
        }

    }

    /**
     * returns an insert query where the values in the query come from
     * the passed ad object
     */
    private String createInsertQuery(Ad ad) {
        String sql = "INSERT INTO ads(user_id, title, description) VALUES(%d, '%s', '%s')";
        return String.format(
                sql,
                ad.getUserId(),
                ad.getTitle(),
                ad.getDescription()
        );
    }
}
