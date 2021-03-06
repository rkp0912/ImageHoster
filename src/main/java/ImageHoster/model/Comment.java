package ImageHoster.model;

import javax.persistence.*;
import java.util.Date;


//Creates a table named 'comments' in the database
@Entity
@Table(name="comments")
public class Comment {

    //Primary key and the value is autogenerated.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    //Field to enter the comments
    @Column(columnDefinition = "TEXT")
    private String text;

    //Date and time at which comment is created
    @Column(name="createddate")
    private Date createdDate;

    //The 'comments' table is mapped to 'users' table with Many:One mapping
    //One comment can have only user (owner) but one user can create multiple comments
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    //The 'comments' table is mapped to 'images' table with Many:One mapping
    //One comment can belong to only one image but one image can have multiple comments
    //Below annotation indicates that the name of the column in 'comment' table referring the primary key in 'images' table will be 'image_id'
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
