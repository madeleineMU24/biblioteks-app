import java.util.List;
import java.util.Scanner;

public class ConsolView {

    private BookDAO bookDAO = new BookDAO();
    private LoanDAO loanDAO = new LoanDAO();
    private Scanner sc = new Scanner(System.in);

    public void showMenu(){

        while (true){
            System.out.println("  ----Menu----");
            System.out.println("1. I'm a user");
            System.out.println("2. I'm a admin");
            System.out.println("0. Quit");

            String choice = sc.nextLine();

            switch (choice) {
                case "1": showUserMenu();
                    break;
                case "2": showAdminMenu();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice, try again\n");
            }
        }

    }

    public void showAdminMenu() {
        while (true){
            System.out.println("  ----Admin menu----");
            System.out.println("1. Show all books and availability");
            System.out.println("2. Add book");
            System.out.println("3. Delete book");
            System.out.println("0. Back to main menu");

            String choice = sc.nextLine();

            switch (choice){
                case "1": getAllBooks();
                    break;
                case "2": addBook();
                    break;
                case "3": deleteBook();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice, try again\n");
            }
        }
    }

    public void showUserMenu() {
        while (true){
            System.out.println("  ----User menu----");
            System.out.println("1. Show all books and availability");
            System.out.println("2. Loan a book");
            System.out.println("3. Return a book");
            System.out.println("4. See my loaned books");
            System.out.println("0. Back to main menu");

            String choice = sc.nextLine();

            switch (choice){
                case "1": getAllBooks();
                    break;
                case "2": loanBook();
                    break;
                case "3": returnBook();
                    break;
                case "4": listLoanedBooks();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice, try again\n");
            }
        }
    }
    public void getAllBooks(){
        List<Book> books = bookDAO.getAllBooks();
        System.out.println(" ---Books---");
        books.forEach(s -> System.out.println(s));
        System.out.println("\n");
    }

    public void addBook(){
        System.out.println("Enter the author of the book:");
        String author = sc.nextLine();
        System.out.println("Enter the title of the book:");
        String title = sc.nextLine();

        bookDAO.addBook(title, author, true);
    }
    public void deleteBook(){
        System.out.println("Enter the title of the book you want to delete:");
        String title = sc.nextLine();

        bookDAO.deleteBook(title);
    }

    public void loanBook(){
        System.out.println("Enter the title of the book you want to loan:");
        String title = sc.nextLine();
        loanDAO.loanBook(title);
    }
    public void returnBook(){
        System.out.println("Enter the title of the book you want to return:");
        String title = sc.nextLine();

        loanDAO.returnBook(title);
    }
    public void listLoanedBooks(){
        List<Book> loanedBooks = loanDAO.listLoanedBooks();
        if(loanedBooks.isEmpty()){
            System.out.println("  *Empty*  Time to start reading!\n");
        }else {
            System.out.println(" ---Loaned books---");
            loanedBooks.forEach(book -> System.out.println("Author: " + book.getAuthor() + ", Title: " + book.getTitle()));
            System.out.println("\n");
        }
        }
    }
