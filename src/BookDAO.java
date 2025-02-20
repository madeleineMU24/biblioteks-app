import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {


    public void addBook (String title, String author, boolean available){
        String sql = "INSERT INTO book (title, author, available) VALUES (?, ?, ?)";

        try{
            Connection conn = Database.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setBoolean(3, true);

            stmt.executeUpdate();
            System.out.println("Book successfully added");

        }catch (SQLException e){
            System.out.println("Failed to add book");
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM book";

        try{
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available")));
            }

        } catch (SQLException e) {
            System.out.println("Failed to get the books");
            e.printStackTrace();
        }
        return books;
    }

    public void deleteBook(String title){
        String sql = "DELETE FROM book WHERE title = ?";

        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, title);
            int affectedRown = stmt.executeUpdate();

            if(affectedRown > 0){
                System.out.println("Product succesfully deleted");
            }else {
                System.out.println("No book with that title");
            }

        }catch (SQLException e){
        System.out.println("Failed to delete the book");
        e.printStackTrace();
        }
    }

}
