import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanDAO {


    public void loanBook(String title){
        String sqlCount = "SELECT COUNT(*) AS count FROM book WHERE title = ?";
        String sqlUpdate =  "UPDATE book SET available = false WHERE title = ? AND available = true";

    try{
        Connection conn = Database.getConnection();
        PreparedStatement stmtCount = conn.prepareStatement(sqlCount);
        stmtCount.setString(1,title);

        ResultSet rs = stmtCount.executeQuery();
        if(rs.next() && rs.getInt("count") == 0){
            System.out.println("The book with the title " + title + " doesn't exist");
            return;
        }

        PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
        stmtUpdate.setString(1, title);

        int affectedRows = stmtUpdate.executeUpdate();
        if(affectedRows > 0){
            System.out.println(title + " is successfully loaned");
        }else{
            System.out.println(title + " is not available");
        }

    }catch (SQLException e){
        System.out.println("Failed to loan book");
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
                System.out.println("The book with the title " + title + " doesn't exist");
                return;
            }

            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
            stmtUpdate.setString(1,title);

            int affectedRows = stmtUpdate.executeUpdate();
            if(affectedRows > 0){
                System.out.println(title + " is successfully returned");
            }else {
                System.out.println(title + " is already available");
            }

        }catch(SQLException e){
            System.out.println("Failed to return book");
            e.printStackTrace();
        }

    }

    public void listLoanedBooks(){
        String sql = " SELECT * FROM book WHERE available = false";

        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String title = rs.getString("title");
                String author = rs.getString("author");
                System.out.println("Author: " + author + ", Title: " + title);
            }

        }catch(SQLException e){
            System.out.println("Failed to list loaned books");
        }
    }

}
