package by.dmLessons;

import by.dmLessons.util.ConnectionManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;

public class BlobRunner {
    public static void main(String[] args) throws SQLException, IOException {

        // binary large object - BLOB картинки, аудио и прочие файлы
        // In the PostgreSQL - BYTEA
        // character large object - CLOB
        // In the PostgreSQL - TEXT

        getImage();

    }

    private static void getImage() throws SQLException, IOException {

// Для хранения картинок, файлов и пр., лучше использовать внешние хранилища, а в базе сохранять ссылку на хранилище.
        var sql = """
                SELECT image
                FROM game.game_table
                WHERE id = ?;
                """;

        try (var connect = ConnectionManager.get();
             var preparedStatement = connect.prepareStatement(sql)) {

            preparedStatement.setInt(1, 1);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                var image = resultSet.getBytes("image");
                Files.write(Path.of("src/main/resources", "boeing_new.jpg"), image, StandardOpenOption.CREATE);
            }
        }
    }

    private static void saveImage() throws SQLException, IOException {
        var sql = """
                UPDATE game.game_table
                SET image = ?
                WHERE id = 1
                """;

// не работает с PostgreSQL
        try (var open = ConnectionManager.get();
             var preparedStatement = open.prepareStatement(sql)) {
//            var exampleString = Files.readAllBytes(Path.of("src/main/resources", "boeing.jpg")).toString();
            preparedStatement.setBytes(1, Files.readAllBytes(Path.of("src/main/resources", "boeing.jpg")));
//            preparedStatement.setString(1, exampleString);
            preparedStatement.executeUpdate();
        }
    }

/*    private static void saveImage() throws SQLException, IOException {
        var sql = """
                UPDATE game.game_table
                SET image = ?
                WHERE id = 1
                """;

// не работает с PostgreSQL
        try (var open = ConnectionManager.open();
             var preparedStatement = open.prepareStatement(sql)) {
            var blob = open.createBlob();
            blob.setBytes(1, Files.readAllBytes(Path.of("resources", "boeing.jpg")));

            preparedStatement.setBlob(1, blob);
            var i = preparedStatement.executeUpdate();
            System.out.println(i);
        }
    }*/
}
