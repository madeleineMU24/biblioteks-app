import java.security.Timestamp;
import java.util.Date;

public class Loan {

    private int id;
    private String user_name;
    private int book_id;
    private Timestamp loan_date;
    private Date return_date;

    public Loan(int id, String user_name, int book_id, Timestamp loan_date, Date return_date) {
        this.id = id;
        this.user_name = user_name;
        this.book_id = book_id;
        this.loan_date = loan_date;
        this.return_date = return_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Timestamp getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(Timestamp loan_date) {
        this.loan_date = loan_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return id +
                ", User: " + user_name +
                ", Book-id: " + book_id +
                ", Loan-date: " + loan_date +
                ", Return-date: " + return_date;
    }
}
