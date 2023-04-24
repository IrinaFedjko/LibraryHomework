package enties;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter

public class Customers {
    private Integer id;
    private String userName;
    private String email;
    private String phoneNumber;
    private int bookId;

    public Customers(Integer id, String userName, String email, String phoneNumber, int bookId) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bookId = bookId;
    }

    public Customers(int id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                        "userName=" + userName + ", \n" +
                        "email=" + email + ", \n" +
                        "phoneNumber=" + phoneNumber + ", \n" +
                        "bookId=" + bookId ;
    }
}
