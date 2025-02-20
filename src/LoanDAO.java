import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    public void loanBook(String title) {
        String sqlCount = "SELECT COUNT(*) AS count FROM book WHERE title = ?";
        String sqlUpdate = "UPDATE book SET available = false WHERE title = ? AND available = true";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmtCount = conn.prepareStatement(sqlCount);
            stmtCount.setString(1, title);

            ResultSet rs = stmtCount.executeQuery();
            if (rs.next() && rs.getInt("count") == 0) {
                System.out.println("The book with the title " + title + " doesn't exist\n");
                return;
            }

            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
            stmtUpdate.setString(1, title);

            int affectedRows = stmtUpdate.executeUpdate();
            if (affectedRows > 0) {
                System.out.println(title + " is successfully loaned\n");
            } else {
                System.out.println(title + " is not available\n");
            }

        } catch (SQLException e) {
            System.out.println("Failed to loan book\n");
            e.printStackTrace();
        }
    }

    public void returnBook(String title){
        String sqlCount = "SELECT COUNT(*) AS count FROM book WHERE title = ?";
        String sqlUpdate = "UPDATE book SET available = true WHERE title = ? AND available = false";

        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmtCount = conn.prepareStatement(sqlCount);
            stmtCount.setString(1,title);

            ResultSet rs = stmtCount.executeQuery();
            if(rs.next() && rs.getInt("count") == 0){
                System.out.println("The book with the title " + title + " doesn't exist\n");
                return;
            }

            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
            stmtUpdate.setString(1,title);

            int affectedRows = stmtUpdate.executeUpdate();
            if(affectedRows > 0){
                System.out.println(title + " is successfully returned\n");
            }else {
                System.out.println(title + " is already available\n");
            }

        }catch(SQLException e){
            System.out.println("Failed to return book\n");
            e.printStackTrace();
        }
    }

    public List<Book> listLoanedBooks() {
        List<Book> loanedBooks = new ArrayList<>();
        String sql = " SELECT * FROM book WHERE available = false";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                boolean available = rs.getBoolean("available");
                loanedBooks.add(new Book(id, title, author, available));
            }

        } catch (SQLException e) {
            System.out.println("Failed to list loaned books\n");
        }
        return loanedBooks;
    }

}
